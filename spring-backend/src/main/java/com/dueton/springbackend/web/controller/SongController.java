package com.dueton.springbackend.web.controller;

import com.dueton.springbackend.persistence.model.SongSimplified;
import com.dueton.springbackend.persistence.model.SongSimplifiedBuilder;
import com.dueton.springbackend.persistence.model.User;
import com.dueton.springbackend.persistence.model.Vote;
import com.dueton.springbackend.service.*;
import com.dueton.springbackend.service.responseEntities.Results;
import com.dueton.springbackend.service.responseEntities.iTunesSong;
import com.dueton.springbackend.web.dto.SongDto;
import com.dueton.springbackend.web.dto.SongBuilder;
import com.dueton.springbackend.web.dto.StatusDto;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.RolesAllowed;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/songs")
public class SongController {

  private final ISongService songService;
  private final IiTunesService iTunesService;
  private final ISpotifyService spotifyService;
  private final IYoutubeService youtubeService;

  private final IUserService userService;
  private final IVoteService voteService;

  public SongController(ISongService songService, IiTunesService iTunesService, ISpotifyService spotifyService, IYoutubeService youtubeService, IUserService userService, IVoteService voteService) {
    this.songService = songService;
    this.iTunesService = iTunesService;
    this.spotifyService = spotifyService;
    this.youtubeService = youtubeService;
    this.userService = userService;
    this.voteService = voteService;
  }

  @GetMapping(params = "id")
  public SongDto findOne(@RequestParam long id) {
    SongDto song;
    Optional<SongSimplified> optSimpleSong = songService.findById(id);

    if (optSimpleSong.isPresent()) {
      song = convertToDto(optSimpleSong.get());
    }
    else {
      Optional<iTunesSong> optiSong = iTunesService.findById(id);

      if (optiSong.isPresent()) {
        song = convertToDto(optiSong.get());
        songService.save(convertToEntity(song));
      }
      else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }

    if (SecurityContextHolder.getContext().getAuthentication() instanceof KeycloakAuthenticationToken) {
      KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
      User user = userService.findOrCreateById(authenticationToken.getAccount().getKeycloakSecurityContext().getToken().getSubject());
      user.setLastSearchedSongId(convertToEntity(song));
      userService.save(user);
    }

    return song;
  }

  @RequestMapping(value = "vote", params = "id")
  @RolesAllowed({"user", "admin"})
  public StatusDto<Long> vote(@RequestParam long id) {
    KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    StatusDto<Long> statusDto;

    User user = userService.findOrCreateById(authenticationToken.getAccount().getKeycloakSecurityContext().getToken().getSubject());

    SongSimplified song = convertToEntity(findOne(id));
    Iterable<Vote> voteIterable = voteService.findByUserAndSong(user, song);

    if (voteIterable.iterator().hasNext()) {
      voteService.deleteById(voteIterable.iterator().next().getId());
      statusDto = new StatusDto<>("Removed one vote", true, voteService.countBySong(song));
    }
    else {
      voteService.save(new Vote(user, song));
      statusDto = new StatusDto<>("Added one vote", true, voteService.countBySong(song));
    }

    return statusDto;
  }

  @GetMapping(value = "/top-voted", params = "limit")
  public Collection<SongDto> getTopVoted(@RequestParam int limit) {
    List<SongDto> songs = new LinkedList<>();

    voteService.findMostVotedSongs(limit).forEach(s -> {
      songs.add(findOne(s.getId()));
    });

    return songs;
  }

  @GetMapping(value = "/top-voted")
  public Collection<SongDto> getTopVoted() {
    return getTopVoted(5);
  }


  @GetMapping(params = "name")
  public Collection<SongDto> findByName(@RequestParam String query) {
    return findByName(query, 10);
  }

