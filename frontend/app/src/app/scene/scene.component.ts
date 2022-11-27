import { Component, OnInit, ViewChild, ElementRef, AfterViewInit} from '@angular/core';
import { WebGLService } from './services/web-gl.service';

@Component({
  selector: 'app-scene',
  templateUrl: './scene.component.html',
  styleUrls: ['./scene.component.css']
})
export class SceneComponent implements OnInit, AfterViewInit {
  @ViewChild('canvas')
  canvas: ElementRef<HTMLCanvasElement>;

  constructor(private webglService: WebGLService) { }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void{
    if(!this.canvas){
      alert("Canvas element not found - cannot initialize WebGL context.");
      return;
    }
    this.webglService.initializeWebGLContext(this.canvas)
  }

}
