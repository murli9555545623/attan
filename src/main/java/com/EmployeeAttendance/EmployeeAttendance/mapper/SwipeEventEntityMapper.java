package com.EmployeeAttendance.EmployeeAttendance.mapper;

import com.EmployeeAttendance.EmployeeAttendance.dto.AttendanceEvent;
import com.EmployeeAttendance.EmployeeAttendance.entity.SwipeEventEntity;
import org.springframework.stereotype.Component;

@Component
public class SwipeEventEntityMapper {

    public static SwipeEventEntity mapToEntity(AttendanceEvent attendanceEvent) {
        SwipeEventEntity swipeEventEntity = new SwipeEventEntity();
        swipeEventEntity.setEmployeeId(attendanceEvent.getEmployeeId());
        swipeEventEntity.setTimestamp(attendanceEvent.getTimestamp());
        swipeEventEntity.setEventType(attendanceEvent.getEventType());
        return swipeEventEntity;
    }
}
