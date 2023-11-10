package com.macfi.controller;

import com.macfi.model.Attendance;
import com.macfi.payload.AttendanceDto;
import com.macfi.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping //localhost:8080/attendance
    public ResponseEntity<List<AttendanceDto>> getAttendances() {
        return ResponseEntity.ok(attendanceService.getAttendances());
    }

    @PostMapping//localhost:8080/attendance
    public ResponseEntity<AttendanceDto> createAttendance(@Valid @RequestBody AttendanceDto attendance) {
        return new ResponseEntity<>(attendanceService.createAttendance(attendance), HttpStatus.CREATED);
    }

    @GetMapping("classroom/{id}") //localhost:8080/attendance/classroom/1
    public ResponseEntity<List<AttendanceDto>> getAttendancesByClassroom(@PathVariable("id") Long id) {
        List<AttendanceDto> attendances = attendanceService.getAttendancesByClassroom(id);
        return ResponseEntity.ok(attendances);
    }

    //yyyy-MM-dd hh:mm:ss

    @GetMapping("inClassroomByDate") //localhost:8080/attendance/inClassroomByDate?classroomid=1&date=2021-06-01
    public ResponseEntity<List<AttendanceDto>> getAttendancesByClassroomAndDate(@RequestParam("classroomid") Long classroomid, @RequestParam("date") String date) {
        List<AttendanceDto> attendances = attendanceService.getAttendancesByClassroomAndDate(classroomid, date);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("happening") //localhost:8080/attendance/happenings
    public ResponseEntity<List<AttendanceDto>> getAttendancesHappening() {
        List<AttendanceDto> attendances = attendanceService.getAttendancesHappening();
        return ResponseEntity.ok(attendances);
    }


    @GetMapping("happeningByClassroom/{id}") //localhost:8080/attendance/happeningByClassroom/1
    public ResponseEntity<List<AttendanceDto>> getAttendancesHappeningByClassroom(@PathVariable("id") Long id) {
        List<AttendanceDto> attendances = attendanceService.getAttendancesHappeningByClassroom(id);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("happeningByStudent/{id}") //localhost:8080/attendance/happeningByStudent/1
    public ResponseEntity<List<AttendanceDto>> getAttendancesHappeningByStudent(@PathVariable("id") Long id) {
        List<AttendanceDto> attendances = attendanceService.getAttendancesHappeningByStudent(id);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/{id}") //localhost:8080/attendance/1
    public ResponseEntity<AttendanceDto> getAttendanceById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(attendanceService.getAttendanceById(id));
    }

    @PutMapping //localhost:8080/attendance/attendanceobj
    public ResponseEntity<AttendanceDto> updateAttendance(@Valid @RequestBody AttendanceDto attendanceDto) {
        return ResponseEntity.ok(attendanceService.updateAttendance(attendanceDto));
    }

    @GetMapping("/happening/{id}") //localhost:8080/attendance/happening/1
    public ResponseEntity<AttendanceDto> getAttendanceHappeningById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(attendanceService.getAttendanceHappeningById(id));
    }
}
