package mongo.springbootmongo.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mongo.springbootmongo.model.Employee;
import mongo.springbootmongo.repo.EmployeeRepo;

@RestController
public class EmployeeController {
	
	@Autowired
	public MongoTemplate mongoT;

	@Autowired
	private EmployeeRepo employeerepo;
	
	
	@PostMapping("/addEmployee")
	public String addEmployee(@RequestBody Employee employee) {
		employeerepo.save(employee);
		return "Employee : "+ employee.getName()+" inserted in MongoDB";
	}
	
	@GetMapping("/getAllEmployees")
	public List<Employee> getAllEmployees(){
		return employeerepo.findAll();
	}
	
	@GetMapping("/getEmploy/{name}")
	public Optional<Employee> getEmployee(@PathVariable String name){
		Employee emp=null;
		emp=employeerepo.findByName(name);
		return Optional.ofNullable(emp);
	}
	
	@PutMapping("/updateEmployee/{name}")
	public String updateEmployee(@PathVariable String name, @RequestBody Employee emp) {
		
		Employee employ=null;
		employ=employeerepo.findByName(name);
		if(employ!=null) {
			employeerepo.save(emp);
			return "Employee Updated Successfully!!";
		}
		else {
			return "Employee Not found";
		}
	}
	
	@DeleteMapping("/delete/{name}")
	public String deleteEmployee(@PathVariable String name) {
		employeerepo.deleteByName(name);
		return "Employee : "+name+"deleted";
	}
	
	@GetMapping("custom/getByDept")
	public List<Employee> getByDept(){
		Query query = new Query(Criteria.where("dept").is("BE"));
		return mongoT.find(query, Employee.class);
	}
	
}
