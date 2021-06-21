import { APP_INITIALIZER, NgModule, ResolvedReflectiveFactory } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { MusicSearchComponent } from './music-search/music-search.component';
import { SongDetailComponent } from './song-detail/song-detail.component';
import { ComparisonComponent } from './comparison/comparison.component';
import { ProjectDescriptionComponent } from './project-description/project-description.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NewSongsComponent } from './new-songs/new-songs.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { AccountSettingsComponent } from './account-settings/account-settings.component';
import { TopVotedComponent } from './top-voted/top-voted.component';

function initializeKeycloak(keycloak: KeycloakService) {
  return () => {
    return new Promise<void>(async (resolve, reject) => {
      try {
        await keycloak.init({
          config: {
            url: 'keycloak-url',
            realm: 'realm',
            clientId: 'client-id',
          },
          initOptions: {
            onLoad: 'check-sso',
            silentCheckSsoRedirectUri: window.location.origin + '/assets/silent-check-sso.html'
          },
          enableBearerInterceptor: true,
          bearerExcludedUrls: ['/assets', 'https://itunes.apple.com']
        })

        const keycloakAuth = keycloak.getKeycloakInstance();

        keycloakAuth.onTokenExpired = () => {
          if (keycloakAuth.refreshToken) {
            keycloakAuth.updateToken(360);
          }
          else {
            keycloakAuth.login();
          }
        }

        resolve();
      } catch (error) {
        reject(error);
      }
    });
  };

  /*return () => 
    keycloak.init({
      config: {
        url: 'https://keycloak.local:8443/auth',
        realm: 'Dueton',
        clientId: 'angular-frontend',
        /*url: 'http://localhost:8080/auth',
        realm: 'master',
        clientId: 'keycloak-angular',*\/
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri: window.location.origin + '/assets/silent-check-sso.html'
      },
      enableBearerInterceptor: true,
      bearerExcludedUrls: ['/assets', 'https://itunes.apple.com']
    });*/
}

@NgModule({
  declarations: [
    AppComponent,
    MusicSearchComponent,
    SongDetailComponent,
    ComparisonComponent,
    ProjectDescriptionComponent,
    NavbarComponent,
    NewSongsComponent,
    NotFoundComponent,
    AccountSettingsComponent,
    TopVotedComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    KeycloakAngularModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
