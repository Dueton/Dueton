package com.dueton.springbackend.service;

import com.dueton.springbackend.persistence.model.SongSimplified;

import java.util.Optional;

public interface ISongService {

  Optional<SongSimplified> findById(Long id);

  Iterable<SongSimplified> findByName(String name);

  SongSimplified save(SongSimplified song);

  Iterable<SongSimplified> findTopVoted();

}
