package com.chamada.macfi.model.utils;

import lombok.ToString;

import java.util.Date;


@ToString
public abstract class NumeroIdentificador {

    private String identificator;

    private Date dateStarted;

    private Date dateFinished;

    private boolean isActive;

}
