package com.macfi.model.utils;

import lombok.ToString;

import java.util.Date;


@ToString
public abstract class RegisterCollegeID {

    private String identifier;

    private Date dateStarted;

    private Date dateFinished;

    private boolean isActive;

}
