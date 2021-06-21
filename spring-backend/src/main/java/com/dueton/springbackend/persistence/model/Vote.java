package com.dueton.springbackend.persistence.model;

import javax.persistence.*;

@Entity
public class Vote {

  @Id
  @Column(name = "vote_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name="vote_user_id", referencedColumnName = "user_id")
  private User userId;

  @ManyToOne
  @JoinColumn(name="vote_song_id", referencedColumnName = "song_id")
  private SongSimplified songId;

  public Vote(User userId, SongSimplified songId) {
    this.userId = userId;
    this.songId = songId;
  }

  protected Vote() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUserId() {
    return userId;
  }

  public void setUserId(User userId) {
    this.userId = userId;
  }

  public SongSimplified getSongId() {
    return songId;
  }

  public void setSongId(SongSimplified songId) {
    this.songId = songId;
  }
}
