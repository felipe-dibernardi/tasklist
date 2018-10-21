import { BrowserModule } from '@angular/platform-browser';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';

import {MatInputModule} from '@angular/material/input';
import {MatButtonModule, MatFormFieldModule, MatTableModule} from '@angular/material';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import {routes} from './app.routing';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { SignupComponent } from './components/signup/signup.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthInterceptor} from './interceptors/auth.interceptor';
import {AuthService} from './services/auth.service';
import {UserService} from './services/user.service';
import { TaskListComponent } from './components/task-list/task-list.component';
import {TaskService} from './services/task.service';
import { TaskComponent } from './components/task/task.component';
import { HeaderComponent } from './components/header/header.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    TaskListComponent,
    TaskComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    routes,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatTableModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    AuthService,
    TaskService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
