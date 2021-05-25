package com.dueton.springbackend.service.impl;

import com.dueton.springbackend.configs.YoutubeConfig;
import com.dueton.springbackend.persistence.model.SongSimplified;
import com.dueton.springbackend.service.IYoutubeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class YoutubeServiceImpl implements IYoutubeService {

  private final YoutubeConfig config;
  private final WebClient webClient;

  public YoutubeServiceImpl(YoutubeConfig youtubeConfig) {
    this.config = youtubeConfig;

    webClient = WebClient.builder()
      .baseUrl("https://youtube.googleapis.com/youtube/v3/")
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .build();
  }

  @Override
  public String getUrl(String searchQuery) {
    String url = "No YouTube video found!";

    if (searchQuery == null || searchQuery.isBlank()) {
      return url;
    }

    searchQuery = searchQuery.replaceAll(" ", "+");

    String jsonResponse = webClient.get()
      .uri("search?part=id,snippet&type=video&videoEmbeddable=true&key=" + config.getApiKey() + "&q=" + searchQuery + "&maxResults=1")
      .retrieve()
      .bodyToMono(String.class)
      .block();

    if (jsonResponse != null && jsonResponse.contains(config.getVideoIdKey())) {
      List<String> idList = jsonResponse.lines()
        .filter(s -> s.contains(config.getVideoIdKey()))
        .map(s -> {
          String trimmed = s.trim();
          return trimmed.substring(config.getVideoIdKey().length(), trimmed.length() - 1);
        })
        .collect(Collectors.toList());

      url = config.getVideoUrlTemplate() + idList.get(0);
    }

    return url;
  }

  @Override
  public String getUrl(SongSimplified simpleSong) {
    return getUrl(simpleSong.getName());
  }
}
