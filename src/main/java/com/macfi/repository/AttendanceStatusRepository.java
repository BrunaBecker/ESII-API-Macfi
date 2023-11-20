package com.macfi.repository;

import com.macfi.model.AttendanceStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttendanceStatusRepository extends JpaRepository<AttendanceStatus, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from AttendanceStatus a where a.attendance.id = :attendanceid")
    List<AttendanceStatus> FindByAttendanceId(Long attendanceid);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from AttendanceStatus a where a.attendance.id = :attendanceid and a.student.id = :studentid")
    AttendanceStatus FindByAttendanceIdAndStudentId(Long attendanceid, Long studentid);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from AttendanceStatus a where a.student.id = :studentid")
    List<AttendanceStatus> FindByStudentId(Long studentid);

}