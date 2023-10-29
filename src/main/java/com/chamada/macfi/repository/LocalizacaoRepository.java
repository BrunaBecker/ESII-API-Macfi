package com.chamada.macfi.repository;

import com.chamada.macfi.model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {

    @Query("select l from Localizacao l where l.id = :id")
    Localizacao findByID(Long id);

    @Query("select l from Localizacao l where l.Professor.SIAPE = :SIAPE")
    Localizacao findBySIAPE(Integer SIAPE);

    @Query("select l from Localizacao l where l.CEP = :CEP")
    Localizacao findByCEP(Integer CEP);

}
