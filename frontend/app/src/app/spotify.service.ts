import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SpotifyService  {
  private backend_url:string;

  constructor(private http: HttpClient) { 
    this.backend_url = "http://localhost:8080/api/spotify";
  }

  connectToSpotify(){
      console.log("sending GET")
      console.log(this.backend_url + "/login")
      return this.http.get<String>(this.backend_url + "/login");
       
  }

  helloWorld(){
    return this.http.get<string>("http://localhost:8080/hello")
  }
}
