import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserService} from "./app.userService";
import {Router} from "@angular/router";
import {AuthService} from "./auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [UserService, AuthService, ]
})
export class AppComponent {

  title = 'GitHub Turbo Statistics Application';

  constructor(authService : AuthService, router: Router) {

    if (authService.authenticated == false) {
         // router.navigate(['login']);
    }
  }

}
