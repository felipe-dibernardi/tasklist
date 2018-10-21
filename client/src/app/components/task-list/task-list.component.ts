import {Component, OnInit} from '@angular/core';
import {User} from '../../models/user.model';
import {Task} from '../../models/task.model';
import {UserService} from '../../services/user.service';
import {TaskService} from '../../services/task.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  user: User;

  tasks: Task[];

  displayedColumns: string[] = ['remove', 'edit', 'action', 'title', 'status', 'creation', 'last-update', 'description'];

  constructor(
    private userService: UserService,
    private taskService: TaskService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.userService.loggedUser.subscribe(user => {
      this.user = user;
      if (user) {
        this.getTasks();
      }
    });
  }

  getTasks() {
    this.taskService.listByUser(this.user.id).pipe().subscribe(tasks => {
      this.tasks = tasks;
    });
  }

  remove(id: number) {
    this.taskService.remove(id).pipe()
      .subscribe(() => {
        this.getTasks();
        alert('Tarefa removida com sucesso');
      }, err => {
        alert(err.error.message);
      });
  }

  conclude(id: number) {
    this.taskService.conclude(id).pipe()
      .subscribe(task => {
        this.getTasks();
        alert('Tarefa concluída com sucesso.');
        this.router.navigate(['tasks']);
      }, err => {
        alert(err.error.message);
      });
  }

  restart(id: number) {
    this.taskService.restart(id).pipe()
      .subscribe(task => {
        this.getTasks();
        alert('Tarefa reiniciada com sucesso.');
        this.router.navigate(['tasks']);
      }, err => {
        alert(err.error.message);
      });
  }

  navigateToNewTask() {
    this.router.navigate(['task']);
  }

}
