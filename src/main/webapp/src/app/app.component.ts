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

  title = 'GitHub Statistics Application';

  constructor(private authService : AuthService, private router: Router) {

  }

  goToUsers(){
    this.router.navigate(['userlist'])
  }

  goToStats(){
    this.router.navigate(['stats'])
  }

}
