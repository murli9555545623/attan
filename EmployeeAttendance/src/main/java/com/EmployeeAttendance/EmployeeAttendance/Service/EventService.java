package com.EmployeeAttendance.EmployeeAttendance.Service;

import com.EmployeeAttendance.EmployeeAttendance.time.LocalDateTIme.AttendanceEvent;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
@Service
public class EventService {


    // Map to store swipe in/out events for each employee on a given date
    private Map<String, Map<LocalDate, Duration>> employeeAttendanceMap = new HashMap<>();

    public void processEvent(AttendanceEvent event) {
        // Initialize attendance map for the employee if not present
        employeeAttendanceMap.putIfAbsent(event.getEmployeeId(), new HashMap<>());

        // Get the map of attendance for the employee
        Map<LocalDate, Duration> attendanceMap = employeeAttendanceMap.get(event.getEmployeeId());

        // Update or add attendance duration for the date of the event
        attendanceMap.compute(event.getTimestamp().toLocalDate(), (date, duration) -> {
            if (duration == null) {
                return Duration.ZERO.plusHours(event.getEventType() == AttendanceEvent.EventType.SWIPE_IN ? 1 : -1);
            } else {
                return duration.plusHours(event.getEventType() == AttendanceEvent.EventType.SWIPE_IN ? 1 : -1);
            }
        });

        // Calculate attendance status based on total duration for the date
        Duration totalAttendance = attendanceMap.values().stream().reduce(Duration::plus).orElse(Duration.ZERO);
        String attendanceStatus = calculateAttendanceStatus(totalAttendance);

        // Store or send attendance status as needed (e.g., store in database or send to Kafka)
        // attendanceService.storeAttendance(event.getEmployeeId(), event.getTimestamp().toLocalDate(), attendanceStatus);
    }

    private String calculateAttendanceStatus(Duration totalAttendance) {
        long totalHours = totalAttendance.toHours();

        if (totalHours < 4) {
            return "Absent";
        } else if (totalHours >= 4 && totalHours < 8) {
            return "Half day";
        } else {
            return "Present";
        }
    }


}
