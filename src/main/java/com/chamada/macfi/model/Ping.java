package com.chamada.macfi.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ping {

    private String ip;

    private Long id;

    private Date date;

    private boolean status;

    private AttendanceStatus attendanceStatus;

}
