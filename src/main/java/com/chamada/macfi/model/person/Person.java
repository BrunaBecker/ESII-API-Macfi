package com.chamada.macfi.model.person;

import com.chamada.macfi.model.Setting;
import jakarta.persistence.*;

import java.util.Date;


@MappedSuperclass
@Entity
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nome;
    protected String nomeSocial;
    protected Date dataNascimento;
    protected Boolean isActive;
    protected String CPF;
    protected String email;
    protected String senha;

    @OneToOne
    protected Setting setting;

    //todo procurar como a gente salva imagens no banco
    String imagem;


}
