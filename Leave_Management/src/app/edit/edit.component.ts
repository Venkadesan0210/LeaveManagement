import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { UrlService } from '../Services/url.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent {
UserEmail: any;
ManagerEmail: any;
userForm!: FormGroup;
constructor(private urlService:UrlService,private router: Router,private toastr: ToastrService,private fb: FormBuilder ) {}
ngOnInit() {
  this.userForm = this.fb.group({
    useremail: ['', [Validators.required, Validators.email]],
    manageremail: ['', [Validators.required, Validators.email]]
  });
}

  sendData(){

    const assignedManger = {
      managerUserName: this.ManagerEmail,
      userName: this.UserEmail,
    }

    this.urlService.assigenedManager(assignedManger).subscribe({
      next: (response: any) => {
        this.router.navigate(['/home']);

      },
      error: (error: any) => {
        console.error('Error fetching user details:', error);
      }
    });
  }
  onSubmit(){
    if (this.userForm.valid) {
      console.log(this.userForm.value);
    }
  }
  logout(){
    this.router.navigate(['/home']);

  }
}
