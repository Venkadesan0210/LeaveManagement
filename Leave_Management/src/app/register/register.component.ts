import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { UrlService } from '../Services/url.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent {
  username:string="";
  password:string="";
  role:string="";
  constructor(private urlService:UrlService,private router: Router){
  }
  sendData(){
    const registerData={
      username:this.username,
      password:this.password,
      role:this.role
    }
    this.urlService.sendRegisterDetails(registerData).subscribe({
      next:(response:any)=>{
        if(response!="")
        {
         // console.log(response);
          this.router.navigate(['/login']);
        }
      },
      error:(err:any)=>{
        console.log(err);
      }
    })
  }
}
