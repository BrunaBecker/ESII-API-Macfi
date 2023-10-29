package com.chamada.macfi.repository;

import com.chamada.macfi.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

    @Query("select t from Turma t where t.codigo = :codigo")
    Turma findByCodigo(Integer codigo);

    @Query("select t from Turma t join Professor p where p.SIAPE = :siape")
    List<Turma> findBySIAPE(Integer siape);

    @Query("select t from Turma t join Aluno a where a.matricula.codigo = :matricula")
    List<Turma> findByMatricula(Integer matricula);

}
