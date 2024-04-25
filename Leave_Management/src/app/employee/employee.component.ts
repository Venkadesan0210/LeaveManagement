import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { UrlService } from '../Services/url.service';



@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent {
  status!: string;
  leavetype!: string;
  marriageLeave:string | null="";
  privilegeLeave:string| null="";
  medicalLeave:string|null="";
  paternityLeave:string|null="";
  tableData: any;
  leaveRequestId: string | null | undefined;
  employeeLeaveid: string | null | undefined;
  userNames: string | null | undefined;

  ngOnInit() {
     this.requestDataGet();
     const username = localStorage.getItem('userName');
       this.userNames = username;
  }
  constructor(private urlService:UrlService,private router: Router) {
  }

  requestDataGet(){
    this.employeeLeaveid=localStorage.getItem('employeeLeaveid');
    const requestDataGet=this.employeeLeaveid;
    this.urlService.requestDataGet(requestDataGet).subscribe({
      next: (response: any) => {
        if (response.length !== 0 ) {
          this.tableData = response;
          this.marriageLeave=response[0].employeeLeave.marriageLeave ;
          this.privilegeLeave=response[0].employeeLeave.privilegeLeave;
          this.medicalLeave=response[0].employeeLeave.medicalLeave;
          this.paternityLeave =response[0].employeeLeave.paternityLeave;
        }
        else{
        this.marriageLeave="3" ;
          this.privilegeLeave="10";
          this.medicalLeave="10";
          this.paternityLeave ="5";
          }

      },
      error: (error) => {
        console.error('Error:', error);
      }     
    }
    );
    

  }  
  navigateToApplyLeave() {
    this.router.navigate(['/applyleave']);
  }
  navigateToLogIn(){
    this.router.navigate(['/login']);
  }

}
