package com.TicketRaisingSystem.repo;






import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TicketRaisingSystem.entity.Employee;
import com.TicketRaisingSystem.entity.Ticket;

public interface TicketRaisingSystemRepository extends JpaRepository<Ticket,Integer>{

	List<Ticket> findByResolved(int theResolved);

	List<Ticket> resolvedon(String resolvedon);

	List<Ticket> findByRaisedbyAndRaisedon(int raisedby, String raisedon);

	List<Ticket> findByResolvedbyAndResolvedon(int resolvedby, String resolvedon);

	List<Ticket> findByRaisedonBetween(String raisedon, String raisedon1);
	public Ticket findById(int ticket_id);








}
