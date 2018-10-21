import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {first} from 'rxjs/operators';
import {Router} from '@angular/router';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;

  error: boolean = false;

  constructor(
    private router: Router,
    private authService: AuthService,
    private userService: UserService
  ) {}

  ngOnInit() {
  }

  onSubmit() {
    this.authService.login(this.username, this.password).pipe(first()).subscribe(user => {
      if (user) {
        this.userService.getLoggedUser(user.username)
          .pipe()
          .subscribe(data => {
            this.userService.assignLoggedUser(data);
            this.router.navigate(['tasks']);
            this.error = false;
          });
      }
    }, err => {
      if (err.status === 401) {
        this.error = true;
      }
    });
    // this.authService.login(this.email, this.password)
    //   .pipe(first())
    //   .subscribe(data => {
    //     this.router.navigate(['classifications']);
    //   },
    //     error => {
    //     console.log('Erro ao realizar login');
    //     });

  }

}
