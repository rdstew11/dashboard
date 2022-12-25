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
    if(this._context == null){
      alert('Failed to initialize WebGL context.')
      return
    }
    
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

  loadShader(type:number, source:string){
    var shader = this.gl.createShader(type);
    this.gl.shaderSource(shader, source);
    this.gl.compileShader(shader);

    if(!this.gl.getShaderParameter(shader, this.gl.COMPILE_STATUS)){
      alert('An error occured compiling the shaders: ' + this.gl.getShaderInfoLog(shader));
      return null;
    }
    return shader;
  }

  initializeShaderProgram(vsSource: string, fsSource: string){
    const vertexShader = this.loadShader(this.gl.VERTEX_SHADER, vsSource);
    const fragmentShader = this.loadShader(this.gl.FRAGMENT_SHADER, fsSource);
    const shaderProgram = this.gl.createProgram();
    this.gl.attachShader(shaderProgram, vertexShader);
    this.gl.attachShader(shaderProgram, fragmentShader);
    this.gl.linkProgram(shaderProgram);

    if(!this.gl.getProgramParameter(shaderProgram, this.gl.LINK_STATUS)){
      alert('Shader program failed to initialize: ' + this.gl.getProgramInfoLog(shaderProgram));
      return null;
    }

    return shaderProgram;
  }
}
