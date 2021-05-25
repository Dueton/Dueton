package com.dueton.springbackend.web.controller;

import com.dueton.springbackend.persistence.model.SongSimplified;
import com.dueton.springbackend.persistence.model.SongSimplifiedBuilder;
import com.dueton.springbackend.service.ISongService;
import com.dueton.springbackend.service.ISpotifyService;
import com.dueton.springbackend.service.IYoutubeService;
import com.dueton.springbackend.service.IiTunesService;
import com.dueton.springbackend.service.responseEntities.Results;
import com.dueton.springbackend.service.responseEntities.iTunesSong;
import com.dueton.springbackend.web.dto.SongDto;
import com.dueton.springbackend.web.dto.SongBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/songs")
public class SongController {

  private final ISongService songService;
  private final IiTunesService iTunesService;
  private final ISpotifyService spotifyService;
  private final IYoutubeService youtubeService;

  public SongController(ISongService songService, IiTunesService iTunesService, ISpotifyService spotifyService, IYoutubeService youtubeService) {
    this.songService = songService;
    this.iTunesService = iTunesService;
    this.spotifyService = spotifyService;
    this.youtubeService = youtubeService;
  }

  @GetMapping(params = "id")
  public SongDto findOne(@RequestParam long id) {
    return songService.findById(id)
      .map(s -> iTunesService.findBySimplified(s)
        .map(this::convertToDto))
      .orElse(iTunesService.findById(id)
        .map(i -> {
          SongDto dto = convertToDto(i);
          songService.save(convertToEntity(dto));
          return dto;
        }))
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @GetMapping(params = "name")
  public Collection<SongDto> findByName(@RequestParam String name) {
    return findByName(name, 10);
  }

  @GetMapping(params = {"name", "limit"})
  public Collection<SongDto> findByName(@RequestParam String name, @RequestParam int limit) {
    List<SongDto> songs;
    Iterable<SongSimplified> simpleSongs = songService.findByName(name);

    if (simpleSongs.spliterator().estimateSize() >= limit) {
      songs = StreamSupport.stream(simpleSongs.spliterator(), false)
        .map(s -> iTunesService.findBySimplified(s)
          .map(this::convertToDto))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());
    }
    else {
      // TODO maybe w subscriber, non-blocking - or find other blocking reason
      Results<iTunesSong> results = iTunesService.findByName(name, limit).block();

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
    List<String> songNameList = new LinkedList<>();

    Iterable<String> songNameIterable = spotifyService.findNewReleases();
    Iterator<String> songNameIterator = songNameIterable.iterator();

    for (int i = 0; i < Math.min(5, songNameIterable.spliterator().estimateSize()); i++) {
      songNameList.add(songNameIterator.next());
    }

    songNameList.forEach(n -> {
      Iterable<SongDto> searchResults = findByName(n, 1);

      if (searchResults.iterator().hasNext()) {
        songs.add(searchResults.iterator().next());
      }
    });
    return songs;
  }

  protected SongDto convertToDto(iTunesSong iTunesSong) {
    return new SongBuilder()
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
      .setReleaseDate(iTunesSong.getReleaseDate())
      .build();
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
