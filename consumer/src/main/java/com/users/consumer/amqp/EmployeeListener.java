package com.users.consumer.amqp;

import com.users.consumer.dto.EmployeeDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeListener {

    @RabbitListener(queues = "employee.details")
    public void getMessage(EmployeeDto employeeDto) {
        System.out.println(employeeDto.toString());
    }
}
