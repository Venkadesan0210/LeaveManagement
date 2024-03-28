import { Component } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { UrlService } from '../Services/url.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent {
tableData: any;
  managername: string | null | undefined;
  usersNames: string | null | undefined;
  constructor(private urlService:UrlService,private router: Router,private toastr: ToastrService){
  }
  ngOnInit(){
    this.getLeaveRequestList(); 
    const username = localStorage.getItem('userName');
    this.usersNames = username;
   }
  navigateToLogIn(){
    this.router.navigate(['/login']);
  }
  navigateToApplyLeave(){
    this.router.navigate(['/leaverequestlist']);
  }

  getLeaveRequestList(){
   this.managername=localStorage.getItem('managername');
    const requestListdata= this.managername;
    this.urlService.managerEmployeeList(requestListdata).subscribe({
      next: (response: any) => {
        if (response !== "") {
          this.tableData = response;
        }
      },
      error: (error) => {
        console.error('Error:', error);
      }
    });

  }
}