  @GetMapping(params = {"name", "limit"})
  public Collection<SongDto> findByName(@RequestParam String query, @RequestParam int limit) {
    List<SongDto> songs;
    Iterable<SongSimplified> simpleSongs;

    String[] split = query.split("by");

    if (split.length > 2) {
      split = new String[]{ split[0] + split[1], split[2] };
    }

    simpleSongs = songService.findByName(split[0]);

    if (simpleSongs.spliterator().estimateSize() >= limit) {
      songs = StreamSupport.stream(simpleSongs.spliterator(), false)
        .map(this::convertToDto)
        .collect(Collectors.toList());
    }
    else {
      // TODO maybe w subscriber, non-blocking - or find other blocking reason
      Results<iTunesSong> results = iTunesService.findByName(query, limit).block();

      if (results != null && results.getResults() != null && results.getResultCount() > 0) {
        songs = Arrays.stream(results.getResults())
          .map(i -> {
            SongDto dto = convertToDto(i);
            songService.save(convertToEntity(dto));
            return dto;
          })
          .collect(Collectors.toList());
      }
      else {
        songs = new LinkedList<>();
      }
    }

    return songs;
  }

  @GetMapping(value = "/new")
  public Collection<SongDto> findNewReleases() {
    List<SongDto> songs = new LinkedList<>();
    Iterable<String> songNameIterable = spotifyService.findNewReleases();

    songNameIterable.forEach(n -> {
      Iterable<SongDto> searchResults = findByName(n, 1);

      if (searchResults.iterator().hasNext()) {
        songs.add(searchResults.iterator().next());
      }
    });
    return songs;
  }

  protected SongDto convertToDto(SongSimplified simpleSong) {
    Optional<iTunesSong> optiSong = iTunesService.findById(simpleSong.getId());
    SongBuilder builder = new SongBuilder()
        .setId(simpleSong.getId())
        .setName(simpleSong.getName())
        .setSpotifyUrl(simpleSong.getSpotifyUrl())
        .setYoutubeUrl(simpleSong.getYoutubeUrl())
        .setVoteCount(voteService.countBySong(simpleSong));

    if (optiSong.isPresent()) {
      builder = buildToDto(builder, optiSong.get());
    }

    return builder.build();
  }

  protected SongDto convertToDto(iTunesSong iTunesSong) {
    SongBuilder builder = new SongBuilder();
    Optional<SongSimplified> optSimpleSong = songService.findById(iTunesSong.getTrackId());

    if (optSimpleSong.isPresent()) {
      SongSimplified simpleSong = optSimpleSong.get();
      builder.setSpotifyUrl(simpleSong.getSpotifyUrl());
      builder.setYoutubeUrl(simpleSong.getYoutubeUrl());
    }

    return buildToDto(builder, iTunesSong).build();
  }

  protected SongBuilder buildToDto(SongBuilder builder, iTunesSong iTunesSong) {

    return builder
      .setId(iTunesSong.getTrackId())
      .setName(iTunesSong.getTrackName())
      .setSpotifyUrl(spotifyService)
      .setYoutubeUrl(youtubeService)
      .setiTunesUrl(iTunesSong.getTrackViewUrl())
      .setCollectionName(iTunesSong.getCollectionName())
      .setPictureUrl(iTunesSong.getArtworkUrl100())
      .setArtist(iTunesSong.getArtistName())
      .setPreviewUrl(iTunesSong.getPreviewUrl())
      .setGenre(iTunesSong.getPrimaryGenreName())
      .setReleaseDate(iTunesSong.getReleaseDate());
  }

  /*protected SongSimplified convertToEntity(iTunesSong iTunesSong) {
    return new SongSimplifiedBuilder()
      .setId(iTunesSong.getTrackId())
      .setName(iTunesSong.getTrackName())
      .setiTunesUrl(iTunesSong.getTrackViewUrl())
      .build();
  }*/

  protected SongSimplified convertToEntity(SongDto dto) {
    return new SongSimplifiedBuilder()
      .setId(dto.getId())
      .setName(dto.getName())
      .setiTunesUrl(dto.getiTunesUrl())
      .setSpotifyUrl(dto.getSpotifyUrl())
      .setYoutubeUrl(dto.getYoutubeUrl())
      .build();
  }
}
