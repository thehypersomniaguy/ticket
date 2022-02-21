
package com.TicketRaisingSystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TicketRaisingSystem.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	List<Employee> findByDeptNum(int deptNum);
}
