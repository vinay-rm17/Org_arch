package org.example.notificationservice.consumer;

import org.example.notificationservice.event.EmployeeCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkaConsumer {
    @KafkaListener(
            topics = "employeeService-events",
            groupId = "notification-group"
    )
    public void consumer(EmployeeCreatedEvent event)
    {
        System.out.println("====================================");
        System.out.println("EmployeeId "+event.getEmployeeId());
        System.out.println("EmployeeName "+event.getEmployeeName());
        System.out.println("EmployeeType "+event.getEventType());
        System.out.println("=====================================");
        System.out.println("Notification will be sent");

    }

}
