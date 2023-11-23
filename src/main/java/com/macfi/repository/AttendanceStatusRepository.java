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


    @Query("select a from AttendanceStatus a where a.student.id = :studentid")
    List<AttendanceStatus> FindByStudentId(Long studentid);

    @Query("select a from AttendanceStatus a where a.student.id = ?1 and a.attendance.classroom.id = ?2")
    List<AttendanceStatus> findByStudentIdAndClassroomId(Long id, Long id1);
}