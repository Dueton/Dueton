import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Song } from './song';
import { ITunesResponse } from './interfaces/itunesresponse'
import { Result } from './interfaces/result'

@Injectable({
  providedIn: 'root'
})
export class SongService {

  private iTunesApiUrl = "https://itunes.apple.com/";

  constructor(private http: HttpClient,) { }

  getSong(id: number): Observable<Song> {
    return this.searchSongs(id+"", 1).pipe(
      map(songs => songs[0])
    )
  }

  searchSongs(term: string, limit: number): Observable<Song[]> {
    if (!term.trim()) {
      return of([]);
    }

    term = term.replace(/\s/gi , '+');

    return this.http.get<Result<ITunesResponse[]>>(`${this.iTunesApiUrl}/search?media=music&term=${term}&limit=${limit}`).pipe(
      map((result) => result.results.map((sp): Song => ({
      id: sp.trackId,
      name: sp.trackName,
      artist: sp.artistName,
      collectionName: sp.collectionName,
      pictureUrl: sp.artworkUrl100.replace(/100/gi, "512"),
      previewUrl: sp.previewUrl,
      genre: sp.primaryGenreName,
      releaseDate: new Date(sp.releaseDate)
    }))))
  }

  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {
      console.log(error);
      return of(result as T);
    }
  }
}
