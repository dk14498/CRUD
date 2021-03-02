package com.emplyee.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emplyee.crud.model.Employee;
import com.emplyee.crud.repository.EmployeeRepository;

@RestController

@RequestMapping("/test")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PutMapping("/update/{empId}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable int empId) {
        employee.setEmpId(empId);
        employeeRepository.save(employee);
        return employee;
    }
	@GetMapping("/get/{empId}")
	public Employee findEmployee ( @PathVariable int empId){
	    return employeeRepository.findEmployeeById(empId);
	 }

		/*
		 * @GetMapping("/{empName}") public List<Employee>
		 * findEployeeByName( @PathVariable String empName){ return
		 * employeeRepository.findEmployeeByName(empName); }
		 */
	@GetMapping
	public List listDepts(){
	    return employeeRepository.findAll();
	 }
	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
		return employee;
	}
	
	@DeleteMapping("/delete/{empId}")
	public String DeleteById( @PathVariable int empId){
		employeeRepository.deleteById(empId);
	    return "OK";
	 }
}
