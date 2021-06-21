package com.dueton.springbackend.web.controller;

import com.dueton.springbackend.persistence.model.User;
import com.dueton.springbackend.service.IUserService;
import com.dueton.springbackend.service.IVoteService;
import com.dueton.springbackend.web.dto.StatusDto;
import com.dueton.springbackend.web.dto.UserDto;
import com.dueton.springbackend.web.dto.UserDtoBuilder;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/user")
public class UserController {

  private final IUserService userService;
  private final IVoteService voteService;

  public UserController(IUserService userService, IVoteService voteService) {
    this.userService = userService;
    this.voteService = voteService;
  }

  @GetMapping("/info")
  @RolesAllowed({"user", "admin"})
  public UserDto getUserInfo() {
    return convertToDto(getToken());
  }

  @GetMapping(value = "/update", params = "username")
  @RolesAllowed({"user", "admin"})
  public void update(@RequestParam String username) {
    this.userService.updateUser(getToken().getSubject(), u -> {
      u.setUsername(username);
      return u;
    });
  }

  /*@GetMapping(value = "/update", params = {"username", "profilePictureUrl", "firstName", "lastName"})
  @RolesAllowed({"user", "admin"})
  public void update(@RequestParam String username, @RequestParam String profilePictureUrl, @RequestParam String firstName, @RequestParam String lastName) {
    this.userService.updateUser(getToken().getSubject(), u -> {
      u.setUsername(username);
      u.setFirstName(firstName);
      u.setLastName(lastName);
      return u;
    });
    userService.findOrCreateById(getToken().getSubject()).setProfilePictureUrl(profilePictureUrl);
  }*/

  @PostMapping(value = "/update")
  @RolesAllowed({"user", "admin"})
  public void update(@RequestBody UserDto dto) {
    this.userService.updateUser(getToken().getSubject(), u -> {
      u.setUsername(dto.getUsername());
      u.setFirstName(dto.getFirstName());
      u.setLastName(dto.getLastName());
      return u;
    });
    User user = userService.findOrCreateById(getToken().getSubject());
    user.setProfilePictureUrl(dto.getProfilePictureUrl());
    userService.save(user);
  }

  protected AccessToken getToken() {
    KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    return authenticationToken.getAccount().getKeycloakSecurityContext().getToken();
  }

  protected UserDto convertToDto(AccessToken token) {
    User userEntity = userService.findOrCreateById(token.getSubject());

    return new UserDtoBuilder()
      .setUserId(userEntity.getId())
      .setProfilePictureUrl((userEntity.getProfilePictureUrl() == null) ? "https://i.ibb.co/sW8bTKr/generic-user-icon-9.png" : userEntity.getProfilePictureUrl())
      .setFirstName(token.getGivenName())
      .setLastName(token.getFamilyName())
      .setLastSearchedSongId((userEntity.getLastSearchedSongId() == null) ? null : userEntity.getLastSearchedSongId().getId())
      .setUsername(token.getPreferredUsername())
      .setVoteCount(voteService.countByUser(userEntity))
      .build();
  }
}
