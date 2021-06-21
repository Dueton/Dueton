import { Component, OnInit } from '@angular/core';
import { User } from '../interfaces/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  user: User;
  isLoggedIn: Boolean;
  
  constructor(
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.userService.isLoggedIn().subscribe(l => this.isLoggedIn = l);

    this.user = { username: "Log in", profilePictureUrl: "https://i.ibb.co/sW8bTKr/generic-user-icon-9.png",
    userId: null, lastSearchedSongId: null, lastName: null, firstName: null, voteCount: null };

    if (this.userService.isLoggedIn()) {
      this.userService.getUser().subscribe(u => this.user = u);
    }
  }

  openNavBar(): void {
    var nav = document.getElementById("topnav");
    if (nav.className === "nav") {
      nav.className += " responsive";
    } else {
      nav.className = "nav";
    }
  }

  login(): void {
    this.userService.login();
  }

  logout(): void {
    this.userService.logout();
  }
}
