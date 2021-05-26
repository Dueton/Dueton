export interface Song {
  id: number;
  name: string;
  voteCount: number;

  spotifyUrl: string;
  iTunesUrl: string;
  youtubeUrl: string;
  
  artist: string;
  collectionName: string;
  pictureUrl: string;
  previewUrl: string;
  genre: string;
  releaseDate: string;
}
