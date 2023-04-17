package com.users.consumer.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeListener {

    @RabbitListener(queues = "employee.name")
    public void getMessage(Message message) {
        System.out.println(message.toString());
    }
}
