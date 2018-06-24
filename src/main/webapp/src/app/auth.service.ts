import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {ActivatedRoute} from "@angular/router";

function getWindow (): any {
  return window;
}

@Injectable()
export class AuthService {

  authenticated = false;

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute) {

    this.activatedRoute.queryParams.subscribe(params => {
      let code = params['code'];
      console.log(code); // Print the parameter to the console.

      let headers = new Headers();
      this.http.post('https://github.com/login/oauth/access_token?client_id=c75d32a090e3ca5820e1?client_secret' +
        '=    c3061958e641b9815f3689db7396c6b32e4961f4?code=' + code, {headers: headers})
        .subscribe((response: Response) =>{
        console.log (response.json());
      });

    });

  }

  static get nativeWindow(): any {
    return getWindow();
  }

  authenticate() {

    const headers = new HttpHeaders({
      content_type: 'text/html'
    });
    AuthService.nativeWindow.location.href = 'https://github.com/login/oauth/authorize?client_id=c75d32a090e3ca5820e1'
    // return this.http.get('https://github.com/login/oauth/authorize?client_id=c75d32a090e3ca5820e1',
    //   {headers: headers});

  }

}
