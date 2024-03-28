import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';
import { HomeComponent } from './home/home.component'; 
import { ManagerComponent } from './manager/manager.component';
import { EmployeeComponent } from './employee/employee.component';
import { EditComponent } from './edit/edit.component';
import { ApplyleaveComponent } from './applyleave/applyleave.component';
import { LeaverequestlistComponent } from './leaverequestlist/leaverequestlist.component';
import { EditrequestComponent } from './editrequest/editrequest.component';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ManagerComponent,
    EmployeeComponent,
    EditComponent,
    ApplyleaveComponent,
    LeaverequestlistComponent,
    EditrequestComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot(),
    BsDatepickerModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
