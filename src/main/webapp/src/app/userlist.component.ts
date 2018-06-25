import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserService} from "./app.userService";

@Component({
  selector: 'app-root',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.css'],
  providers: [UserService]
})
export class UserlistComponent {
  title = 'app';

  public users: any;
  public userLibraries: any;
  public currentUser: any;
  public userFollowers : any;

  public currentPage = 1;

  constructor(private _userService: UserService) {
    this.getUsers(this.currentPage);
  }

  getUserInfo(login) {
    this.currentUser = login;
    this.getUserLibraries(login);
    this.getUserFollowers(login);
  }

  getUserLibraries(login){
    this._userService.getUserLibraries(login)
      .subscribe(
        data => {
          this.userLibraries = data;
          console.log(data);
        },
        err => console.error(err),
        () => console.log('done loading libraries')
      );
  }

  getUserFollowers(login){
    this._userService.getUserFollowers(login)
      .subscribe(
        data => {
          this.userFollowers = data;
          console.log(data);
        },
        err => console.error(err),
        () => console.log('done loading followers')
      );
  }

  getUsers(step) {
    if (step == 1) this.currentPage++;
    else {
      if (this.currentPage > 1)
        this.currentPage--;
    }
    this._userService.getUsers(this.currentPage).subscribe(
      data => {
        this.users = data;
        console.log(data)
      },
      err => console.error(err),
      () => console.log('done loading foods')
    );
  }

}
