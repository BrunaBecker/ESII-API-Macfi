package com.chamada.macfi.model.utils;

import lombok.ToString;

import java.util.Date;


@ToString
public abstract class RegisterCollegeID {

    private String indentifier;

    private Date dateStarted;

    private Date dateFinished;

    private boolean isActive;

}
