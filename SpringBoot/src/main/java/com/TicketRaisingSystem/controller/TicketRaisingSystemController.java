package com.TicketRaisingSystem.controller;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TicketRaisingSystem.entity.Ticket;
import com.TicketRaisingSystem.service.TicketRaisingSystemService;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class TicketRaisingSystemController {
	@Autowired
	private TicketRaisingSystemService ticketRaisingSystemService;
	
	@GetMapping("/all")
	public List<Ticket> fetchAllTicket()
	{
		return this.ticketRaisingSystemService.findAll();
	}
	@DeleteMapping("/all/{theId}")  
	public Map<String, String> deleteById(@PathVariable int theId){
		return this.ticketRaisingSystemService.deleteById(theId);
	}
	@PostMapping("/all")
	public Map<String,String> addTicket(@RequestBody Ticket ticket)
	{
		return this.ticketRaisingSystemService.addTicket(ticket);
		
	}
	@PutMapping("/all/{theId}")
	public ResponseEntity<Ticket> updateTickets(@PathVariable int theId,@RequestBody Ticket ticketDetails)  {
		Ticket ticket = ticketRaisingSystemService.findById(theId);

		ticket.setTicket_id(ticketDetails.getTicket_id());
		ticket.setRaisedby(ticketDetails.getRaisedby());
		ticket.setTypeOfIssue(ticketDetails.getTypeOfIssue());
		ticket.setRaisedon(ticketDetails.getRaisedon());
		ticket.setResolved(ticketDetails.getResolved());
		ticket.setResolvedby(ticketDetails.getResolvedby());
		ticket.setResolvedon(ticketDetails.getResolvedon());
		ticket.setPriority(ticketDetails.getPriority());
		
		return this.ticketRaisingSystemService.updateTicket(ticket);
		
	}
	
	
	
	@GetMapping("/all/{theId}")
	public ResponseEntity<Ticket> findById(@PathVariable int theId)
			 {
	 Ticket ticket = ticketRaisingSystemService.findById(theId);
		return ResponseEntity.ok().body(ticket);
	}
	@GetMapping("/resolve/{theResolved}")
	public Map<String, Object> findByResolved(@PathVariable int theResolved)
	{
		return this.ticketRaisingSystemService.findByResolved(theResolved);
		
		
	}
	@GetMapping("/resolvedon/{resolvedon}")
    public List<Ticket> resolvedon (@PathVariable Date resolvedon)
    {
    	return this.ticketRaisingSystemService.resolvedon(resolvedon);
    }
	
	@GetMapping("/employeeonDate/{raisedby}/{raisedon}")
	public Map<String,Object>employeesonDate(@PathVariable int raisedby,@PathVariable String raisedon)
	{
		return this.ticketRaisingSystemService.employeesonDate(raisedby, raisedon);
		
	}
	@GetMapping("/employeeonDate1/{resolvedby}/{resolvedon}")
	public Map<String,Object>employeesonDate1(@PathVariable int resolvedby,@PathVariable String resolvedon)
	{
		return this.ticketRaisingSystemService.employeesonDate1(resolvedby, resolvedon);
		
	}
	@GetMapping("/report")
	public String ticketReport() {

		return this.ticketRaisingSystemService.generateReport();
	}
	public TicketRaisingSystemController() {
		// TODO Auto-generated constructor stub
	}
	@GetMapping("/report/{raisedon}/{raisedon1}")
	public String findByRaisedonBetween(@PathVariable String raisedon,@PathVariable String raisedon1)  
	{
		return this.ticketRaisingSystemService.findByRaisedonBetween(raisedon, raisedon1);
	}
	@GetMapping("/report/{deptNum}")
	public String findByDeptNum(@PathVariable int deptNum) 
	{
		return this.ticketRaisingSystemService.findByDeptNum(deptNum);
	}


    
}
