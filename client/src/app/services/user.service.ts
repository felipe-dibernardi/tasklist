import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {User} from '../models/user.model';

@Injectable()
export class UserService {

  baseUrl = 'users';

  private userSource = new BehaviorSubject(null);
  loggedUser = this.userSource.asObservable();

  constructor(private http: HttpClient) {
    if (sessionStorage.getItem('currentUser')) {
      this.getLoggedUser(JSON.parse(sessionStorage.getItem('currentUser')).username)
        .pipe().subscribe(data => this.assignLoggedUser(data));
    }
  }

  getLoggedUser(username: string): Observable<any> {
    return this.http.get<any>(environment.baseUrl + this.baseUrl + '/' + username);
  }

  assignLoggedUser(user: any) {
    this.userSource.next(user);
  }

  insert(user: User): Observable<User> {
    return this.http.post<User>(environment.baseUrl + this.baseUrl + '/', user);
  }
}
