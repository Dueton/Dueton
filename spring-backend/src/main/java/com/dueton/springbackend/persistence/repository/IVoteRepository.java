package com.dueton.springbackend.persistence.repository;

import com.dueton.springbackend.persistence.model.SongSimplified;
import com.dueton.springbackend.persistence.model.User;
import com.dueton.springbackend.persistence.model.Vote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IVoteRepository extends PagingAndSortingRepository<Vote, Long> {

  Iterable<Vote> findByUserId(User user);
  Iterable<Vote> findByUserIdAndSongId(User user, SongSimplified song);

  long countByUserId(User user);
  long countBySongId(SongSimplified song);

  //@Query(value = "SELECT DISTINCT vote_song_id FROM vote ORDER BY COUNT(vote_song_id) DESC LIMIT 5", nativeQuery = true)
  @Query(value = "SELECT DISTINCT e.songId " +
    "FROM Vote e " +
    "GROUP BY e.songId " +
    "ORDER BY COUNT(e.songId) DESC ")
  List<SongSimplified> findMostVoted(Pageable pageable);
}
