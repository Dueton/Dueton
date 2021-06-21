package com.dueton.springbackend.web.dto;

public class UserDto {

  private final String userId;
  private final String username;
  private final String profilePictureUrl;

  private final String firstName;
  private final String lastName;

  private final Long lastSearchedSongId;
  private final Long voteCount;


  public UserDto(String userId, String username, String profilePictureUrl, String firstName, String lastName, Long lastSearchedSongId, Long voteCount) {
    this.userId = userId;
    this.username = username;
    this.profilePictureUrl = profilePictureUrl;

    this.firstName = firstName;
    this.lastName = lastName;

    this.lastSearchedSongId = lastSearchedSongId;
    this.voteCount = voteCount;
  }

  public String getUserId() {
    return userId;
  }

  public String getUsername() {
    return username;
  }

  public String getProfilePictureUrl() {
    return profilePictureUrl;
  }

  public Long getLastSearchedSongId() {
    return lastSearchedSongId;
  }

  public Long getVoteCount() {
    return voteCount;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
}
