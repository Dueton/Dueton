package com.dueton.springbackend.service.impl;

import com.dueton.springbackend.persistence.model.SongSimplified;
import com.dueton.springbackend.service.IiTunesService;
import com.dueton.springbackend.service.responseEntities.Results;
import com.dueton.springbackend.service.responseEntities.iTunesSong;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class iTunesServiceImpl implements IiTunesService {

  private final WebClient webClient;

  public iTunesServiceImpl() {
    webClient = WebClient.builder()
      .baseUrl("https://itunes.apple.com")
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .filter(contentTypeInterceptor())
      .build();
  }

  @Override
  public Optional<iTunesSong> findById(Long id) {
    Results<iTunesSong> results = findByName(id.toString(), 1).block();
    return (results == null || results.getResults() == null || results.getResultCount() < 1) ? Optional.empty() : Optional.of(results.getResults()[0]);
  }

  @Override
  public Mono<Results<iTunesSong>> findByName(String name) {
    return findByName(name, 10);
  }

  @Override
  public Mono<Results<iTunesSong>> findByName(String name, int limit) {
    name = name.replaceAll(" ", "+");

    return webClient.get()
      .uri("search?media=music&term=" + name + "&limit=" + limit)
      .retrieve()
      .bodyToMono(new ParameterizedTypeReference<>() { });
  }

  @Override
  public Optional<iTunesSong> findBySimplified(SongSimplified simpleSong) {
    if (simpleSong.getId() != null) {
      return findById(simpleSong.getId());
    }
    else if (simpleSong.getName() != null && !simpleSong.getName().isBlank()) {
      Results<iTunesSong> results = findByName(simpleSong.getName(), 1).block();
      return (results == null || results.getResults() == null || results.getResultCount() < 1) ? Optional.empty() : Optional.of(results.getResults()[0]);
    }
    else {
      return Optional.empty();
    }
  }

  private ExchangeFilterFunction contentTypeInterceptor() {
    return ExchangeFilterFunction.ofResponseProcessor(clientResponse ->
      Mono.just(clientResponse.mutate()
        .headers(headers -> headers.remove(HttpHeaders.CONTENT_TYPE))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()));
  }
}
