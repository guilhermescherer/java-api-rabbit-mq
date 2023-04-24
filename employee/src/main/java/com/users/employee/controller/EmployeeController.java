package com.users.employee.controller;

import com.users.employee.data.EmployeeData;
import com.users.employee.dto.EmployeeDto;
import com.users.employee.model.Employee;
import com.users.employee.service.EmployeeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService employeeService;
	private final RabbitTemplate rabbitTemplate;

	@Autowired
	public EmployeeController(EmployeeService employeeService, RabbitTemplate rabbitTemplate) {
		this.employeeService = employeeService;
		this.rabbitTemplate = rabbitTemplate;
	}

	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeData employeeData, UriComponentsBuilder uriBuilder) {
		Employee employee = employeeService.createEmployee(employeeData);

		EmployeeDto employeeDto = new EmployeeDto(employee);

		rabbitTemplate.convertAndSend("employee.name", employeeDto);

		URI uri = uriBuilder.path("/employee/{id}").buildAndExpand(employee.getId()).toUri();
		return ResponseEntity.created(uri).body(employeeDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) {
		Employee employee = employeeService.getEmployee(id);

		return ResponseEntity.ok(new EmployeeDto(employee));
	}
}
