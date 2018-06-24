import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserService} from "./app.userService";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [UserService]
})
export class UserlistComponent {
  title = 'app';

  public users : any;

  constructor(private _userService: UserService) {
  }

  getUsers() {
    this._userService.getUsers().subscribe(
      data => {
        this.users = data
        console.log(data)
      },
      err => console.error(err),
      () => console.log('done loading foods')
    );
  }

}
