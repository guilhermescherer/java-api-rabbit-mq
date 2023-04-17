package com.users.employee.controller;

import com.users.employee.data.EmployeeData;
import com.users.employee.dto.EmployeeDto;
import com.users.employee.model.Employee;
import com.users.employee.service.EmployeeService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

		URI uri = uriBuilder.path("/employee/{id}").buildAndExpand(employee.getId()).toUri();

		Message message = new Message(("Employee created with id " + employee.getId()).getBytes());
		rabbitTemplate.send("employee.name", message);

		return ResponseEntity.created(uri).body(new EmployeeDto(employee));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) {
		Employee employee = employeeService.getEmployee(id);

		return ResponseEntity.ok(new EmployeeDto(employee));
	}
}
