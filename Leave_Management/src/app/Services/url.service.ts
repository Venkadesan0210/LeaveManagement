import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UrlService {
  constructor(private http:HttpClient) { }
  sendRegisterDetails(data:any){
    return this.http.post("http://localhost:8040/registerNewUser",data)
  }

  sendLoginDetails(data:any){
    return this.http.post("http://localhost:8040/authenticate",data)
  }
  getAllDetails(data:any){
    return this.http.get("http://localhost:8040/allusers",data)
  }
  assigenedManager(data:any){
    return this.http.post("http://localhost:8040/update-user",data)
  }

  sendApplyLeaveDetails(data:any){
    return this.http.post("http://localhost:8040/apply-leave",data)
  }

  requestDataGet(data:any){
    return this.http.get("http://localhost:8040/userLeaves/"+data)
  }
  
  requestListDataGet(data:any){
    return this.http.post("http://localhost:8040/leaves-manager",data)
  }

  managerEmployeeList(data:any){
    return this.http.get("http://localhost:8040/assigneeByEmployee?assigneeByEmployee="+data)
  }
  statusChanges(data:any){
    return this.http.post("http://localhost:8040/update-leave",data)
  }

}