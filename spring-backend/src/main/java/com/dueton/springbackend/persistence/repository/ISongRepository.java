package com.dueton.springbackend.persistence.repository;

import com.dueton.springbackend.persistence.model.SongSimplified;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ISongRepository extends PagingAndSortingRepository<SongSimplified, Long> {
  Iterable<SongSimplified> findByName(String name);
}
