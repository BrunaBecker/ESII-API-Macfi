package com.chamada.macfi.model.person;

import com.chamada.macfi.model.utils.NumeroIdentificador;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Matricula extends NumeroIdentificador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "matricula")
    private Student Student;
}
