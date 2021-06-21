package com.dueton.springbackend.service.impl;

import com.dueton.springbackend.persistence.model.SongSimplified;
import com.dueton.springbackend.persistence.repository.ISongRepository;
import com.dueton.springbackend.service.ISongService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SongServiceImpl implements ISongService {

  private final ISongRepository songRepository;

  public SongServiceImpl(ISongRepository songRepository) {
    this.songRepository = songRepository;
  }

  @Override
  public Optional<SongSimplified> findById(Long id) {
    return songRepository.findById(id);
  }

  @Override
  public Iterable<SongSimplified> findByName(String name) {
    return songRepository.findByName(name);
  }

  @Override
  public SongSimplified save(SongSimplified song) {
    songRepository.save(song);
    return song;
  }

  @Override
  public Iterable<SongSimplified> findTopVoted() {
    return songRepository.findAll(Sort.by(Sort.Direction.ASC, "voteCount"));
  }
}
