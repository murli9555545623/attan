package com.EmployeeAttendance.EmployeeAttendance.entity;

import com.EmployeeAttendance.EmployeeAttendance.dto.AttendanceEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class SwipeEventEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String employeeId;
    private LocalDateTime timestamp;
    private AttendanceEvent.EventType eventType;

}
