package com.macfi.repository;

import com.macfi.model.Classroom;
import com.macfi.model.Location;
import com.macfi.model.person.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("select p from Professor p where p.registerProfessor = :registerProfessor")
    Professor findByregisterProfessor(Integer registerProfessor);

    @Query("select p.Classrooms from Professor p  where p.registerProfessor = :registerProfessor")
    List<Classroom> findClassroomsByregisterProfessor(Integer registerProfessor);

    @Query("select p.Location from Professor p where p.registerProfessor = :registerProfessor")
    Location findLocationByregisterProfessor(Integer registerProfessor);

}