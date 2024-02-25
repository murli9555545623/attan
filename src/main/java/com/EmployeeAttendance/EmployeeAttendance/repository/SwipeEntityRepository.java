package com.EmployeeAttendance.EmployeeAttendance.repository;

import com.EmployeeAttendance.EmployeeAttendance.entity.SwipeEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SwipeEntityRepository extends JpaRepository<SwipeEventEntity, Long> {

    List<SwipeEventEntity> findWithTimestampBetween(LocalDateTime startTimestamp, LocalDateTime endTimestamp);

}
