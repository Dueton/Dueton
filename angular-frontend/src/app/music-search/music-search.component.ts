import { Component, OnInit } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { ITunesResponse } from '../interfaces/itunesresponse';

import { SongService } from '../song.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-music-search',
  templateUrl: './music-search.component.html',
  styleUrls: ['./music-search.component.css']
})
export class MusicSearchComponent implements OnInit {

  songs$: Observable<ITunesResponse[]>;
  private searchTerms = new Subject<string>();

  constructor(private songService: SongService) { }

  ngOnInit(): void {
    this.songs$ = this.searchTerms.pipe(
      debounceTime(500),
      distinctUntilChanged(),
      switchMap((term: string) => this.songService.searchSongs(term, 3))
    );
  }

  search(term: string): void {
    this.searchTerms.next(term);
  }
}
