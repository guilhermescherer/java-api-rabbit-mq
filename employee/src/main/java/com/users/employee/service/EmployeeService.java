package com.users.employee.service;

import com.users.employee.data.EmployeeData;
import com.users.employee.model.Employee;

public interface EmployeeService {

	Employee createEmployee(EmployeeData employeeData);

	Employee getEmployee(Long id);
}
