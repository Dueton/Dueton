package com.dueton.springbackend.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SongSimplified {

  @Id
  @Column(name = "song_id")
  private Long id;

  @Column(name = "song_name")
  private String name;

  @Column(name = "song_spotify_url")
  private String spotifyUrl;

  @Column(name = "song_itunes_url")
  private String iTunesUrl;

  @Column(name = "song_youtube_url")
  private String youtubeUrl;

  protected SongSimplified(Long id, String name, String spotifyUrl, String iTunesUrl, String youtubeUrl) {
    this(id);
    this.name = name;
    this.spotifyUrl = spotifyUrl;
    this.iTunesUrl = iTunesUrl;
    this.youtubeUrl = youtubeUrl;
  }

  public SongSimplified(Long id) {
    this.id = id;
  }

  protected SongSimplified() { }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
