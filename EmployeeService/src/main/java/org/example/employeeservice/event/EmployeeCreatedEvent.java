package org.example.employeeservice.event;

public class EmployeeCreatedEvent {

    private Integer employeeId;
    private String employeeName;
    private String eventType;

    public EmployeeCreatedEvent() {
    }

    public EmployeeCreatedEvent(
            Integer employeeId,
            String employeeName,
            String eventType) {

        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.eventType = eventType;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
