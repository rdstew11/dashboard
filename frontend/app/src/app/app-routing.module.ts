import { NgModule } from '@angular/core';
import { SceneComponent } from "./scene/scene.component";
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [{path: "/help", component: SceneComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
