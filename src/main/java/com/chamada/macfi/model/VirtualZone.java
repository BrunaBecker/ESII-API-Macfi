package com.chamada.macfi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class VirtualZone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Localizacao localizacao;

    @OneToOne
    private Chamada chamada;

    //todo metodo que dado uma lat e long verifica se o aluno ta em aula

}
