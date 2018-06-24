import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {Router, RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login.component";

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: ''},
  { path: 'login', component: LoginComponent}
];

@NgModule({
  declarations: [
    AppComponent, LoginComponent
  ],
  imports: [
    RouterModule.forRoot(routes), BrowserModule, HttpClientModule, NgbModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
