import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EventComponent} from './event/event.component'

const routes: Routes = [
 /* {
    path: 'event',
    component: EventComponent,
    data: { title: 'List of Events' }
  },
  { path: '',
    redirectTo: '/event',
    pathMatch: 'full'
  }*/
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
