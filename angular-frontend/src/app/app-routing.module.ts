import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { NotFoundComponent } from './not-found/not-found.component';

import { MusicSearchComponent } from './music-search/music-search.component';
import { SongDetailComponent } from './song-detail/song-detail.component';
import { NewSongsComponent } from './new-songs/new-songs.component';
import { TopVotedComponent } from './top-voted/top-voted.component';
import { AccountSettingsComponent } from './account-settings/account-settings.component';

import { ComparisonComponent } from './comparison/comparison.component';
import { ProjectDescriptionComponent } from './project-description/project-description.component';

const routes: Routes = [
  { path: 'search', component: MusicSearchComponent },
  { path: 'song/:id', component: SongDetailComponent },
  { path: 'new', component: NewSongsComponent },
  { path: 'comparison', component: ComparisonComponent },
  { path: 'about', component: ProjectDescriptionComponent },
  { path: 'account/settings', component: AccountSettingsComponent },
  { path: 'topvoted', component: TopVotedComponent },
  { path: 'error/notfound/:message', component: NotFoundComponent },

  { path: '', redirectTo: '/search', pathMatch: 'full'},
  { path: '**', redirectTo: '/error/notfound/site', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
