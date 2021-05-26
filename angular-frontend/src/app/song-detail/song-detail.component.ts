import { Component, OnInit, SecurityContext } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

import { Song } from '../song';
import { SongService } from '../song.service';

@Component({
  selector: 'app-song-detail',
  templateUrl: './song-detail.component.html',
  styleUrls: ['./song-detail.component.css']
})

export class SongDetailComponent implements OnInit {

  song: Song;

  embedSrc: SafeResourceUrl;
  releaseDate: Date;

  constructor(
    private route: ActivatedRoute,
    private sanitizer: DomSanitizer,
    private songService: SongService
  ) { }

  ngOnInit(): void {
    this.getSong();
  }

  getSong(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.songService.getSong(id)
      .subscribe(song => {
        this.song = song;
        //this.embedSrc = this.sanitizer.sanitize(SecurityContext.URL, this.song.youtubeUrl.replace('https://www.youtube.com/watch?v=', 'https://www.youtube-nocookie.com/embed/'));
        this.embedSrc = this.sanitizer.bypassSecurityTrustResourceUrl(this.song.youtubeUrl.replace('https://www.youtube.com/watch?v=', 'https://www.youtube-nocookie.com/embed/'));
        this.releaseDate = new Date(this.song.releaseDate);
      });
  }
}
