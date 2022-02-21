import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ticket } from '../models/ticket.model';



@Injectable({
  providedIn: 'root'
})

export class TicketService {
  
  constructor(private http: HttpClient) { }
  
  private baseUrl = "http://localhost:8080/api/all"
  private baseUrl2 = "http://localhost:8080/api/report"
  getTickets(): Observable<any>{
    return this.http.get(`${this.baseUrl}`);
  }
  get(id: any): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
  create(data: any): Observable<any> {
    return this.http.post(this.baseUrl, data);
  }
  updateTicket(id:number,value:any):Observable<object>{
    return this.http.put(`${this.baseUrl}/${id}`,value)
    }
  deleteTicket(id:number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
  deleteAll(): Observable<any> {
    return this.http.delete(this.baseUrl);
  }
    exportToPdf():Observable<any>{
      return this.http.get(this.baseUrl2);
    }
      monthlyReport(raisedon:String,raisedon1:String):Observable<any>{
        return this.http.get(`${this.baseUrl2}/${raisedon}/${raisedon1}`);
   }
   itEmployeeReport(deptNum:number):Observable<any>{
    return this.http.get(`${this.baseUrl2}/${deptNum}`);
}

    }


