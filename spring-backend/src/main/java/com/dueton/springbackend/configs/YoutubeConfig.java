package com.dueton.springbackend.configs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
@PropertySource("classpath:application.yml")
public class YoutubeConfig {
  @Value("${youtube.api-key}")
  private String apiKey;

  @Value("${youtube.video-id-key}")
  private String videoIdKey;

  @Value("${youtube.video-url-template}")
  private String videoUrlTemplate;

  public String getApiKey() {
    return apiKey;
  }

  public String getVideoIdKey() {
    return videoIdKey;
  }

  public String getVideoUrlTemplate() {
    return videoUrlTemplate;
  }
}
