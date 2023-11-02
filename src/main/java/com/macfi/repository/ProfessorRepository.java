package com.macfi.repository;

import com.macfi.model.Classroom;
import com.macfi.model.Location;
import com.macfi.model.person.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("select p from Professor p where p.register.identifier = :identifier")
    Professor findByIdentifier(String identifier);

    @Query("select p.classrooms from Professor p  where p.register.identifier = :identifier")
    List<Classroom> findClassroomsByIdentifier(String identifier);

    @Query("select p.locations from Professor p where p.register.identifier = :identifier")
    Location findLocationByIdentifier(String identifier);

}