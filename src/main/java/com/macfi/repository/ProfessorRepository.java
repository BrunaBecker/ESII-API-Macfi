package com.macfi.repository;

import com.macfi.model.person.Professor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends PersonRepository<Professor, Long> {

    @Query("select p from Person p where type(p) = Professor and p.register.identifier = :identifier")
    Professor findByIdentifier(String identifier);


    @Query("select a from Professor a left join fetch Person p on a.email = :email and p.email = :email")
    Professor findByEmail(String email);


    @Query("select professor from Professor professor left join fetch Person person on person.id = professor.id left join fetch Classroom c on c.professor.id = professor.id where c.code = :code")
    Professor findByClassroomCode(String code);

    @Query("select p from Person p where type(p) = Professor")
    List<Professor> findAllByRepository();
}