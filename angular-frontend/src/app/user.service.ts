import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { catchError, map, tap } from 'rxjs/operators';
import { from, Observable, of } from 'rxjs';

import { User } from './interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private backendUrl = "https://localhost:8444/api/user";

  private loggedIn: Boolean;

  constructor(
    private http: HttpClient,
    private keycloak: KeycloakService
  ) { 
    this.keycloak.isLoggedIn().then(l => {
      this.loggedIn = l;
    });
  }

  isLoggedInCached(): Boolean {
    return this.loggedIn;
  }

  isLoggedIn(): Observable<Boolean> {
    return from(this.keycloak.isLoggedIn());
  }

  login() {
    this.keycloak.login();
  }

  logout() {
    this.keycloak.logout();
  }

  getUser(): Observable<User> {
    return this.http.get<User>(`${this.backendUrl}/info`);
  }

  saveUser(user: User): Observable<any> {
    return this.http.post(`${this.backendUrl}/update`, user);
  }
}
