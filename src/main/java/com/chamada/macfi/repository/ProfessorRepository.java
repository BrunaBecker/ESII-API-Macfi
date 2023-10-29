package com.chamada.macfi.repository;

import com.chamada.macfi.model.Localizacao;
import com.chamada.macfi.model.Professor;
import com.chamada.macfi.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("select p from Professor p where p.SIAPE = :siape")
    Professor findBySIAPE(Integer siape);

    @Query("select p.turmas from Professor p  where p.SIAPE = :siape")
    List<Turma> findTurmasBySIAPE(Integer siape);

    @Query("select p.localizacao from Professor p where p.SIAPE = :siape")
    Localizacao findLocalizacaoBySIAPE(Integer siape);

}