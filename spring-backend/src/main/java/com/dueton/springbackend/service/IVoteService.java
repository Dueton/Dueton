package com.dueton.springbackend.service;

import com.dueton.springbackend.persistence.model.SongSimplified;
import com.dueton.springbackend.persistence.model.User;
import com.dueton.springbackend.persistence.model.Vote;

import java.util.Optional;

public interface IVoteService {

  Optional<Vote> findById(Long id);

  Iterable<Vote> findByUser(User user);

  Iterable<Vote> findByUserAndSong(User user, SongSimplified songSimplified);

  Long countByUser(User user);

  Long countBySong(SongSimplified song);

  Iterable<SongSimplified> findMostVotedSongs(int limit);

  Iterable<SongSimplified> findMostVotedSongs();

  Vote save(Vote vote);

  void deleteById(Long id);
}
