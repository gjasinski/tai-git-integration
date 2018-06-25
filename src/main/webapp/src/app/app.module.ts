import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {Router, RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login.component";
import {UserlistComponent} from "./userlist.component";
import {StatsComponent} from "./stats.component";

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'userlist'},
  {path: 'login', component: LoginComponent},
  {path: 'userlist', component: UserlistComponent},
  {path: 'stats', component: StatsComponent}
];

@NgModule({
  declarations: [
    AppComponent, LoginComponent, UserlistComponent, StatsComponent
  ],
  imports: [
    RouterModule.forRoot(routes), BrowserModule, HttpClientModule, NgbModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
