import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl = "http://localhost:8098/customer/login";
 
  constructor(private httpClient: HttpClient) { }

  login(newUser: Object): Observable<any> {
    return this.httpClient.post(`${this.loginUrl}/`,newUser,{responseType:'text'});
  }

}
