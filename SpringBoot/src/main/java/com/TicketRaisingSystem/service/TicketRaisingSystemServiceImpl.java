package com.TicketRaisingSystem.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.TicketRaisingSystem.entity.Employee;
import com.TicketRaisingSystem.entity.Ticket;
import com.TicketRaisingSystem.repo.EmployeeRepository;
import com.TicketRaisingSystem.repo.TicketRaisingSystemRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



@Service 
public class TicketRaisingSystemServiceImpl implements TicketRaisingSystemService {
	@Autowired
	private TicketRaisingSystemRepository ticketRaisingSystemRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public List<Ticket>findAll() {
		// TODO Auto-generated method stub
		return  this.ticketRaisingSystemRepository.findAll();
	}
	public TicketRaisingSystemServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	@Override
	@Transactional
	public Map<String, String> deleteById(int theId) {
		// TODO Auto-generated method stub
		this.ticketRaisingSystemRepository.deleteById(theId);
		HashMap<String,String>response = new HashMap<String,String>();
		response.put("status", "0");
		response.put("message", "Deleted student successfully");
		return response;
	}
	@Override
	public Map<String, String> addTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		ticket.setTicket_id(0);
		Ticket t=this.ticketRaisingSystemRepository.save(ticket);
		HashMap<String,String> response= new HashMap<String,String>();
		if(t!=null)
		{
			response.put("status","0");
			response.put("message", "added new ticket sucessfull");
		}
		else
		{
			response.put("status","1");
			response.put("message", "failed to added new ticket");
		}
			return response;
	}
	
	
	@Override
	public ResponseEntity<Ticket> updateTicket(Ticket ticket) {
		
		
		return ResponseEntity.ok(this.ticketRaisingSystemRepository.save(ticket));
	}
	
	@Override
	public Map<String, Object> findByResolved(int theResolved) {
		// TODO Auto-generated method stub
		List<Ticket> ticket  = this.ticketRaisingSystemRepository.findByResolved(theResolved);
		Map<String, Object> response = new HashMap<String,Object>();
		if(theResolved==1) {
			response.put("ticket",ticket);
		}
		else if(theResolved==0){
			response.put("ticket",ticket);
		}
		else {
			response.put("status","3");
			response.put("message", "failed to fetch ticket");
			response.put("error", "either give 0 or 1 as endpoint");
		}
		return response;
	}
	@Override
	public List<Ticket> resolvedon(String resolvedon) {
		// TODO Auto-generated method stub
List<Ticket> ticket  = this.ticketRaisingSystemRepository.resolvedon(resolvedon);
		
		return ticket;
	}
	@Override
	public Map<String, Object> employeesonDate(int raisedby, String raisedon) {
		// TODO Auto-generated method stub
		List<Ticket> ticket=this.ticketRaisingSystemRepository.findByRaisedbyAndRaisedon(raisedby,raisedon);
		Map<String, Object> response = new HashMap<String,Object>();
		if(raisedby>0&& raisedon!=null)
		{
			response.put("ticket", ticket);
		}
		else
		{
			response.put("status","1");
			response.put("message", "ticket not found on that date");
		}
		return response;
	}
	@Override
	public Map<String, Object> employeesonDate1(int resolvedby, String resolvedon) {
		// TODO Auto-generated method stub
		List<Ticket> ticket=this.ticketRaisingSystemRepository.findByResolvedbyAndResolvedon(resolvedby,resolvedon);
		Map<String, Object> response = new HashMap<String,Object>();
		if(resolvedby>0&& resolvedon!=null)
		{
			response.put("ticket", ticket);
		}
		else
		{
			response.put("status","1");
			response.put("message", "ticket not found on that date");
		}
		return response;
	}
	@Override
	public String generateReport() {
		try {
			String reportPath = "C:\\Users\\ybhar\\Documents\\workspace-spring-tool-suite-4-4.12.1.RELEASE\\TicketRaising\\src\\main\\resources\\";
		
		List<Ticket> ticket=this.ticketRaisingSystemRepository.findAll();

		JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "TICKET.jrxml");


		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(ticket);

		
		Map<String, Object> parameters = new HashMap<>();

		parameters.put("createdBy", "bharat");

		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
				jrBeanCollectionDataSource);

		
		JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "TICKET.pdf");
		JasperExportManager.exportReportToHtmlFile(jasperPrint, reportPath + "TICKET.html");

		System.out.println("Done");

		return "Report successfully generated @path= " + reportPath;

	} catch (Exception e) {
		e.printStackTrace();
		return e.getMessage();
	}
}
	@Override
	public String findByRaisedonBetween(String raisedon, String raisedon1) {
		try {
	String reportPath = "C:\\Users\\ybhar\\Documents\\workspace-spring-tool-suite-4-4.12.1.RELEASE\\TicketRaising\\src\\main\\resources\\";
	JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "TICKET.jrxml");


	

		List<Ticket> ticket  = this.ticketRaisingSystemRepository.findByRaisedonBetween(raisedon,raisedon1);

		Map<String, Object> parameters = new HashMap<String,Object>();
		if(raisedon!=null&&raisedon1!=null) {
			parameters.put("tickets",ticket);
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(ticket);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					jrBeanCollectionDataSource);

			
			JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "TICKETDATES.pdf");
			JasperExportManager.exportReportToHtmlFile(jasperPrint, reportPath + "TICKETDATES.html");
		}
		System.out.println("Done");
		return "Report successfully generated @path= " + reportPath;
	}catch (Exception e) {
		e.printStackTrace();
		return e.getMessage();
	}
		
}
	@Override
	public 	String findByDeptNum(int deptNum) {
		try {
			String reportPath = "C:\\Users\\ybhar\\Documents\\workspace-spring-tool-suite-4-4.12.1.RELEASE\\TicketRaising\\src\\main\\resources\\";
			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "Employee.jrxml");


			

				List<Employee> employee  = this.employeeRepository.findByDeptNum(deptNum);

				Map<String, Object> parameters = new HashMap<String,Object>();
				if(deptNum==2) {
					parameters.put("employees",employee);
					JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(employee);
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
							jrBeanCollectionDataSource);

					
					JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "ITEmployee.pdf");
					JasperExportManager.exportReportToHtmlFile(jasperPrint, reportPath + "ITEmployee.html");
					}
					else  {
						parameters.put("employees",employee);
					JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(employee);
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
							jrBeanCollectionDataSource);

					
					JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "NonITEmployee.pdf");
					JasperExportManager.exportReportToHtmlFile(jasperPrint, reportPath + "NonITEmployee.html");
				}
				System.out.println("Done");
				return "Report successfully generated @path= " + reportPath;
			}catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
	}
	@Override
	public Ticket findById(int ticket_id) {
		return this.ticketRaisingSystemRepository.findById(ticket_id);
	}
}


	
	
	

