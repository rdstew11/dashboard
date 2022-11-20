import { Component, OnInit } from '@angular/core';
import { SpotifyService } from '../spotify.service';

@Component({
  selector: 'connect-spotify-button',
  templateUrl: './connect-spotify-button.component.html',
  styleUrls: ['./connect-spotify-button.component.css'],
  providers: [ SpotifyService ]
})
export class ConnectSpotifyButtonComponent implements OnInit {
  private data : String;
  constructor() { this.data = ""; }

  ngOnInit(): void {

  }


}
