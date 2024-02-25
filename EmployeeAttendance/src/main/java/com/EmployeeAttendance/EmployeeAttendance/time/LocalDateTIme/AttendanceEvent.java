package com.EmployeeAttendance.EmployeeAttendance.time.LocalDateTIme;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
@Entity
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
