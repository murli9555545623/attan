package com.EmployeeAttendance.EmployeeAttendance.dto;

import java.time.LocalDateTime;
public class  AttendanceEvent {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String employeeId;

//    @JsonFormat(pattern = ("yyyy/MM/dd HH:mm:ss"))
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    private LocalDateTime dateOfRental;
    private LocalDateTime timestamp;
    private EventType eventType;

    public AttendanceEvent() {
    }

    public enum EventType {
        SWIPE_IN,
        SWIPE_OUT
    }

    public AttendanceEvent(String employeeId, LocalDateTime timestamp, EventType eventType) {
        this.employeeId = employeeId;
        this.timestamp = timestamp;
        this.eventType = eventType;
    }

    // Getters and Setters
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

}
