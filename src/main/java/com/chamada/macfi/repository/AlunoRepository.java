package com.chamada.macfi.repository;

import com.chamada.macfi.model.Aluno;
import com.chamada.macfi.model.Turma;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query("select a from Aluno a join a.matricula m where m.codigo = :matricula")
    Aluno findByMatricula(Integer matricula);

    @Query("select a.turmas from Aluno a join a.matricula m where m.codigo = :matricula")
    List<Turma> findTurmasByMatricula(Integer matricula);

}
