package com.macfi.model;

import com.macfi.model.utils.enums_class.EventStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name; //sera a data

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private EventStatus status;

    private String description;

    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "calendar_id", referencedColumnName = "id")
    @ToString.Exclude
    private Calendar calendar;

    public Event(String name, Date date, String description, Classroom classroom, EventStatus status, Calendar calendar) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.classroom = classroom;
        this.status = status;
        this.calendar = calendar;
    }
}
