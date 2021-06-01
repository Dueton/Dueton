import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrls: ['./not-found.component.css']
})
export class NotFoundComponent implements OnInit {

  message: String;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    const msg = this.route.snapshot.paramMap.get('message');

    switch(msg) {
      case 'site':
        this.message = 'Sorry, this site doesn\'t exist!';
        break;

      case 'song':
        this.message = 'Sorry, this song doesn\'t exist!'
        break;

      case 'newreleases':
        this.message = 'Sorry, we couldn\'t find the newest releases.';
        break;

      default:
        this.message = 'Sorry, the resource couldn\'t be found!'
    }
  }
}
