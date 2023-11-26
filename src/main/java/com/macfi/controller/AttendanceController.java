package com.macfi.controller;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.payload.AttendanceDto;
import com.macfi.service.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
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
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("classroom/{id}") //localhost:8080/attendance/classroom/1
    public ResponseEntity<List<AttendanceDto>> getAttendancesByClassroom(@PathVariable("id") Long id) {

        try {
            List<AttendanceDto> attendances = attendanceService.getAttendancesByClassroom(id);
            return ResponseEntity.ok(attendances);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }

    }

    //yyyy-MM-dd hh:mm:ss

    @GetMapping("getClassroomByDate") //localhost:8080/attendance/inClassroomByDate?classroomid=1&date=2021-06-01
    public ResponseEntity<List<AttendanceDto>> getAttendancesByClassroomAndDate(@RequestParam("classroomid") Long classroomid, @RequestParam("date") String date) {
        try {
            List<AttendanceDto> attendances = attendanceService.getAttendancesByClassroomAndDate(classroomid, date);
            return ResponseEntity.ok(attendances);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("happening") //localhost:8080/attendance/happenings
    public ResponseEntity<List<AttendanceDto>> getAttendancesHappening() {
        try {
            List<AttendanceDto> attendances = attendanceService.getAttendancesHappening();
            return ResponseEntity.ok(attendances);
        } catch (Exception e) {            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("happeningByClassroom/{id}") //localhost:8080/attendance/happeningByClassroom/1
    public ResponseEntity<AttendanceDto> getAttendancesHappeningByClassroom(@PathVariable("id") Long id) {
        try {
            AttendanceDto attendance = attendanceService.getAttendancesHappeningByClassroom(id);
            return ResponseEntity.ok(attendance);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("happeningByProfessor/{id}") //localhost:8080/attendance/happeningByProfessor/1
    public ResponseEntity<AttendanceDto> getAttendancesHappeningByProfessor(@PathVariable("id") Long id) {
        try {
            AttendanceDto attendances = attendanceService.getAttendancesHappeningByProfessor(id);
            return ResponseEntity.ok(attendances);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("happeningByStudent/{id}") //localhost:8080/attendance/happeningByStudent/1
    public ResponseEntity<AttendanceDto> getAttendancesHappeningByStudent(@PathVariable("id") Long id) {
        try {
            AttendanceDto attendance = attendanceService.getAttendancesHappeningByStudent(id);
            return ResponseEntity.ok(attendance);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}") //localhost:8080/attendance/1
    public ResponseEntity<AttendanceDto> getAttendanceById(@PathVariable("id") Long id) {
        AttendanceDto attendanceDto;

        try {
            attendanceDto = attendanceService.getAttendanceById(id);
            return ResponseEntity.ok(attendanceDto);
        } catch (Exception e) {            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping //localhost:8080/attendance/attendanceobj
    public ResponseEntity<AttendanceDto> updateAttendance(@Valid @RequestBody AttendanceDto attendanceDto) {
        AttendanceDto attendanceDto1;
        try {
            attendanceDto1 = attendanceService.updateAttendance(attendanceDto);
            return ResponseEntity.ok(attendanceDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("start/{id}") //localhost:8080/attendance/start/1
    public ResponseEntity<AttendanceDto> startAttendance(@PathVariable("id") Long id) {
        AttendanceDto attendanceDto;
        try {
            attendanceDto = attendanceService.startAttendance(id);
            return ResponseEntity.ok(attendanceDto);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("end/{id}") //localhost:8080/attendance/end/1
    public ResponseEntity<AttendanceDto> endAttendance(@PathVariable("id") Long id) {
        AttendanceDto attendanceDto;
        try {
            attendanceDto = attendanceService.endAttendance(id);
            return ResponseEntity.ok(attendanceDto);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setVirtualZone") //localhost:8080/attendance/setVirtualZone/1
    public ResponseEntity<AttendanceDto> setVirtualZone(@RequestParam("idAttendance") Long idAttendance, @RequestParam("idVirtualZone") Long idVirtualZone) {
        try {
            return ResponseEntity.ok(attendanceService.setVirtualZone(idAttendance, idVirtualZone));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setAttendanceStatus")
    //localhost:8080/attendance/setAttendanceStatus?idAttendance=1&idAttendanceStatus=1
    public ResponseEntity<AttendanceDto> setAttendanceStatus(@RequestParam("idAttendance") Long idAttendance, @RequestParam("idAttendanceStatus") Long idAttendanceStatus) {
        try {
            return ResponseEntity.ok(attendanceService.setAttendanceStatus(idAttendance, idAttendanceStatus));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("duration")//localhost:8080/attendance/duration?id=1
    public ResponseEntity<Duration> getDuration(@RequestParam("id") Long id) {
        try {
            return ResponseEntity.ok(attendanceService.getDuration(id));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

}
