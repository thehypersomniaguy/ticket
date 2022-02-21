package com.TicketRaisingSystem.service;



import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;

import com.TicketRaisingSystem.entity.Employee;
import com.TicketRaisingSystem.entity.Ticket;

import net.sf.jasperreports.engine.JRException;

public interface TicketRaisingSystemService {
	
	@Transactional
	public Map<String, String> deleteById(int theId);
	@Transactional
	public Map<String,String> addTicket(Ticket ticket);

	
	public Map<String, Object> findByResolved(int theResolved);
	public List<Ticket> resolvedon(String resolvedon);
	public Map<String, Object> employeesonDate(int raisedby, String raisedon);
	public Map<String, Object> employeesonDate1(int resolvedby, String resolvedon);
	public String generateReport();
	List<Ticket> findAll();
	String findByRaisedonBetween(String raisedon, String raisedon1);
	public String findByDeptNum(int deptNum) ;
	public ResponseEntity<Ticket> updateTicket(Ticket ticket);
	public Ticket findById(int ticket_id);
	
	
}