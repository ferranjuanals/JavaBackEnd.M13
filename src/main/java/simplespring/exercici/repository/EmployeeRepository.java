package simplespring.exercici.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import simplespring.exercici.domain.Employee;
import simplespring.exercici.domain.Employee.Role;

@Repository
public class EmployeeRepository implements IRepository, JpaRepository<Employee, Long>{
    
	private static List<Employee> employees = new ArrayList<Employee>();
	public EmployeeRepository() {
		try {
			employees.add(new Employee("Andreu", "Intern"));
			employees.add(new Employee("Maria", "Manager"));
			employees.add(new Employee("Joaquim", "Employee"));
			employees.add(new Employee("Anna", "Employee"));
		}catch(Exception e) {
			System.err.println("No es pot crear l'empleat");
		}
	}
	@Override
	public Page<Employee> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Employee> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<Employee> findById(Long id) {
		return employees.stream().filter(e -> e.getId() == id).findAny();
	}
	@Override
	public boolean existsById(Long id) {
		if(employees.stream().anyMatch(e -> e.getId() == id)) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void deleteById(Long id) {
		employees.remove(getOne(id));
	}
	@Override
	public void delete(Employee entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll(Iterable<? extends Employee> entities) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll() {
		employees.clear();		
	}
	@Override
	public <S extends Employee> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Employee> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Employee> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public <S extends Employee> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Employee> findAll() {
		return employees;
	}
	@Override
	public List<Employee> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Employee> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Employee> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public <S extends Employee> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteInBatch(Iterable<Employee> entities) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Employee getOne(Long id) {
		return employees.stream().filter(e -> e.getId() == id).findAny().get();
	}
	@Override
	public <S extends Employee> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Employee> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Employee> findByRole(Role role) {
		List<Employee> employeesByRole = employees.stream()
											.filter(e -> e.getRole().equals(role))
											.collect(Collectors.toList());
		return employeesByRole;
	}
	public void addEmployee(Employee employee) {
		employees.add(employee);		
	}
	
}