package com.macfi.repository;

import com.macfi.model.Waiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WaiverRepository extends JpaRepository<Waiver, Long> {

    @Query("select w from Waiver w left join AttendanceStatus ats on ats.id = w.attendanceStatus.id left join Attendance at on ats.attendance.id = at.id left join Classroom c on c.id = at.classroom.id where w.student.id = :id and c.id = :idClassroom")
    Waiver findByStudentIdAndClassroomId(Long id, Long idClassroom);


}
