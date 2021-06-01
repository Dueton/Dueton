# Todo

## Backend (Java Spring)

- [x] Make song DTO object
  - [x] Restructure --> getting and managing full song-data is a task for the SongController
- [ ] Make request to iTunes non-blocking
- [x] Optimize loading times
- [ ] DB
  - [x] Switch to MariaDB
  - [x] Table Songs: ID (prob iTunes id), name, vote-count
  - [ ] Table User: Spotify token/auth credentials

## Frontend (Angular)

- [ ] List new releases on landing page
- [ ] Make 404 page
  - [ ] [Stack-Overflow](<https://stackoverflow.com/questions/56163117/how-to-set-up-a-route-for-custom-404-page-in-angular-project>)
  - [ ] Explicit routes required here

```bash
[eli@Eli app]$ grep -rnw . -e "*ngIf"
./new-songs/new-songs.component.html:1:<div *ngIf="songs">
./song-detail/song-detail.component.html:1:<div *ngIf="song" class="songdetails">
```

- [x] For search still use iTunes API with Angular
- [x] For song details use Spring