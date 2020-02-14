package com.employees.crud.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employees.crud.entity.Employee;
import com.employees.crud.service.EmployeeService;


@RestController
@RequestMapping("/repo")
public class EmployeeRepoRestController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeRepoRestController(@Qualifier("employeeServiceJpaImpl") EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{theId}")
	public Employee findById(@PathVariable int theId) {
		
		Employee theEmployee = employeeService.findById(theId);
		if(theEmployee == null)
			throw new RuntimeException("Employee id not found - " + theId);
		return theEmployee;
	}
	
	@PostMapping("/employees")
	public Employee save(@RequestBody Employee theEmployee) {	
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;
	}
	
	@PutMapping("/employees")
	public Employee update(@RequestBody Employee theEmployee) {
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	@DeleteMapping("/employees/{theId}")
	public String deleteById(@PathVariable int theId) {
		Employee theEmployee = employeeService.findById(theId);
		if(theEmployee == null)
			throw new RuntimeException("Employee id not found - " + theId);
		
		employeeService.deleteById(theId);
		
		return "Deleted employee id - " + theId;
	}
	
	
	
}
