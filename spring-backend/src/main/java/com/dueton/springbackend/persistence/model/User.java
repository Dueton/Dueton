package com.dueton.springbackend.persistence.model;

import javax.persistence.*;

@Entity
public class User {

  @Id
  @Column(name="user_id")
  private String id;

  @ManyToOne
  @JoinColumn(name="user_last_searched_song_id", referencedColumnName = "song_id")
  private SongSimplified lastSearchedSongId;

  @Column(name = "user_picture_url", columnDefinition = "varchar(255) default 'https://i.ibb.co/sW8bTKr/generic-user-icon-9.png'")
  private String profilePictureUrl;

  public User(String id) {
    this.id = id;
  }

  protected User() { }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SongSimplified getLastSearchedSongId() {
    return lastSearchedSongId;
  }

  public void setLastSearchedSongId(SongSimplified lastSearchedSongId) {
    this.lastSearchedSongId = lastSearchedSongId;
  }

  public String getProfilePictureUrl() {
    return profilePictureUrl;
  }

  public void setProfilePictureUrl(String profilePictureUrl) {
    this.profilePictureUrl = profilePictureUrl;
  }
}
