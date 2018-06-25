import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ActivatedRoute, Router} from "@angular/router";

function getWindow(): any {
  return window;
}

@Injectable()
export class AuthService {

  authenticated = false;

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute, private router: Router) {

    this.activatedRoute.queryParams.subscribe(params => {

      let code = params['code'];
      console.log(code); // Print the parameter to the console.

      if (code != undefined) {
        const body = {
          client_id: 'c75d32a090e3ca5820e1',
          client_secret: 'c3061958e641b9815f3689db7396c6b32e4961f4',
          code: code
        };
        let x = new XMLHttpRequest();
        x.open('POST', ' https://cryptic-headland-94862.herokuapp.com/https://github.com/login/oauth/access_token', true);
        x.setRequestHeader("X-Requested-With", "XMLHttpRequest");
        x.onload = function() {
          console.log(x);
        };
        x.send((body));
        // this.http.post('https://cors-anywhere.herokuapp.com/https://github.com/login/oauth/access_token', body)
        //   .subscribe((response: Response) => {
        //     console.log(response.json());
        //   });
      }
        if (this.authenticated == false) {
          //router.navigate(['login']);
        }

      //https://cryptic-headland-94862.herokuapp.com/https://github.com/login/oauth/access_token
    });

  }

  static get nativeWindow(): any {
    return getWindow();
  }

  static authenticate() {
    AuthService.nativeWindow.location.href = 'https://github.com/login/oauth/authorize?client_id=c75d32a090e3ca5820e1'
  }

}
