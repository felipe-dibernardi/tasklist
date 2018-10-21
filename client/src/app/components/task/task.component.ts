import { Component, OnInit } from '@angular/core';
import {TaskService} from '../../services/task.service';
import {UserService} from '../../services/user.service';
import {User} from '../../models/user.model';
import {Task} from '../../models/task.model';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
s
  task: Task = new Task();

  constructor(
    private taskService: TaskService,
    private userService: UserService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.userService.loggedUser.subscribe(user => {
      if (user) {
        this.activatedRoute.params.subscribe(params => {
          if (params['id']) {
            this.taskService.find(params['id']).pipe().subscribe(task => {
              this.task = task;
            });
          } else {
            this.task = new Task();
            this.task.user = user;
          }
        });
      }
    });
  }

  save(task: Task) {
    if (!task.id) {
      this.taskService.insert(task).pipe()
        .subscribe(t => {
          this.task = t;
          alert('Tarefa inserida com sucesso.');
          this.router.navigate(['tasks']);
        }, err => {
          alert(err.error.message);
        });
    } else {
      this.taskService.update(task).pipe()
        .subscribe(t => {
          this.task = t;
          alert('Tarefa atualizada com sucesso.');
          this.router.navigate(['tasks']);
        }, err => {
          alert(err.error.message);
        });
    }
  }

  conclude(id: number) {
    this.taskService.conclude(id).pipe()
      .subscribe(task => {
        this.task = task;
        alert('Tarefa concluída com sucesso.');
        this.router.navigate(['tasks']);
      }, err => {
        alert(err.error.message);
      });
  }

  restart(id: number) {
    this.taskService.restart(id).pipe()
      .subscribe(task => {
        this.task = task;
        alert('Tarefa reiniciada com sucesso.');
        this.router.navigate(['tasks']);
      }, err => {
        alert(err.error.message);
      });
  }

  remove(id: number) {
    this.taskService.remove(id).pipe()
      .subscribe(() => {
        alert('Tarefa removida com sucesso');
        this.router.navigate(['tasks']);
      }, err => {
        alert(err.error.message);
      });
  }

}
