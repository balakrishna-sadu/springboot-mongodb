package mongo.springbootmongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import mongo.springbootmongo.model.Employee;

public interface EmployeeRepo extends MongoRepository<Employee, Integer>{
	public Employee findByName(String name);
	public Employee deleteByName(String name);
}
