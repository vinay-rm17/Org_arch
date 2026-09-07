package org.example.employeeservice.repo;

import org.example.employeeservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface empRepo extends JpaRepository<Employee,Integer> {
}
