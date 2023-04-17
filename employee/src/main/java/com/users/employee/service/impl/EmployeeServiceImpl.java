package com.users.employee.service.impl;

import com.users.employee.data.EmployeeData;
import com.users.employee.model.Employee;
import com.users.employee.repository.EmployeeRepository;
import com.users.employee.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee createEmployee(EmployeeData employeeData) {
		Employee employee = Employee.builder()
			.withName(employeeData.getName())
			.build();

		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployee(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}
}
