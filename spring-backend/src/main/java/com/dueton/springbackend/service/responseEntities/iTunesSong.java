package com.dueton.springbackend.service.responseEntities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class iTunesSong {

  private long trackId;
  private String trackName;
  private String collectionName;
  private String artworkUrl100;
  private String artistName;
  private String trackViewUrl;
  private String previewUrl;
  private String primaryGenreName;
  private String releaseDate;


  public long getTrackId() {
    return trackId;
  }

  public void setTrackId(long trackId) {
    this.trackId = trackId;
  }

  public String getTrackName() {
    return trackName;
  }

  public void setTrackName(String trackName) {
    this.trackName = trackName;
  }

  public String getCollectionName() {
    return collectionName;
  }

  public void setCollectionName(String collectionName) {
    this.collectionName = collectionName;
  }

  public String getArtworkUrl100() {
    return artworkUrl100;
  }

  public void setArtworkUrl100(String artworkUrl100) {
    this.artworkUrl100 = artworkUrl100;
  }

  public String getArtistName() {
    return artistName;
  }

  public void setArtistName(String artistName) {
    this.artistName = artistName;
  }

  public String getTrackViewUrl() {
    return trackViewUrl;
  }

  public void setTrackViewUrl(String trackViewUrl) {
    this.trackViewUrl = trackViewUrl;
  }

  public String getPreviewUrl() {
    return previewUrl;
  }

  public void setPreviewUrl(String previewUrl) {
    this.previewUrl = previewUrl;
  }

  public String getPrimaryGenreName() {
    return primaryGenreName;
  }

  public void setPrimaryGenreName(String primaryGenreName) {
    this.primaryGenreName = primaryGenreName;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }
}
