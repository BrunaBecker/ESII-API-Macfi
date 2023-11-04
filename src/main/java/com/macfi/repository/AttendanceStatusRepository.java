package com.macfi.repository;

import com.macfi.model.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttendanceStatusRepository extends JpaRepository<AttendanceStatus, Long> {

    @Query("select a from AttendanceStatus a where a.attendance.id = :attendanceid")
    List<AttendanceStatus> FindByAttendanceId(Long attendanceid);

    @Query("select a from AttendanceStatus a where a.attendance.id = :attendanceid and a.student.id = :studentid")
    AttendanceStatus FindByAttendanceIdAndStudentId(Long attendanceid, Long studentid);


    //TODO receive attendance status for a student ID must be verified here


}