package simplespring.exercici.domain;

import java.util.Arrays;

import javax.persistence.*;

@Entity
public class Employee {
	
	public enum Role {
		INTERN, EMPLOYEE, MANAGER, BOSS
	}

	@Id
	private long id;
	private static long next_id=0;
	private String name;
	private Role role;
	private double salary;
	
	public Employee(String name, String role) throws Exception {
		this.id = next_id;
		this.name = name;
		if(!Arrays.stream(Role.values()).anyMatch((r) -> r.name().equals(role.toUpperCase()))) throw new Exception();
		defineRoleAndSalary(role);
		next_id++;
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
	public void setRole(String role) throws Exception {
		if(!Arrays.stream(Role.values()).anyMatch((r) -> r.name().equals(role.toUpperCase()))) throw new Exception();
		defineRoleAndSalary(role);
	}
	public double getSalary() {
		return salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", role=" + role + ", salary=" + salary + "]";
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
