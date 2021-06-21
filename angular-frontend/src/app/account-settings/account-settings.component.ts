import { Component, OnInit } from '@angular/core';

import { User } from '../interfaces/user';
import { ITunesResponse } from '../interfaces/itunesresponse';
import { UserService } from '../user.service';
import { SongService } from '../song.service';

@Component({
  selector: 'app-account-settings',
  templateUrl: './account-settings.component.html',
  styleUrls: ['./account-settings.component.css']
})
export class AccountSettingsComponent implements OnInit {

  user: User;
  lastSearchedSong: ITunesResponse;

  constructor(
    private userService: UserService,
    private songService: SongService
  ) { }

  ngOnInit(): void {
    if (this.userService.isLoggedIn) {
      this.userService.getUser().subscribe(u => {
        this.user = u;
        this.songService.searchSongs(u.lastSearchedSongId.toString(), 1).subscribe(s => this.lastSearchedSong = s[0]);
      });
    }
  }

  save(): void {
    this.userService.saveUser(this.user).subscribe();
  }
}
