package com.macfi.repository;

import com.macfi.model.Classroom;
import com.macfi.model.person.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select a from Student a where a.register.identifier = :identifier")
    Student findByIdentifier(String identifier);

    @Query("select a.classrooms from Student a where a.register.identifier = :identifier")
    List<Classroom> findClassroomsByIdentifier(String identifier);

}
