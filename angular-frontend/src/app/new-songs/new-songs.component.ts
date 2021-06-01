import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Song } from '../song';
import { SongService } from '../song.service';

@Component({
  selector: 'app-new-songs',
  templateUrl: './new-songs.component.html',
  styleUrls: ['./new-songs.component.css']
})
export class NewSongsComponent implements OnInit {

  songs: Song[];

  constructor(private songService: SongService) { }

  ngOnInit(): void {
    this.songService.getNewReleases().subscribe(s => this.songs = s);
  }
}
