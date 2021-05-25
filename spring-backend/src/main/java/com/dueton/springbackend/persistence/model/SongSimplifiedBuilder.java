package com.dueton.springbackend.persistence.model;

public class SongSimplifiedBuilder {

    private Long id;
    private String name;

    private String spotifyUrl;
    private String iTunesUrl;
    private String youTubeUrl;

    public SongSimplifiedBuilder setId(Long id) {
      this.id = id;
      return this;
    }

    public SongSimplifiedBuilder setName(String name) {
      this.name = name;
      return this;
    }

    public SongSimplifiedBuilder setSpotifyUrl(String spotifyUrl) {
      this.spotifyUrl = spotifyUrl;
      return this;
    }

    public SongSimplifiedBuilder setiTunesUrl(String iTunesUrl) {
      this.iTunesUrl = iTunesUrl;
      return this;
    }

    public SongSimplifiedBuilder setYoutubeUrl(String youtubeUrl) {
      this.youTubeUrl = youtubeUrl;
      return this;
    }

    public SongSimplified build() {
        return new SongSimplified(id, name, spotifyUrl, iTunesUrl, youTubeUrl);
    }
}
