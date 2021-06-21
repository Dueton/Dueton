package com.dueton.springbackend.service.impl;

import com.dueton.springbackend.persistence.model.User;
import com.dueton.springbackend.persistence.repository.IUserRepository;
import com.dueton.springbackend.service.IUserService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

  private final IUserRepository userRepository;
  private final Keycloak keycloak;

  @Value("${keycloakapi.realm}")
  private String realm;

  public UserServiceImpl(IUserRepository userRepository, Keycloak keycloak) {
    this.userRepository = userRepository;
    this.keycloak = keycloak;
  }


  @Override
  public Optional<User> findById(String id) {
    return userRepository.findById(id);
  }

  @Override
  public User findOrCreateById(String id) {
    User user;
    Optional<User> optUser = userRepository.findById(id);

    if (optUser.isPresent()) {
      user = optUser.get();
    }
    else {
      user = new User(id);
      save(user);
    }

    return user;
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public void updateUser(String id, EditUserRep userRep) {
    UserResource res = keycloak
      .realm(realm)
      .users()
      .get(id);

    //System.out.println(keycloak.realm(realm).users().search("eli", true).get(0).getId().equals(id));

    res.update(userRep.editRepresentation(res.toRepresentation()));
  }
}
