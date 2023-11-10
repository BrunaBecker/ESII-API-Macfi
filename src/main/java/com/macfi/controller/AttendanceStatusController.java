package com.macfi.controller;

import com.macfi.payload.AttendanceStatusDto;
import com.macfi.service.AttendanceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("attendanceStatus")
public class AttendanceStatusController {

    @Autowired
    private AttendanceStatusService attendanceStatusService;

    @GetMapping //localhost:8080/attendanceStatus
    public ResponseEntity<List<AttendanceStatusDto>> getAttendanceStatus() {
        List<AttendanceStatusDto> attendanceStatuses = attendanceStatusService.getAttendanceStatus();
        return ResponseEntity.ok(attendanceStatusService.getAttendanceStatus());
    }

    @PostMapping //localhost:8080/attendanceStatus
    public AttendanceStatusDto createAttendanceStatus(@RequestBody AttendanceStatusDto attendanceStatusDto) {
        return attendanceStatusService.createAttendanceStatus(attendanceStatusDto);
    }

    @GetMapping("{idAttendanceStatus}") //localhost:8080/attendanceStatus/1
    public AttendanceStatusDto getAttendanceStatusById(@PathVariable("idAttendanceStatus") Long id) {
        return attendanceStatusService.getAttendanceStatusById(id);
    }

    @PutMapping //localhost:8080/attendanceStatus
    public AttendanceStatusDto updateAttendanceStatus(@RequestBody AttendanceStatusDto attendanceStatusDto) {
        return attendanceStatusService.updateAttendanceStatus(attendanceStatusDto);
    }

    @GetMapping("attendance/{idAttendance}") //localhost:8080/attendanceStatus/attendance/1
    public ResponseEntity<List<AttendanceStatusDto>> getAttendanceStatusByAttendanceId(@PathVariable("idAttendance") Long attendanceid) {
        List<AttendanceStatusDto> attendanceStatuses = attendanceStatusService.getAttendanceStatusByAttendanceId(attendanceid);
        return ResponseEntity.ok(attendanceStatusService.getAttendanceStatusByAttendanceId(attendanceid));
    }

    @GetMapping("byAttendanceAndStudent")
    //localhost:8080/attendanceStatus/byAttendanceAndStudent?attendanceid=1&studentid=1
    public AttendanceStatusDto getAttendanceStatusByAttendanceIdAndStudentId(@RequestParam("attendanceid") Long attendanceid, @RequestParam("studentid") Long studentid) {
        return attendanceStatusService.getAttendanceStatusByAttendanceIdAndStudentId(attendanceid, studentid);
    }


}
