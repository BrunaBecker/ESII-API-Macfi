package com.macfi.repository;

import com.macfi.model.person.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends PersonRepository<Student, Long> {
    @Query("select s from Student s where s.register.identifier = :identifier ")
    Student findByIdentifier(String identifier);


    @Query("select p from Person p where type(p) = Student and p.email = :email")
    Student findByEmail(String email);


    @Query("select s from Student s join s.classrooms cs where cs.code = :code ")
    List<Student> findAllByClassroomCode(String code);
    //FIX
    @Query("select s from Student s left join s.attendanceStatuses ats where ats.attendance.id = :id ")
    List<Student> findAllByAttendanceId(Long id);

    @Query("select s from Student s  join fetch s.waivers ws where ws.id = :id ")
    Student findByWaiverId(Long id);
    //FIX
    @Query("select s from Student s join fetch s.attendanceStatuses ats where ats.attendance.id = :id and ats.attendance.isHappening = true")
    List<Student> findAllByAttendanceIsHappeningId(Long id);


    @Query("select p from Person p where type(p) = Student order by p.name asc")
    List<Student> findAllByRepository();

    @Query("select s from Student s join fetch s.classrooms c where c.id = :id")
    List<Student> findByClassroomId(Long id);
}
