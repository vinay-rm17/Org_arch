package org.example.employeeservice.Exception;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(Integer depId)
    {
        super("Department not found with id "+depId);
    }
}
