package com.dueton.springbackend.configs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
@PropertySource("classpath:application.yml")
public class SpotifyConfig {
  @Value("${spotify.client-id}")
  private String clientId;

  @Value("${spotify.client-secret}")
  private String clientSecret;

  public String getClientId() { return clientId; }
  public String getClientSecret() { return clientSecret; }
}
