import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { UrlService } from '../Services/url.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent {

  email:string="";
  firstname:string="";
  lastname:string="";
  password:string="";
  role:string="";
  userForm!: FormGroup;
  registeruserNames: string | null | undefined;
  ngOnInit() {
    this.userForm = this.fb.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/)]],
      role: ['', Validators.required]
    });

    const username = localStorage.getItem('userName');
      this.registeruserNames = username;
  }
  constructor(private urlService:UrlService,private router: Router,private toastr: ToastrService,private fb: FormBuilder){
  }
  sendData(){
    const registerData={
      userFirstName:this.firstname,
      userLastName:this.lastname,
      userName:this.email,
      userPassword:this.password,
      userRole:this.role
    }
    this.urlService.sendRegisterDetails(registerData).subscribe({
      next: (response: any) => {
        if (response !== "") {
          this.toastr.success('Registration successful', 'Success');
          this.router.navigate(['/home']);
        }
      },
      error: (error) => {
        this.toastr.error('Registration failed', 'Error');
        console.error('Error:', error);
      }
    });
}
onSubmit() {
  if (this.userForm.valid) {
    // Handle form submission
    console.log(this.userForm.value);
  }
  }
  logout(){
    this.router.navigate(['/home']);
  }
}
