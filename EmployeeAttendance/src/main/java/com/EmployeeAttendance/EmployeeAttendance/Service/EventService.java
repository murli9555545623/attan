package com.EmployeeAttendance.EmployeeAttendance.service;

import com.EmployeeAttendance.EmployeeAttendance.dto.AttendanceEvent;
import com.EmployeeAttendance.EmployeeAttendance.entity.AttendenceEntity;
import com.EmployeeAttendance.EmployeeAttendance.entity.SwipeEventEntity;
import com.EmployeeAttendance.EmployeeAttendance.enums.UserState;
import com.EmployeeAttendance.EmployeeAttendance.reposervice.SwipeEntityReposervice;
import com.EmployeeAttendance.EmployeeAttendance.repository.AttendanceEntityRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {


    private static final ZoneId zone = ZoneId.of("Asia/Kolkata");
    private final SwipeEntityReposervice swipeEntityReposervice;
    private final AttendanceEntityRepository attendanceEntityRepository;
    private Map<String, Map<LocalDate, Duration>> employeeAttendanceMap = new HashMap<>();

    public EventService(SwipeEntityReposervice swipeEntityReposervice, AttendanceEntityRepository attendanceEntityRepository) {
        this.swipeEntityReposervice = swipeEntityReposervice;
        this.attendanceEntityRepository = attendanceEntityRepository;
    }

    public void saveEvent(AttendanceEvent attendanceEvent) {
        swipeEntityReposervice.save(attendanceEvent);
    }

    //Cron to run at 12 AM every day
    @Scheduled(cron = "0 0 0 * * *")
    public List<AttendenceEntity> processEvent() {

        List<AttendenceEntity> response = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();

        // Calculate the end of the day
        LocalDateTime endOfDay = now.toLocalDate().plusDays(1).atStartOfDay().minusSeconds(1);

        // Get all events for the last 24 hours
        List<SwipeEventEntity> events = swipeEntityReposervice.findByTimestampBetween(startOfDay, endOfDay);

        Map<String, List<SwipeEventEntity>> userToEventMap= events.stream().collect(Collectors.groupingBy(SwipeEventEntity::getEmployeeId));

        userToEventMap.forEach((userId, userEvents) -> {
            Duration totalAttendance = Duration.ZERO;
            userEvents.sort(Comparator.comparing(SwipeEventEntity::getTimestamp));
            for (int i = 0; i < userEvents.size()-1; i++) {
                LocalDateTime start = userEvents.get(i).getTimestamp();
                LocalDateTime end = userEvents.get(i + 1).getTimestamp();
                totalAttendance = totalAttendance.plus(Duration.between(start, end));
            }
            UserState attendanceStatus = calculateAttendanceStatus(totalAttendance);
            AttendenceEntity attendenceEntity = new AttendenceEntity();
            attendenceEntity.setEmployeeId(userId);
            attendenceEntity.setDate(startOfDay.toLocalDate());
            attendenceEntity.setUserState(attendanceStatus);

            // Save the attendance status or send to Kafka
            //attendanceEntityRepository.save(attendenceEntity);

            response.add(attendenceEntity);

        });

        return response;
    }

    private UserState calculateAttendanceStatus(Duration totalAttendance) {
        long totalHours = totalAttendance.toHours();

        if (totalHours < 4) {
            return UserState.ABSENT;
        } else if (totalHours >= 4 && totalHours < 8) {
            return UserState.HALF_DAY;
        } else {
            return UserState.PRESENT;
        }
    }


}
