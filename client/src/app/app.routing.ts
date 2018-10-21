import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {ModuleWithProviders} from '@angular/core';
import {SignupComponent} from './components/signup/signup.component';
import {AuthGuard} from './guards/auth.guard';
import {TaskListComponent} from './components/task-list/task-list.component';
import {TaskComponent} from './components/task/task.component';

export const AppRoutes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'signup',
    component: SignupComponent
  },
  {
    path: 'tasks',
    component: TaskListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'task/:id',
    component: TaskComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'task',
    component: TaskComponent,
    canActivate: [AuthGuard]
  }
];

export const routes: ModuleWithProviders = RouterModule.forRoot(AppRoutes);
