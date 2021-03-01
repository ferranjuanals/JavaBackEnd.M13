package simplespring.exercici.repository;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import simplespring.exercici.domain.Employee;

@NoRepositoryBean
public interface IRepository {
	
	public List<Employee> findByRole(Employee.Role role);
}
