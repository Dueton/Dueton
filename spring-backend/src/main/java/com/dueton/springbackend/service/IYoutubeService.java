package com.dueton.springbackend.service;

import com.dueton.springbackend.persistence.model.SongSimplified;

public interface IYoutubeService {

  String getUrl(String searchQuery);

  String getUrl(SongSimplified simpleSong);
}
