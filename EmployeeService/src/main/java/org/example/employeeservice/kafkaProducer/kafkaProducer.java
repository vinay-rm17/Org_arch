package org.example.employeeservice.kafkaProducer;

import org.example.employeeservice.event.EmployeeCreatedEvent;
import org.example.employeeservice.model.Employee;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class kafkaProducer {
     private final KafkaTemplate<String, EmployeeCreatedEvent> kafkTemplate;
     public kafkaProducer(KafkaTemplate<String, EmployeeCreatedEvent> kafkaTemplate)
     {
         this.kafkTemplate=kafkaTemplate;
     }
     public void sendEmpCreatedEvent(EmployeeCreatedEvent event)
     {
         kafkTemplate.send(
                 "employeeService-events",
                 event.getEmployeeId().toString(),
                 event

         );
         System.out.println("Employee event sent to kafka "+event.getEmployeeId());
     }

}
