import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { MusicSearchComponent } from './music-search/music-search.component';
import { SongDetailComponent } from './song-detail/song-detail.component';
import { ComparisonComponent } from './comparison/comparison.component';
import { ProjectDescriptionComponent } from './project-description/project-description.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NewSongsComponent } from './new-songs/new-songs.component';
import { NotFoundComponent } from './not-found/not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    MusicSearchComponent,
    SongDetailComponent,
    ComparisonComponent,
    ProjectDescriptionComponent,
    NavbarComponent,
    NewSongsComponent,
    NotFoundComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
