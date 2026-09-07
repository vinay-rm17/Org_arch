package org.example.employeeservice.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.example.employeeservice.Exception.DepartmentNotFoundException;
import org.example.employeeservice.Exception.DepartmentServiceUnavailableException;
import org.example.employeeservice.event.EmployeeCreatedEvent;
import org.example.employeeservice.kafkaProducer.kafkaProducer;
import org.example.employeeservice.model.Department;
import org.example.employeeservice.model.Employee;
import org.example.employeeservice.repo.empRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.util.EnumMap;
import java.util.List;

@Service
public class empService {

    private final empRepo emprepo;
    private final kafkaProducer kafkaproducer;
    private final RestClient restClient;
    public empService(empRepo emprepo, kafkaProducer kafkaproducer)
    {
        this.emprepo=emprepo;
        this.kafkaproducer=kafkaproducer;
        this.restClient=RestClient.builder()
                .baseUrl("http://localhost:8082")
                .build();
    }
    public List<Employee> getempDetails()
    {
        return emprepo.findAll();
    }

    public Employee addEmployee(Employee employee) {
        Employee savedEmp=emprepo.save(employee);
        System.out.println("Employee "+employee.getName()+" added successfully");
        EmployeeCreatedEvent event=new EmployeeCreatedEvent(
                savedEmp.getId(),
                savedEmp.getName(),
                "Employee Created"
        );
        kafkaproducer.sendEmpCreatedEvent(event);
        return employee;

    }
    public Employee addDepId(Integer empId,Integer depId)
    {
        Department department;
        try {


            department = restClient.get()
                    .uri("/department/{id}", depId)
                    .retrieve()
                    .onStatus(status->status.value()==404,
                            (request, response) ->
                            {
                                throw new DepartmentNotFoundException(depId);
                            })
                    .body(Department.class);
        }
        catch(ResourceAccessException e){
            throw new DepartmentServiceUnavailableException("Department service currently unavailable");

        }

      Employee employee1=emprepo.findById(empId)
              .orElseThrow(()->new RuntimeException("Employee not found"));
      employee1.setDepId(depId);

      return emprepo.save(employee1);

    }
}
