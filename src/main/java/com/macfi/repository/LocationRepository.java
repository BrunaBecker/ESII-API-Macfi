package com.macfi.repository;

import com.macfi.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("select l from Location l where l.professor.register.identifier = :identifier")
    List<Location> findByProfessor(String identifier);
}
