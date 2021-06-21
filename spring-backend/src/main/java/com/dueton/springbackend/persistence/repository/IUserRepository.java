package com.dueton.springbackend.persistence.repository;

import com.dueton.springbackend.persistence.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IUserRepository extends PagingAndSortingRepository<User, String> {
}
