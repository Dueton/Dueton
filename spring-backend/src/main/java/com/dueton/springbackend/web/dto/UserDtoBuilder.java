package com.dueton.springbackend.web.dto;

public class UserDtoBuilder {
    private String userId;
    private String username;
    private String profilePictureUrl;
    private String firstName;
    private String lastName;
    private Long lastSearchedSongId;
    private Long voteCount;

    public UserDtoBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public UserDtoBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserDtoBuilder setProfilePictureUrl(String profilePictureUrl) {
      this.profilePictureUrl = profilePictureUrl;
      return this;
    }

    public UserDtoBuilder setFirstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public UserDtoBuilder setLastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public UserDtoBuilder setLastSearchedSongId(Long lastSearchedSongId) {
        this.lastSearchedSongId = lastSearchedSongId;
        return this;
    }

    public UserDtoBuilder setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
        return this;
    }

    public UserDto build() {
        return new UserDto(userId, username, profilePictureUrl, firstName, lastName, lastSearchedSongId, voteCount);
    }
}
