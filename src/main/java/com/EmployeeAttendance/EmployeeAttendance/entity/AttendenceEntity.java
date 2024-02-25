package com.EmployeeAttendance.EmployeeAttendance.entity;

import com.EmployeeAttendance.EmployeeAttendance.dto.AttendanceEvent;
import com.EmployeeAttendance.EmployeeAttendance.enums.UserState;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AttendenceEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String employeeId;
    private LocalDate date;
    private UserState userState;
}
