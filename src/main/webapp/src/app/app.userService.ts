

import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class UserService {

  url = 'http://localhost:8080';

  constructor(private http:HttpClient) {}

  getUsers(currentPage: number) {
    return this.http.get('http://localhost:8080/api/users?page=' + currentPage +'&limit=6');
  }

  getUserLibraries(login) {
    return this.http.get('http://localhost:8080/api/users/libraries?login=' + login);
  }

  getUserFollowers(login) {
    return this.http.get('http://localhost:8080/api/users/following?login=' + login);
  }

}
