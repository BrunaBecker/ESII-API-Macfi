package com.macfi.repository;

import com.macfi.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    @Query("select t from Classroom t where t.codigo = :codigo")
    Classroom findByCodigo(Integer codigo);

    @Query("select t from Classroom t join Professor p where p.registerProfessor = :registerProfessor")
    List<Classroom> findByregisterProfessor(Integer registerProfessor);

    @Query("select t from Classroom t join Student a where a.registerStudent.codigo = :registerStudent")
    List<Classroom> findByRegisterStudent(Integer registerStudent);

}
