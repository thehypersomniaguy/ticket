import { Component, OnInit } from '@angular/core';
import { Ticket } from 'src/app/models/ticket.model';
import { TicketService } from 'src/app//services/ticket.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
@Component({
  selector: 'app-tickets-list',
  templateUrl: './tickets-list.component.html',
  styleUrls: ['./tickets-list.component.css']
})
export class TicketsListComponent implements OnInit {
  raisedon:any;
  raisedon1:any;
  deptNum:number=0;

  tickets!: Ticket[];

  constructor(private ticketService: TicketService,
    private router: Router) { }

    ngOnInit(): void {
      this.ticketService.getTickets().subscribe((data: Ticket[]) => {
        console.log(data);
        this.tickets = data;
      })
    }
  deleteTicket(id:number)  { 
    this.ticketService.deleteTicket(id).subscribe((data: Ticket[])=> {
      console.log(data);  
          this.tickets =data;  
          })
  
     }
     updateTicket(id: number){
      this.router.navigate(['update', id]);
    }
    exportToPdf(){
      this.ticketService.exportToPdf().subscribe((data: Ticket[])=> {
        console.log(data);  
            this.tickets =data;  
            })
    
}
monthlyReport(raisedon:String,raisedon1:String){
  this.ticketService.monthlyReport(raisedon,raisedon1).subscribe((data: Ticket[])=> {
    console.log(data);  
        this.tickets =data;  
        })
}
itEmployeeReport(deptNum:number){
  this.ticketService.itEmployeeReport(deptNum).subscribe((data: Ticket[])=> {
    console.log(data);  
        this.tickets =data;  
        })
}
    
       }


   