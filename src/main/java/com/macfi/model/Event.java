package com.macfi.model;

import com.macfi.model.utils.enums_class.eventStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name; //sera a data

    private Date date;

    private String description;

    @OneToOne
    private Classroom classroom;

    private eventStatus status;

    @ManyToMany(mappedBy = "events")
    private List<Calendar> calendar;
}
