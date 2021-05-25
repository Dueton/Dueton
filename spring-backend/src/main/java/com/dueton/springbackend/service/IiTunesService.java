package com.dueton.springbackend.service;

import com.dueton.springbackend.persistence.model.SongSimplified;
import com.dueton.springbackend.service.responseEntities.Results;
import com.dueton.springbackend.service.responseEntities.iTunesSong;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface IiTunesService {

  Optional<iTunesSong> findById(Long id);

  Mono<Results<iTunesSong>> findByName(String name);

  Mono<Results<iTunesSong>> findByName(String name, int limit);

  Optional<iTunesSong> findBySimplified(SongSimplified simpleSong);
}
