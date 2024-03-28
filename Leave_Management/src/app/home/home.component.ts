import { Component } from '@angular/core';
import { UrlService } from '../Services/url.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  userRole!: string | null;
  userName!: string | null;
  tableHeaders: string[] = [];
  tableData: any[] = [];
  filterData: any[] = [];
  selectedRole: string = "All";
  ifAllSelected: boolean = false;
  responseData: any;
  usersnames: any;

  constructor(private urlService: UrlService, private router: Router) { }

  ngOnInit() {
    const role = localStorage.getItem('userRole');
    const username = localStorage.getItem('userName');
    if (role && username) {
      this.userRole = role;
      this.userName = username;
    }
    this.getUserDetails();
  }
  navigateToRegisterPage() {
    this.router.navigate(['/register']); // Navigate to the register page
  }
  getUserDetails() {
    const registerData = {
      userName: this.userName,
      userRole: this.userRole,
    }

    this.urlService.getAllDetails(registerData).subscribe({
      next: (response: any) => {
        this.tableData = response;
        this.filterData = response;
        // Assuming response is an array of objects, use the first object's keys as table headers
        if (this.tableData.length > 0) {
          this.tableHeaders = Object.keys(this.tableData[0]);
        }
      },
      error: (error: any) => {
        console.error('Error fetching user details:', error);
      }
    });
  }

  filterDetails(event: any) {
    let role = event.target.value;
    if (role != 'All') {
      this.filterData = this.tableData.filter((item: any) => item.userRole == role);
    } else {
      this.filterData = this.tableData;
    }
  }

  editAssignee() {
    this.router.navigate(['/edit']);
  }
  logout(){
    this.router.navigate(['/login']);
  }
}
