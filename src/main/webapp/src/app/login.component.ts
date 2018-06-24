import { Component, OnInit } from '@angular/core';
// import { AuthService } from './app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {AuthService} from "./auth.service";

@Component({
  templateUrl: './login.component.html'
})
export class LoginComponent {

  credentials = {username: '', password: ''};

  constructor(private app: AuthService, private http: HttpClient, private router: Router) {
  }


  login() {
    this.app.authenticate();
  }

  // login() {
  //   this.app.authenticate(this.credentials, () => {
  //     this.router.navigateByUrl('/');
  //   });
  //   return false;
  // }

}
