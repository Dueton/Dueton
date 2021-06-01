package com.dueton.springbackend.web.dto;

public class SongDto {

  private Long id;

  private String name;
  private Long voteCount;

  private String spotifyUrl;
  private String iTunesUrl;
  private String youtubeUrl;

  private String artist;
  private String collectionName;
  private String pictureUrl;
  private String previewUrl;
  private String genre;
  private String releaseDate;

  public SongDto(Long id) {
    this.id = id;
  }

  protected SongDto(Long id, String name, Long voteCount, String artist, String collectionName, String pictureUrl, String previewUrl, String genre, String releaseDate, String spotifyUrl, String iTunesUrl, String youtubeUrl) {
    this(id);
    this.name = name;
    this.voteCount = voteCount;
    this.artist = artist;
    this.collectionName = collectionName;
    this.pictureUrl = pictureUrl;
    this.previewUrl = previewUrl;
    this.genre = genre;
    this.releaseDate = releaseDate;
    this.spotifyUrl = spotifyUrl;
    this.iTunesUrl = iTunesUrl;
    this.youtubeUrl = youtubeUrl;
  }

  protected SongDto() { }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getCollectionName() {
    return collectionName;
  }

  public void setCollectionName(String collectionName) {
    this.collectionName = collectionName;
  }

  public String getPictureUrl() {
    return pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }

  public String getPreviewUrl() {
    return previewUrl;
  }

  public void setPreviewUrl(String previewUrl) {
    this.previewUrl = previewUrl;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public Long getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(Long voteCount) {
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


  public String getYoutubeUrl() {
    return youtubeUrl;
  }

  public void setYoutubeUrl(String youtubeUrl) {
    this.youtubeUrl = youtubeUrl;
  }
}
