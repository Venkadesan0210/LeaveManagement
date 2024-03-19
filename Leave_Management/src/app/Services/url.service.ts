import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class UrlService {

  constructor(private http:HttpClient) { }

  sendRegisterDetails(data:any){
    return this.http.post("http://localhost:8088/user/save",data)
  }

  sendLoginDetails(data:any){
    return this.http.post("http://localhost:8088/user/login",data)
  }
}
  