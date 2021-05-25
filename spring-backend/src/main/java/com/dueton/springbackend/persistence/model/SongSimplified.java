package com.dueton.springbackend.persistence.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SongSimplified {

  @Id
  private Long id;

  private String name;
  private Long voteCount;

  private String spotifyUrl;
  private String iTunesUrl;
  private String youtubeUrl;

  protected SongSimplified(Long id, String name, String spotifyUrl, String iTunesUrl, String youtubeUrl) {
    this(id);
    this.name = name;
    this.spotifyUrl = spotifyUrl;
    this.iTunesUrl = iTunesUrl;
    this.youtubeUrl = youtubeUrl;
  }

  public SongSimplified(Long id) {
    this();
    this.id = id;
  }

  protected SongSimplified() {
    this.voteCount = 0L;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public long getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(long voteCount) {
    this.voteCount = voteCount;
  }

  public String getSpotifyUrl() {
    return spotifyUrl;
  }

  public void setSpotifyUrl(String spotifyUrl) {
    this.spotifyUrl = spotifyUrl;
  }

  public String getiTunesUrl() {
    return iTunesUrl;
  }

  public void setiTunesUrl(String iTunesUrl) {
    this.iTunesUrl = iTunesUrl;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getYoutubeUrl() {
    return youtubeUrl;
  }

  public void setYoutubeUrl(String youtubeUrl) {
    this.youtubeUrl = youtubeUrl;
  }
}
