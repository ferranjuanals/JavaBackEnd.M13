package simplespring.exercici.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import simplespring.exercici.domain.Employee;
import simplespring.exercici.domain.Employee.Role;
import simplespring.exercici.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("")
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> listEmployees = employeeService.getEmployees();
		return ResponseEntity.ok(listEmployees);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
		Optional<Employee> optionalEmployee = employeeService.getEmployeeById(id);
		if(optionalEmployee.isPresent()) {
			return ResponseEntity.ok(optionalEmployee.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/role/{role}")
	public ResponseEntity<List<Employee>> getEmployeesByRole(@PathVariable("role") Role role){
		List<Employee> employeesByRole = employeeService.getEmployeesByRole(role);
		return ResponseEntity.ok(employeesByRole);
	}
	
	@PostMapping(value = "/addEmployee", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee newEmployee = employeeService.addEmployee(employee);
		return ResponseEntity.ok(newEmployee);
	}
	
	@PutMapping(value = "/updateEmployee/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
		Employee updateEmployee = employeeService.updateEmployee(id, employee);
		return ResponseEntity.ok(updateEmployee);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") long id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.ok(null);
	}
}
