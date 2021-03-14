package simplespring.exercici.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simplespring.exercici.domain.Employee;
import simplespring.exercici.domain.Employee.Role;
import simplespring.exercici.exception.IdNotFoundException;
import simplespring.exercici.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}		
	public Optional<Employee> getEmployeeById(long id){
		return employeeRepository.findById(id);
	}
	public List<Employee> getEmployeesByRole(Role role){
		return employeeRepository.findByRole(role);
	}
	public Employee addEmployee(Employee employee) {
		employeeRepository.addEmployee(employee);
		return employee;
	}
	public Employee updateEmployee(long id, Employee employee) {
		if(!employeeRepository.existsById(id)) throw new IdNotFoundException(id);
		Employee updateEmployee = employeeRepository.getOne(id);
		updateEmployee.setName(employee.getName());
		updateEmployee.setRole(employee.getRole().toString());
		return updateEmployee;
	}
	public void deleteEmployee(long id) {
		if(!employeeRepository.existsById(id)) throw new IdNotFoundException(id);
		employeeRepository.deleteById(id);
	}
}
