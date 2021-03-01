package simplespring.exercici.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import simplespring.exercici.domain.Employee;
import simplespring.exercici.domain.Employee.Role;
import simplespring.exercici.service.EmployeeService;

@RestController
@RequestMapping("")
public class EmployeeController {

	private final EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("")
	public String index() {
		return "Application";
	}
	
	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();
	}
	
	@GetMapping("/employees/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable("id") long id) {
		return employeeService.getEmployee(id);
	}
	
	@GetMapping("/employees/role/{role}")
	public List<Employee> getEmployeesByRole(@PathVariable("role") Role role){
		return employeeService.getEmployeesByRole(role);
	}
	
	@PostMapping(value = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}
	
	@PutMapping(value = "/employees/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
		employeeService.updateEmployee(id, employee);
	}

	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable("id") long id) {
		employeeService.deleteEmployee(id);
	}
}
