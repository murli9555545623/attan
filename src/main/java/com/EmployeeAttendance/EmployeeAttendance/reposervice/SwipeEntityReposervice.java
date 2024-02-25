package com.EmployeeAttendance.EmployeeAttendance.reposervice;

import com.EmployeeAttendance.EmployeeAttendance.dto.AttendanceEvent;
import com.EmployeeAttendance.EmployeeAttendance.entity.SwipeEventEntity;
import com.EmployeeAttendance.EmployeeAttendance.mapper.SwipeEventEntityMapper;
import com.EmployeeAttendance.EmployeeAttendance.repository.SwipeEntityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SwipeEntityReposervice {

    private final SwipeEntityRepository swipeEntityRepository;

    public SwipeEntityReposervice(SwipeEntityRepository swipeEntityRepository) {
        this.swipeEntityRepository = swipeEntityRepository;
    }

    public void save(AttendanceEvent attendanceEvent) {
        SwipeEventEntity swipeEventEntity = SwipeEventEntityMapper.mapToEntity(attendanceEvent);
        swipeEntityRepository.save(swipeEventEntity);
    }

    public List<SwipeEventEntity> findByTimestampBetween(LocalDateTime startTimestamp, LocalDateTime endTimestamp) {
        return swipeEntityRepository.findWithTimestampBetween(startTimestamp, endTimestamp);
    }

}
