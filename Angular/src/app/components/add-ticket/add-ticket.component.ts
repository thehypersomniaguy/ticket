import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TicketService } from 'src/app//services/ticket.service'
import { Ticket } from 'src/app/models/ticket.model';
@Component({
  selector: 'app-add-ticket',
  templateUrl: './add-ticket.component.html',
  styleUrls: ['./add-ticket.component.css']
})
export class AddTicketComponent implements OnInit {

  ticket: Ticket = new Ticket();
  submitted = false;
  dateTime!: Date;
  constructor(private ticketService: TicketService,
    private router: Router) { }

  ngOnInit() {
    this.dateTime=new Date;
  }

  newTicket(): void {
    this.submitted = false;
    this.ticket = new Ticket();
  }

  save() {
    this.ticketService
    .create(this.ticket).subscribe(data => {
      console.log(data)
      this.ticket = new Ticket();
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/all']);
  }
}