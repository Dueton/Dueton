import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';

import { Song } from './song';
import { ITunesResponse } from './interfaces/itunesresponse'
import { Result } from './interfaces/result'
import { Router } from '@angular/router';
import { StatusResponse } from './interfaces/statusResponse';

@Injectable({
  providedIn: 'root'
})
export class SongService {

  private iTunesApiUrl = "https://itunes.apple.com";
  private backendUrl = "https://localhost:8444/api/songs";

  constructor(
    private http: HttpClient,
    private router: Router,
    ) { }

  getSong(id: number): Observable<Song> {
    return this.http.get<Song>(`${this.backendUrl}?id=${id}`).pipe(
      catchError(e => {
        this.router.navigate(['error/notfound/song'])
        return this.handleError<Song>()
      })
    );
  }

  getNewReleases(): Observable<Song[]> {
    return this.http.get<Song[]>(`${this.backendUrl}/new`).pipe(
      catchError(e => {
        this.router.navigate(['error/notfound/newreleases'])
        return this.handleError<Song>()
      })
    );
  }

  getTopVoted(): Observable<Song[]> {
    return this.http.get<Song[]>(`${this.backendUrl}/top-voted`).pipe(
      catchError(e => {
        this.router.navigate(['error/notfound/top'])
        return this.handleError<Song>()
      })
    );
  }

  searchSongs(term: string, limit: number): Observable<ITunesResponse[]> {
    if (!term.trim()) {
      return of([]);
    }

    term = term.replace(/\s/gi , '+');

    return this.http.get<Result<ITunesResponse[]>>(`${this.iTunesApiUrl}/search?term=${term}&entity=song&limit=${limit}`).pipe(
      map((result) => result.results));
  }

  voteForSong(id: number): Observable<number> {
    return this.http.get<StatusResponse<number>>(`${this.backendUrl}/vote?id=${id}`).pipe(
      map((res) => res.value));
  }

  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {
      console.log(error);
      return of(result as T);
    }
  }
}
