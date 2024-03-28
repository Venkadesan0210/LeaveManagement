import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { EmployeeComponent } from './employee/employee.component';
import { ManagerComponent } from './manager/manager.component';
import { EditComponent } from './edit/edit.component';
import { ApplyleaveComponent } from './applyleave/applyleave.component';
import { LeaverequestlistComponent } from './leaverequestlist/leaverequestlist.component';
import { EditrequestComponent } from './editrequest/editrequest.component';

const routes: Routes = [
  {path:'',component:LoginComponent,pathMatch:'full'},
  {path:'register',component:RegisterComponent},
  {path:'login',component:LoginComponent},
  {path:'home',component:HomeComponent},
  {path:'employee',component:EmployeeComponent},
  {path:'manager',component:ManagerComponent},
  {path:'edit',component:EditComponent},
  {path:'applyleave',component:ApplyleaveComponent},
  {path:'leaverequestlist',component:LeaverequestlistComponent},
  {path:'editrequest',component:EditrequestComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
