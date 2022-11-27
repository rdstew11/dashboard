import { Injectable, ElementRef } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WebGLService {
  private _context: RenderingContext;
  private get gl(): WebGLRenderingContext{
    return this._context as WebGLRenderingContext;
  }

  constructor() { }

  initializeWebGLContext(canvas: ElementRef<HTMLCanvasElement>){
    this._context = canvas.nativeElement.getContext("webgl") || canvas.nativeElement.getContext("experimental-webgl");
    this.setWebGLCanvasDimensions(canvas);
    this.initializeWebGLCanvas();
  }

  setWebGLCanvasDimensions(canvas: ElementRef<HTMLCanvasElement>){
    this.gl.canvas.width = canvas.nativeElement.clientWidth;
    this.gl.canvas.height = canvas.nativeElement.clientHeight;
  }

  initializeWebGLCanvas(){
    this.gl.clearColor(0,0,0,1.0);
    this.gl.enable(this.gl.DEPTH_TEST);
    this.gl.depthFunc(this.gl.LEQUAL);
    this.gl.clear(this.gl.COLOR_BUFFER_BIT|this.gl.DEPTH_BUFFER_BIT);
  }
}
