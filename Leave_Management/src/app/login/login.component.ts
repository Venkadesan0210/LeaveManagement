import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UrlService } from '../Services/url.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})


export class LoginComponent {

  [x: string]: any;
  username: string = "";
  password: string = "";
  constructor(private urlService: UrlService, private router: Router, private toastr: ToastrService) { }
  navigateToRegisterPage() {
    this.router.navigate(['/register']);
  }

  loginpage() {
    const registerData = {
      userName: this.username,
      userPassword: this.password,
    }

    this.urlService.sendLoginDetails(registerData).subscribe({
      next: (response: any) => {
        localStorage.setItem('userRole', response.user.userRole);
        localStorage.setItem('userName', response.user.userName);
        localStorage.setItem('managername', response.user.userName);
        localStorage.setItem('firstname', response.user.firstname);
        setTimeout(() => {
          if (response.user.userRole == "ADMIN") {
            this.router.navigate(['/home']);
            this.toastr.success('Logged in successfully!', 'Success');
          }
          else if (response.user.userRole == "EMPLOYEE") {
            localStorage.setItem('employeeLeaveid',response.user.employeeLeave.id);
            this.router.navigate(['/employee']);
            this.toastr.success('Logged in successfully!', 'Success');
          }
          else if (response.user.userRole == "MANAGER") {
            this.router.navigate(['/manager']);
            this.toastr.success('Logged in successfully!', 'Success');
          }
          else {
            this.toastr.error('Error occurred while logging in. Please try again.', 'Error');
          }
        }, 1000)

      },
      error: (err: any) => {
        console.log(err);
        this.toastr.error('Error occurred while logging in. Please try again.', 'Error');
      }
    })
  }
  onSubmit() {

  }

}