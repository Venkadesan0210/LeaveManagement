import { Component } from '@angular/core';
import { UrlService } from '../Services/url.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor(private urlService:UrlService,private router: Router) {}
  navigateToRegisterPage() {
    this.router.navigate(['/register']); // Navigate to the register page
  }

}
