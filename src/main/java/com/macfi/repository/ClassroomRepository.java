package com.macfi.repository;

import com.macfi.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {


    @Query("select t from Classroom t where t.professor.register.identifier = :identifier")
    List<Classroom> findByProfessor(String identifier);

    @Query("select t from Classroom t join Student a where a.register.identifier = :identifier")
    List<Classroom> findByStudent(String identifier);

}
