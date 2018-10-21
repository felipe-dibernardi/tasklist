import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {Task} from '../models/task.model';
import {Injectable} from '@angular/core';

@Injectable()
export class TaskService {

  baseUrl = 'tasks';

  constructor(
    private http: HttpClient
  ) {}

  listByUser(id: number): Observable<Task[]> {
    return this.http.get<Task[]>(environment.baseUrl + this.baseUrl + '/user/' + id);
  }

  find(id: number): Observable<Task> {
    return this.http.get<Task>(environment.baseUrl + this.baseUrl + '/' + id);
  }

  insert(task: Task): Observable<Task> {
    return this.http.post<Task>(environment.baseUrl + this.baseUrl, task);
  }

  update(task: Task): Observable<Task> {
    return this.http.put<Task>(environment.baseUrl + this.baseUrl, task);
  }

  conclude(id: number): Observable<Task> {
    return this.http.put<Task>(environment.baseUrl + this.baseUrl + '/conclude/' + id, null);
  }

  restart(id: number): Observable<Task> {
    return this.http.put<Task>(environment.baseUrl + this.baseUrl + '/restart/' + id, null);
  }

  remove(id: number): Observable<any> {
    return this.http.delete(environment.baseUrl + this.baseUrl + '/' + id);
  }
}
