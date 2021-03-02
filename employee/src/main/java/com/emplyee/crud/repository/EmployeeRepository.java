package com.emplyee.crud.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.emplyee.crud.model.Employee;

@Repository
public class EmployeeRepository {
	
	

	    @Autowired
	    private MongoTemplate mongoTemplate;
       
	    public List<Employee> findAll() {
	        return mongoTemplate.findAll(Employee.class);
	    }

	    public  Employee save(Employee employee) {
	        mongoTemplate.save(employee);
	        return employee;
	    }

	    public Employee update(Employee employee){
	        Query query = new Query();
	        query.addCriteria(Criteria.where("id").is(employee.getEmpId()));
	        Update update = new Update();
	        update.set("name", employee.getName());
	        update.set("age", employee.getAge());
	        update.set("salary", employee.getSalary());
	        return mongoTemplate.findAndModify(query, update, Employee.class);
	    }

	    public List<Employee> findEmployeeByName(String empName){
	        Query query = new Query();
	        query.addCriteria(Criteria.where("name").is(empName));
	        return mongoTemplate.find(query, Employee.class);
	    }
	    public Employee findEmployeeById(int empId){
	        
	        return mongoTemplate.findById(empId, Employee.class);
	    }

	    public void deleteById(int empID) {
	        Query query = new Query();
	        query.addCriteria(Criteria.where("_id").is(empID));
	        mongoTemplate.remove(query, Employee.class);
	    }
	}

