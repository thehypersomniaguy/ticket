import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TicketsListComponent } from './components/tickets-list/tickets-list.component';
import { FormsModule } from '@angular/forms';
import {HttpClientModule}  from '@angular/common/http';
import { AddTicketComponent } from './components/add-ticket/add-ticket.component';
import { UpdateTicketComponent } from './components/update-ticket/update-ticket.component';
@NgModule({
  declarations: [
    AppComponent,
    TicketsListComponent,
    AddTicketComponent,
    UpdateTicketComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
