package com.chamada.macfi.model.person;

import com.chamada.macfi.model.Setting;
import jakarta.persistence.*;

import java.util.Date;


@MappedSuperclass
@Entity
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    protected String socialName;
    protected Date birthDate;
    protected Boolean isActive;
    protected String CPF;
    protected String email;
    protected String password;

    @OneToOne
    protected Setting setting;

    //todo procurar como a gente salva imagens no banco
    String imagem;


}
