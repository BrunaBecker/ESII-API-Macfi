package com.macfi.repository;

import com.macfi.model.Classroom;
import com.macfi.model.person.Professor;
import com.macfi.model.person.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends PersonRepository<Student, Long> {
    @Query("select s from Student s left join fetch Person p where s.register.identifier = :identifier and p.register.identifier = :identifier")
    Student findByIdentifier(String identifier);


    @Query("select s from Student s left join fetch Person p where s.email = :email and p.email = :email")
    Student findByEmail(String email);


    @Query("select s from Student s left join fetch Person p left join fetch Classroom c where s.register.identifier = p.register.identifier and c.code = :code")
    List<Student> findAllByClassroomCode(String code);

    @Query("select s from Student s left join fetch Person  p left join fetch Attendance a where s.register.identifier = p.register.identifier and a.id = :id")
    List<Student> findAllByAttendanceId(Long id);

    @Query("select s from Student s left join fetch Person p left join fetch Waiver w where s.register.identifier = p.register.identifier and w.id = :id")
    Student findAllByWaiverId(Long id);

    @Query("select s from Student s left join fetch Person  p left join fetch Attendance a where s.register.identifier = p.register.identifier and a.id = :id and a.isHappening = true")
    List<Student> findAllByAttendanceHappeningId(Long id);


    @Query("select s from Student s left join fetch Person p where p.id = s.id ")
    List<Student> findAllByRepository();


}
