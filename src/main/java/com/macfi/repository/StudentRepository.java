package com.macfi.repository;

import com.macfi.model.person.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.register.identifier = :identifier ")
    Student findByIdentifier(String identifier);


    @Query("select p from Person p where type(p) = Student and p.email = :email")
    Student findByEmail(String email);


    @Query("select s from Student s inner join AttendanceStatus ats on s.id = ats.student.id  where ats.attendance.id = :id")
    List<Student> findAllByAttendanceId(Long id);

    @Query("select s from Student s  join fetch s.waivers ws where ws.id = :id ")
    Student findByWaiverId(Long id);

    @Query("select s from Student s inner join AttendanceStatus ats on s.id = ats.student.id  where ats.attendance.id = :id and ats.attendance.isHappening = true")
    List<Student> findAllByAttendanceIsHappeningId(Long id);


    @Query("select p from Person p where type(p) = Student order by p.name asc")
    List<Student> findAllByRepository();

    @Query("select s from Student s join fetch s.classrooms c where c.id = :id")
    List<Student> findByClassroomId(Long id);

    List<Student> findByClassroomsCodeAndClassroomsClassNameAndClassroomsSemester(String code, String className, String semester);
}
