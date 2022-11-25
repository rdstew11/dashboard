import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConnectSpotifyButtonComponent } from './connect-spotify-button/connect-spotify-button.component';
import { SpotifyService } from './spotify.service';
import { SceneComponent } from './scene/scene.component'

@NgModule({
  declarations: [
    AppComponent,
    ConnectSpotifyButtonComponent,
    SceneComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [SpotifyService],
  bootstrap: [AppComponent]
})
export class AppModule { }
