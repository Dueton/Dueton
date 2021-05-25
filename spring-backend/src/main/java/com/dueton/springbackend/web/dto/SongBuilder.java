package com.dueton.springbackend.web.dto;

import com.dueton.springbackend.persistence.model.SongSimplified;
import com.dueton.springbackend.service.ISpotifyService;
import com.dueton.springbackend.service.IYoutubeService;

public class SongBuilder {

    private Long id;
    private String name;

    private String spotifyUrl;
    private String iTunesUrl;
    private String youtubeUrl;

    private String artist;
    private String collectionName;
    private String pictureUrl;
    private String previewUrl;
    private String genre;
    private String releaseDate;

    public SongBuilder setFromSimple(SongSimplified simple) {
      this.id = simple.getId();
      this.iTunesUrl = simple.getiTunesUrl();
      this.spotifyUrl = simple.getSpotifyUrl();

      return this;
    }

    public SongBuilder setId(Long id) {
      this.id = id;
      return this;
    }

    public SongBuilder setName(String name) {
      this.name = name;
      return this;
    }

    public SongBuilder setSpotifyUrl(ISpotifyService spotifyService) {
      if ((spotifyUrl == null || spotifyUrl.isBlank()) && (name != null && !name.isBlank())) {
        setSpotifyUrl(spotifyService.getUrl(name));
      }

      return this;
    }

    public SongBuilder setYoutubeUrl(IYoutubeService youtubeService) {
      if ((youtubeUrl == null || youtubeUrl.isBlank()) && (name != null && !name.isBlank())) {
        setYoutubeUrl(youtubeService.getUrl(name));
      }

      return this;
    }

    public SongBuilder setSpotifyUrl(String spotifyUrl) {
      this.spotifyUrl = spotifyUrl;
      return this;
    }

    public SongBuilder setiTunesUrl(String iTunesUrl) {
      this.iTunesUrl = iTunesUrl;
      return this;
    }

    public SongBuilder setYoutubeUrl(String youtubeUrl) {
      this.youtubeUrl = youtubeUrl;
      return this;
    }

    public SongBuilder setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public SongBuilder setCollectionName(String collectionName) {
        this.collectionName = collectionName;
        return this;
    }

    public SongBuilder setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public SongBuilder setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
        return this;
    }

    public SongBuilder setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public SongBuilder setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public SongDto build() {
        return new SongDto(id, name, artist, collectionName, pictureUrl, previewUrl, genre, releaseDate, spotifyUrl, iTunesUrl, youtubeUrl);
    }
}
