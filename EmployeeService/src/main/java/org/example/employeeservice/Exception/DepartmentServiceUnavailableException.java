package org.example.employeeservice.Exception;

public class DepartmentServiceUnavailableException extends RuntimeException{
    public DepartmentServiceUnavailableException(String message)
    {
        super(message);
    }

}
