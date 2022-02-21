package com.TicketRaisingSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@Entity
@Table(name = "employee")
public class Employee {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "employee_id")
	 @PrimaryKeyJoinColumn
	    int employeeId;
	    
	@Column(name = "employee_name", table = "employee")
    String employeeName;

    @Column(name = "employee_sal", table = "employee")
    double employeeSal;
    
    @Column(name = "dept_num", table = "employee")
    int deptNum;
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public double getEmployeeSal() {
		return employeeSal;
	}
	public void setEmployeeSal(double employeeSal) {
		this.employeeSal = employeeSal;
	}
	public int getDeptNum() {
		return deptNum;
	}
	public void setDeptNum(int deptNum) {
		this.deptNum = deptNum;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	

	
	public Employee() {
		
	}

}
