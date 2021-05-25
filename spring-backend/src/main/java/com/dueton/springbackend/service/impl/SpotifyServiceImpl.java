package com.dueton.springbackend.service.impl;

import com.dueton.springbackend.configs.SpotifyConfig;
import com.dueton.springbackend.service.ISpotifyService;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpotifyServiceImpl implements ISpotifyService {

  private final SpotifyApi spotifyApi;
  private long spotifyTokenExpiryTimeStamp;

  public SpotifyServiceImpl(SpotifyConfig spotifyConfig) {
    spotifyApi = new SpotifyApi.Builder()
      .setClientId(spotifyConfig.getClientId())
      .setClientSecret(spotifyConfig.getClientSecret())
      .build();

    refreshToken();
  }

  @Override
  public SpotifyApi getSpotify() {
    refreshToken();
    return spotifyApi;
  }

  @Override
  public String getUrl(String name) {
    String url = "Spotify track not found";

    try {
      Track[] tracks = getSpotify().searchTracks(name).build().execute().getItems();

      if (tracks.length > 0) {
        url = tracks[0].getExternalUrls().get("spotify");
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Encountered an exception while trying to get the Spotify url for the track: " + name);
      e.printStackTrace();

      url = "An error occurred while trying to fetch the Spotify-link";
    }

    return url;
  }

  @Override
  public void refreshToken() {
    if (System.currentTimeMillis() > spotifyTokenExpiryTimeStamp) {
      forceRefreshToken();
    }
  }

  @Override
  public void forceRefreshToken() {
    try {
      ClientCredentials credentials = spotifyApi.clientCredentials().build().execute();
      spotifyApi.setAccessToken(credentials.getAccessToken());
      spotifyTokenExpiryTimeStamp = System.currentTimeMillis() + credentials.getExpiresIn() * 1000;
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Encountered an exception while trying to set a new access-token for Spotify. Maybe try checking your credentials." );
      e.printStackTrace();
    }
  }

  @Override
  public Iterable<String> findNewReleases() {
    List<String> songList = new LinkedList<>();

    try {
      AlbumSimplified[] res = getSpotify().getListOfNewReleases().build().execute().getItems();

      songList = Arrays.stream(res)
        .map(a -> a.getName() + " by " + a.getArtists()[0].getName())
        .collect(Collectors.toList());

    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Encountered an exception while trying to get the newest releases from Spotify: " );
      e.printStackTrace();
    }

    return songList;
  }
}
