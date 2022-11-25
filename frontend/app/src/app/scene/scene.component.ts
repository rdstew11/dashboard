import { Component, OnInit } from '@angular/core';
import { WebGLService } from './services/web-gl.service';

@Component({
  selector: 'app-scene',
  templateUrl: './scene.component.html',
  styleUrls: ['./scene.component.css']
})
export class SceneComponent implements OnInit {

  constructor(private webglService: WebGLService) { }

  ngOnInit(): void {
  }

}
