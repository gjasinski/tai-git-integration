import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserService} from "./app.userService";

@Component({
  selector: 'app-root',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css'],
  providers: [UserService]
})
export class StatsComponent {


  public libraries: any;

  public currentPage = 1;

  constructor(private _userService: UserService) {
    this.getStats(this.currentPage);
  }

  getStats(step) {
    if (step == 1) this.currentPage++;
    else {
      if (this.currentPage > 1)
        this.currentPage--;
    }
    this._userService.getStats(this.currentPage).subscribe(
      data => {
        this.libraries = data;
        console.log(data)
      },
      err => console.error(err),
      () => console.log('done loading foods')
    );
  }

}
