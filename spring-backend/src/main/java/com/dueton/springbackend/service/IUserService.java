package com.dueton.springbackend.service;

import com.dueton.springbackend.persistence.model.User;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Optional;

public interface IUserService {

  Optional<User> findById(String id);

  User findOrCreateById(String id);

  User save(User user);

  void updateUser(String id, EditUserRep userRep);

  @FunctionalInterface
  interface EditUserRep {
    UserRepresentation editRepresentation(UserRepresentation userRepresentation);
  }
}
