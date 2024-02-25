package com.EmployeeAttendance.EmployeeAttendance.repository;

import com.EmployeeAttendance.EmployeeAttendance.entity.AttendenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceEntityRepository extends JpaRepository<AttendenceEntity, Long> {
}
