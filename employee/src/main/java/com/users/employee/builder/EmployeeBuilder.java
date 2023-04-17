package com.users.employee.builder;

import com.users.employee.model.Employee;

public class EmployeeBuilder {

	private final Employee employee;

	public EmployeeBuilder() {
		this.employee = new Employee();
	}

	public EmployeeBuilder withName(String name) {
		employee.setName(name);
		return this;
	}

	public Employee build() {
		return employee;
	}
}
