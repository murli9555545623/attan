package com.EmployeeAttendance.EmployeeAttendance.EventController;

import com.EmployeeAttendance.EmployeeAttendance.Service.EventService;
import com.EmployeeAttendance.EmployeeAttendance.time.LocalDateTIme.AttendanceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/swipe")
    public ResponseEntity<String> handleSwipeEvent(@RequestBody AttendanceEvent event) {
        if (event.getEventType() == null || event.getEmployeeId() == null || event.getTimestamp() == null) {
            return new ResponseEntity<>("Invalid event data", HttpStatus.BAD_REQUEST);
        }

        // Validate and store event
        eventService.processEvent(event);

        return new ResponseEntity<>("Event received and processed successfully", HttpStatus.OK);
    }

}
