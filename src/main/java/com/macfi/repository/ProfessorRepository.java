package com.macfi.repository;

import com.macfi.model.Classroom;
import com.macfi.model.Location;
import com.macfi.model.person.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends PersonRepository<Professor, Long> {

    @Query("select a from Professor a left join fetch Person p where a.register.identifier = :identifier and p.register.identifier = :identifier")
    Professor findByIdentifier(String identifier);


    @Query("select a from Professor a left join fetch Person p where a.email = :email and p.email = :email")
    Professor findByEmail(String email);



    @Query("select ps from Professor ps left join fetch Person p where p.id = ps.id ")
    List<Professor> findAllByRepository();


    @Query("select p from Professor p left join fetch Person ps left join fetch Classroom c  where p.id = ps.id and c.code = :code")
    Professor findByClassroomCode(String code);

}