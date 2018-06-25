import { Component, OnInit } from '@angular/core';
// import { AuthService } from './app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {AuthService} from "./auth.service";

@Component({
  templateUrl: './login.component.html'
})
export class LoginComponent {

  constructor(private app: AuthService, private http: HttpClient, private router: Router) {
  }

  login() {
    AuthService.authenticate();
  }

}
