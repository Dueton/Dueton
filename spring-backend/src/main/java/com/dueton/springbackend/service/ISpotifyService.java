package com.dueton.springbackend.service;

import com.wrapper.spotify.SpotifyApi;

public interface ISpotifyService {

  SpotifyApi getSpotify();

  String getUrl(String name);

  void refreshToken();

  void forceRefreshToken();

  Iterable<String> findNewReleases();
}
