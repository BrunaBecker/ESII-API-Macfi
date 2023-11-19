package com.macfi.controller;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.payload.AttendanceStatusDto;
import com.macfi.service.AttendanceStatusService;
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
@RequestMapping("attendanceStatus")
public class AttendanceStatusController {

    @Autowired
    private AttendanceStatusService attendanceStatusService;

    @GetMapping //localhost:8080/attendanceStatus
    public ResponseEntity<List<AttendanceStatusDto>> getAttendanceStatus() {

        try {
            List<AttendanceStatusDto> attendanceStatuses = attendanceStatusService.getAttendanceStatus();
            return ResponseEntity.ok(attendanceStatuses);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Operation(
            summary = "Create AttendanceStatus REST API",
            description = "Create AttendanceStatus REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping //localhost:8080/attendanceStatus
    public ResponseEntity<AttendanceStatusDto> createAttendanceStatus(@Valid @RequestBody AttendanceStatusDto attendanceStatusDto) {
        AttendanceStatusDto a;

        try {
            a = attendanceStatusService.createAttendanceStatus(attendanceStatusDto);
            return new ResponseEntity<>(a, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("{idAttendanceStatus}") //localhost:8080/attendanceStatus/1
    public ResponseEntity<AttendanceStatusDto> getAttendanceStatusById(@PathVariable("idAttendanceStatus") Long id) {
        try {
            return ResponseEntity.ok(attendanceStatusService.getAttendanceStatusById(id));
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping //localhost:8080/attendanceStatus
    public ResponseEntity<AttendanceStatusDto> updateAttendanceStatus(@Valid @RequestBody AttendanceStatusDto attendanceStatusDto) {
        AttendanceStatusDto a;

        try {
            a = attendanceStatusService.updateAttendanceStatus(attendanceStatusDto);
            return ResponseEntity.ok(a);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("attendance/{idAttendance}") //localhost:8080/attendanceStatus/attendance/1
    public ResponseEntity<List<AttendanceStatusDto>> getAttendanceStatusByAttendanceId(@PathVariable("idAttendance") Long attendanceid) {
        try {
            return ResponseEntity.ok(attendanceStatusService.getAttendanceStatusByAttendanceId(attendanceid));
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("byAttendanceAndStudent")
    //localhost:8080/attendanceStatus/byAttendanceAndStudent?attendanceid=1&studentid=1
    public ResponseEntity<AttendanceStatusDto> getAttendanceStatusByAttendanceIdAndStudentId(@RequestParam("attendanceid") Long attendanceid, @RequestParam("studentid") Long studentid) {
        try {
            return ResponseEntity.ok(attendanceStatusService.getAttendanceStatusByAttendanceIdAndStudentId(attendanceid, studentid));
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("validate/{idAttendanceStatus}") //localhost:8080/attendanceStatus/validate/1
    public ResponseEntity<AttendanceStatusDto> validateAttendanceStatus(@PathVariable("idAttendanceStatus") Long id) {
        try {
            return ResponseEntity.ok(attendanceStatusService.validateAttendanceStatus(id));
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("invalidate/{idAttendanceStatus}") //localhost:8080/attendanceStatus/invalidate/1
    public ResponseEntity<AttendanceStatusDto> invalidateAttendanceStatus(@PathVariable("idAttendanceStatus") Long id) {
        try {
            return ResponseEntity.ok(attendanceStatusService.invalidateAttendanceStatus(id));
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("respond/{idAttendanceStatus}") //localhost:8080/attendanceStatus/respond/1
    public ResponseEntity<AttendanceStatusDto> respondAttendanceStatus(@PathVariable("idAttendanceStatus") Long id) {
        try {
            return ResponseEntity.ok(attendanceStatusService.respondAttendanceStatus(id));
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setWaiver") //localhost:8080/attendanceStatus/setWaiver?idAttendanceStatus=1&idWaiver=1
    public ResponseEntity<AttendanceStatusDto> setWaiver(@RequestParam("idAttendanceStatus") Long idAttendanceStatus, @RequestParam("idWaiver") Long idWaiver) {
        try {
            return ResponseEntity.ok(attendanceStatusService.setWaiver(idAttendanceStatus, idWaiver));
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping("setSuccessfulPing") //localhost:8080/attendanceStatus/addPing?idAttendanceStatus=1&idPing=1
    public ResponseEntity<AttendanceStatusDto> setSuccessfulPing(@RequestParam("idAttendanceStatus") Long idAttendanceStatus, @RequestParam("idPing") Long idPing) {
        try {
            return ResponseEntity.ok(attendanceStatusService.setSuccessfulPing(idAttendanceStatus, idPing));
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
        return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setUnsuccessfulPing") //localhost:8080/attendanceStatus/addPing?idAttendanceStatus=1&idPing=1
    public ResponseEntity<AttendanceStatusDto> setUnsuccessfulPing(@RequestParam("idAttendanceStatus") Long idAttendanceStatus, @RequestParam("idPing") Long idPing) {
        try {
            return ResponseEntity.ok(attendanceStatusService.setUnsuccessfulPing(idAttendanceStatus, idPing));
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
        return ResponseEntity.badRequest().body(null);
        }
    }
    @PutMapping("removeSuccessfulPing") //localhost:8080/attendanceStatus/removePing?idAttendanceStatus=1&idPing=1
    public ResponseEntity<AttendanceStatusDto> removeSuccessfulPing(@RequestParam("idAttendanceStatus") Long idAttendanceStatus, @RequestParam("idPing") Long idPing) {
        try {
            return ResponseEntity.ok(attendanceStatusService.removeSuccessfulPing(idAttendanceStatus, idPing));
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
        return ResponseEntity.badRequest().body(null);
        }
    }
    @PutMapping("removeUnsuccessfulPing") //localhost:8080/attendanceStatus/removePing?idAttendanceStatus=1&idPing=1
    public ResponseEntity<AttendanceStatusDto> removeUnsuccessfulPing(@RequestParam("idAttendanceStatus") Long idAttendanceStatus, @RequestParam("idPing") Long idPing) {
        try {
            return ResponseEntity.ok(attendanceStatusService.removeUnsuccessfulPing(idAttendanceStatus, idPing));
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
        return ResponseEntity.badRequest().body(null);
        }
    }



    @GetMapping("byStudent") //localhost:8080/attendanceStatus/byStudent?idStudent=1
    public ResponseEntity<List<AttendanceStatusDto>> getAttendanceStatusByStudentId(@RequestParam("idStudent") Long studentid) {
        try {
            return ResponseEntity.ok(attendanceStatusService.getAttendanceStatusByStudentId(studentid));
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
        return ResponseEntity.badRequest().body(null);
        }
    }


}
