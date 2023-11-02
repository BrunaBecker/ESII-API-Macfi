package com.macfi.repository;

import com.macfi.model.Classroom;
import com.macfi.model.person.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select a from Student a join a.registration m where m.id = :registrationId")
    Student findByRegisterStudent(Integer registerStudent);

    @Query("select a.classrooms from Student a join a.registration m where m.id = :registrationId")
    List<Classroom> findClassroomsByRegisterStudent(Integer registerStudent);

}
