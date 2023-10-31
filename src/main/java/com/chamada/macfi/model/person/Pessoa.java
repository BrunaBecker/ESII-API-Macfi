package com.chamada.macfi.model;

import java.util.Date;

public abstract class Pessoa {
    String nome;
    String nomeSocial;
    Date dataNascimento;
    Boolean isActive;
    String CPF;
    String email;
    String senha;

    //todo procurar como a gente salva imagens no banco
    String imagem;
}
