import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


function getWindow (): any {
  return window;
}


@Injectable()
export class AuthService {

  authenticated = false;

  constructor(private http: HttpClient) {
  }

  get nativeWindow(): any {
    return getWindow();
  }

  // authenticate(credentials, callback) {
  //
  //   const headers = new HttpHeaders(credentials ? {
  //     authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
  //   } : {});
  //
  //   this.http.get('user', {headers: headers}).subscribe(response => {
  //     // if (response['name']) {
  //     //   this.authenticated = true;
  //     // } else {
  //     //   this.authenticated = false;
  //     // }
  //     return callback && callback();
  //   });
  //
  // }

  authenticate() {

    const headers = new HttpHeaders({
      content_type: 'text/html'
    });
    this.nativeWindow.location.href = 'https://github.com/login/oauth/authorize?client_id=c75d32a090e3ca5820e1'
    // return this.http.get('https://github.com/login/oauth/authorize?client_id=c75d32a090e3ca5820e1',
    //   {headers: headers});

  }

}
