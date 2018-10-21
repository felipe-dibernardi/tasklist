import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    return this.http.post<any>(environment.baseUrl + 'users/authenticate', {'username': username, 'password': password})
      .pipe(map(user => {
        let resp;
        if (user) {
          const authdata = window.btoa(username + ':' + password);
          resp = {username: username, authdata: authdata};
          sessionStorage.setItem('currentUser', JSON.stringify(resp));
        }

        return resp;
      }));
  }

  logout() {
    sessionStorage.removeItem('currentUser');
  }

}
