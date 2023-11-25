package com.macfi.repository;

import com.macfi.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    @Query("select a from Attendance a where a.classroom.id = :id")
    List<Attendance> findByClassroomId(Long id);

    @Query("select a from Attendance a where a.isHappening = true")
    List<Attendance> findAttendanceHappening();

    @Query("select a from Attendance a where a.isHappening = true and a.classroom.id = :id")
    List<Attendance> findAttendanceHappeningByClassroom(Long id);

    @Query("select a from Attendance a join fetch Classroom c on c.id = a.classroom.id join c.students s where s.id = :id and a.isHappening = true")
    List<Attendance> findAttendanceHappeningByStudent(Long id);

    @Query("select a from Attendance a where a.date = :date and a.classroom.id = :id")
    List<Attendance> findByClassroomIdAndDate(Long id, Date date);
    @Query("select a from Attendance a join fetch Classroom c on c.id = a.classroom.id join c.professor p where p.id = :id and a.isHappening = true")
    Attendance findAttendanceHappeningByProfessor(Long id);
}
