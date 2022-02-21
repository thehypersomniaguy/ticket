import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddTicketComponent } from './components/add-ticket/add-ticket.component';
import { TicketsListComponent} from './components/tickets-list/tickets-list.component';
import { UpdateTicketComponent } from './components/update-ticket/update-ticket.component';
const routes: Routes = [
  { path: 'tickets', component:TicketsListComponent},
  {path:'update/:id',component:UpdateTicketComponent},
  {path:'addTicket',component:AddTicketComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
