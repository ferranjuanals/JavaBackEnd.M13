package simplespring.exercici.domain;

import java.util.Arrays;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Component
public class Employee {

	@Id
	@GeneratedValue
	private final Long id;
	private String name;
	private Role role;
	private double salary;
	private enum Role {
		INTERN, EMPLOYEE, MANAGER, BOSS
	}
	
	public Employee(String name, String role) throws Exception {
		this.id = null;
		this.name = name;
		if(!Arrays.stream(Role.values()).anyMatch((r) -> r.name().equals(role.toUpperCase()))) throw new Exception();
		defineRoleAndSalary(role);
	};
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}

	private void defineRoleAndSalary(String role) {
		switch(role.toUpperCase()) {
		case "INTERN":
			this.role = Role.INTERN;
			this.salary = 600;
			break;
		case "EMPLOYEE":
			this.role = Role.EMPLOYEE;
			this.salary = 1200;
			break;
		case "MANAGER":
			this.role = Role.MANAGER;
			this.salary = 1600;
			break;
		case "BOSS":
			this.role = Role.BOSS;
			this.salary = 3600;
			break;
		}
	}
	
}
