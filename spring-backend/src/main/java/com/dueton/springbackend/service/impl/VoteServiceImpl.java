package com.dueton.springbackend.service.impl;

import com.dueton.springbackend.persistence.model.SongSimplified;
import com.dueton.springbackend.persistence.model.User;
import com.dueton.springbackend.persistence.model.Vote;
import com.dueton.springbackend.persistence.repository.IVoteRepository;
import com.dueton.springbackend.service.IVoteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteServiceImpl implements IVoteService {

  private final IVoteRepository voteRepository;

  public VoteServiceImpl(IVoteRepository voteRepository) {
    this.voteRepository = voteRepository;
  }

  @Override
  public Optional<Vote> findById(Long id) {
    return voteRepository.findById(id);
  }

  @Override
  public Iterable<Vote> findByUser(User user) {
    return voteRepository.findByUserId(user);
  }

  @Override
  public Iterable<Vote> findByUserAndSong(User user, SongSimplified song) {
    return voteRepository.findByUserIdAndSongId(user, song);
  }

  @Override
  public Long countByUser(User user) {
    return voteRepository.countByUserId(user);
  }

  @Override
  public Long countBySong(SongSimplified song) {
    return voteRepository.countBySongId(song);
  }

  @Override
  public Iterable<SongSimplified> findMostVotedSongs(int limit) {
    return voteRepository.findMostVoted(PageRequest.of(0, limit));
  }

  @Override
  public Iterable<SongSimplified> findMostVotedSongs() {
    return findMostVotedSongs(5);
  }

  @Override
  public Vote save(Vote vote) {
    return voteRepository.save(vote);
  }

  @Override
  public void deleteById(Long id) {
    voteRepository.deleteById(id);
  }
}
