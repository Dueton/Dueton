# Todo

## Final Steps

- [ ] Spotify - add to playlist
- [ ] (Login design)
- [ ] (show lastSearchedSong as soon as search field active)
- [x] fix Log In capital I
- [x] account page
  - [x] See infos
  - [x] evtl. change data?
  - [x] design
- [x] Fix search results
- [x] Top voted
  - [x] backend
  - [x] angular module

## Backend (Java Spring)

- [x] Make song DTO object
  - [x] Restructure --> getting and managing full song-data is a task for the SongController
- [x] Make request to iTunes faster
- [x] Optimize loading times
- [x] DB
  - [x] Switch to MariaDB
  - [x] Table Songs: ID (prob iTunes id), name, vote-count

## Frontend (Angular)

- [x] Make 404 page
  - [x] [Stack-Overflow](<https://stackoverflow.com/questions/56163117/how-to-set-up-a-route-for-custom-404-page-in-angular-project>)
  - [x] Explicit routes required here

```bash
[eli@Eli app]$ grep -rnw . -e "*ngIf"
./new-songs/new-songs.component.html:1:<div *ngIf="songs">
./song-detail/song-detail.component.html:1:<div *ngIf="song" class="songdetails">
```

- [x] Mobile Design
  - [x] Navbar
    - [x] Dropdown for mobile
    - [x] Fix navbar in comparison
  - [x] Home
  - [x] Comparison
  - [x] Project Description
  - [x] 404 page
  - [x] Song Details
  - [x] New Releases
  - [x] Fix unnecessary vertical Scrollbars on mobile
    - [x] Fix table on the comparison site
    - [x] Fix navbar
  - [x] Improve mobile navbar

- [x] Fix unnecessary vertical Scrollbar on PC
- [x] Fix dark theme (comparison, new, white logos)
- [x] Change colors a bit ("grayer gray and brighter blue")
  - [x] Background
  - [x] Main-Color
- [x] For search still use iTunes API with Angular
  - [x] Fix random appearing CORS-Erros
- [x] For song details use Spring
