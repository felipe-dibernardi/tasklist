import { Component, OnInit } from '@angular/core';
import {User} from '../../models/user.model';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user: User;

  constructor(
    private userService: UserService,
    private router: Router) {
    this.user = new User();
    this.user.profile = 'ADMIN';
  }

  ngOnInit() {
  }

  onSubmit() {
    this.userService.insert(this.user).pipe()
      .subscribe(user => {
        this.user = user;
        alert('Usuário cadastrado com sucesso');
        this.router.navigate(['']);
      }, err => {
          alert(err.error.message);
        });
  }

}
