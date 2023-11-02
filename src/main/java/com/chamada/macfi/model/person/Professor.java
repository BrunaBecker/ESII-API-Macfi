package com.chamada.macfi.model.person;

import com.chamada.macfi.model.Localizacao;
import com.chamada.macfi.model.Turma;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Professor extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Siape siape;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.PERSIST)
    private Localizacao localizacao;

    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.PERSIST)
    private List<Turma> turmas;



}
