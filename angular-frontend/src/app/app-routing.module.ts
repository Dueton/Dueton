import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MusicSearchComponent } from './music-search/music-search.component';
import { SongDetailComponent } from './song-detail/song-detail.component';
import { NewSongsComponent } from './new-songs/new-songs.component'
import { ComparisonComponent } from './comparison/comparison.component';
import { ProjectDescriptionComponent } from './project-description/project-description.component';

const routes: Routes = [
  { path: '', redirectTo: '/search', pathMatch: 'full'},
  { path: 'search', component: MusicSearchComponent },
  { path: 'song/:id', component: SongDetailComponent },
  { path: 'new', component: NewSongsComponent },
  { path: 'comparison', component: ComparisonComponent },
  { path: 'about', component: ProjectDescriptionComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
