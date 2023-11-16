package com.macfi.controller;

import com.macfi.payload.AttendanceDto;
import com.macfi.service.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Create Attendance REST API",
            description = "Create Attendance REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping//localhost:8080/attendance
    public ResponseEntity<AttendanceDto> createAttendance(@Valid @RequestBody AttendanceDto attendance) {
        AttendanceDto attendanceDto1;
        try {
            attendanceDto1 = attendanceService.createAttendance(attendance);
            return new ResponseEntity<>(attendanceDto1, HttpStatus.CREATED);
        } catch (Exception e) {
          return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("classroom/{id}") //localhost:8080/attendance/classroom/1
    public ResponseEntity<List<AttendanceDto>> getAttendancesByClassroom(@PathVariable("id") Long id) {

        try {
            List<AttendanceDto> attendances = attendanceService.getAttendancesByClassroom(id);
            return ResponseEntity.ok(attendances);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    //yyyy-MM-dd hh:mm:ss

    @GetMapping("inClassroomByDate") //localhost:8080/attendance/inClassroomByDate?classroomid=1&date=2021-06-01
    public ResponseEntity<List<AttendanceDto>> getAttendancesByClassroomAndDate(@RequestParam("classroomid") Long classroomid, @RequestParam("date") String date) {
        try {
            List<AttendanceDto> attendances = attendanceService.getAttendancesByClassroomAndDate(classroomid, date);
            return ResponseEntity.ok(attendances);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("happening") //localhost:8080/attendance/happenings
    public ResponseEntity<List<AttendanceDto>> getAttendancesHappening() {
       try {
           List<AttendanceDto> attendances = attendanceService.getAttendancesHappening();
           return ResponseEntity.ok(attendances);
       } catch (Exception e) {
           return ResponseEntity.badRequest().body(null);
       }
    }


    @GetMapping("happeningByClassroom/{id}") //localhost:8080/attendance/happeningByClassroom/1
    public ResponseEntity<List<AttendanceDto>> getAttendancesHappeningByClassroom(@PathVariable("id") Long id) {
        try {
            List<AttendanceDto> attendances = attendanceService.getAttendancesHappeningByClassroom(id);
            return ResponseEntity.ok(attendances);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("happeningByStudent/{id}") //localhost:8080/attendance/happeningByStudent/1
    public ResponseEntity<List<AttendanceDto>> getAttendancesHappeningByStudent(@PathVariable("id") Long id) {
        try {
            List<AttendanceDto> attendances = attendanceService.getAttendancesHappeningByStudent(id);
            return ResponseEntity.ok(attendances);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}") //localhost:8080/attendance/1
    public ResponseEntity<AttendanceDto> getAttendanceById(@PathVariable("id") Long id) {
        AttendanceDto attendanceDto;

        try {
            attendanceDto = attendanceService.getAttendanceById(id);
            return ResponseEntity.ok(attendanceDto);
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping //localhost:8080/attendance/attendanceobj
    public ResponseEntity<AttendanceDto> updateAttendance(@Valid @RequestBody AttendanceDto attendanceDto) {
        AttendanceDto attendanceDto1;
        try {
            attendanceDto1 = attendanceService.updateAttendance(attendanceDto);
            return ResponseEntity.ok(attendanceDto1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
