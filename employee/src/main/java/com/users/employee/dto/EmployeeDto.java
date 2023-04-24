package com.users.employee.dto;

import com.users.employee.model.Employee;

public class EmployeeDto {

	private final Long id;
	private final String name;

	public EmployeeDto(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
