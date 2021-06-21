import { Component, OnInit } from '@angular/core';
import { Song } from '../song';
import { SongService } from '../song.service';

@Component({
  selector: 'app-top-voted',
  templateUrl: './top-voted.component.html',
  styleUrls: ['./top-voted.component.css']
})
export class TopVotedComponent implements OnInit {

  songs: Song[];

  constructor(
    private songService: SongService
  ) { }

  ngOnInit(): void {
    this.songService.getTopVoted().subscribe(s => this.songs = s);
  }

}
