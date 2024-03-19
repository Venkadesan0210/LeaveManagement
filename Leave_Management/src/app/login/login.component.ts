import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UrlService } from '../Services/url.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})


export class LoginComponent {
  username:string="";
  password:string="";
  constructor(private urlService:UrlService,private router: Router) {}
  navigateToRegisterPage() {
    this.router.navigate(['/register']); // Navigate to the register page
  }

  loginpage(){
    const registerData={
      username:this.username,
      password:this.password,
    }

    this.urlService.sendLoginDetails(registerData).subscribe({
      next:(response:any)=>{
        if(response="Login successful")
        {
         // console.log(response);
          this.router.navigate(['/home']);
        }
      },
      error:(err:any)=>{
        console.log(err);
      }
    })
  }

}
