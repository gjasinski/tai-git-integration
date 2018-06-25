

import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class UserService {

  url = 'https://obscure-atoll-92583.herokuapp.com';

  constructor(private http:HttpClient) {}

  getUsers(currentPage: number) {
    return this.http.get(this.url + '/api/users?page=' + currentPage +'&limit=10');
  }

  getUserLibraries(login) {
    return this.http.get(this.url + '/api/users/libraries?login=' + login);
  }

  getUserFollowers(login) {
    return this.http.get(this.url + '/api/users/following?login=' + login);
  }

  getStats(currentPage: number) {
    return this.http.get(this.url + '/api/libraries?limit=10&page=' + currentPage + '&order=true' );
  }
}
