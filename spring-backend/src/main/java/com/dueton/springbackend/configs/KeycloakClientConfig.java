package com.dueton.springbackend.configs;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS;

@Configuration
class KeycloakClientConfig {
  @Value("${keycloakapi.credentials.secret}")
  private String clientSecret;

  @Value("${keycloakapi.resource}")
  private String clientId;

  @Value("${keycloakapi.auth-server-url}")
  private String authUrl;

  @Value("${keycloakapi.realm}")
  private String realm;

  @Bean
  Keycloak keycloak() {
    return KeycloakBuilder.builder()
      .grantType(CLIENT_CREDENTIALS)
      .serverUrl(authUrl)
      .realm(realm)
      .clientId(clientId)
      .clientSecret(clientSecret)
      .build();
  }
}
