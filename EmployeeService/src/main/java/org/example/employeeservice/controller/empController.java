package org.example.employeeservice.controller;

import org.example.employeeservice.model.Employee;
import org.example.employeeservice.service.empService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employee")

public class empController {
    private final empService empservice;
    public empController(empService empservice)
    {
        this.empservice=empservice;
    }
    @GetMapping("/getempDetails")
    public List<Employee> getempDetails()
    {
        return empservice.getempDetails();
    }
    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee )
    {
        return empservice.addEmployee(employee);
    }
    @PutMapping("/{empId}/addDepId/{depId}")
    public Employee addDepId(@PathVariable Integer empId,@PathVariable Integer depId)
    {
        return empservice.addDepId(empId,depId);
    }

}
