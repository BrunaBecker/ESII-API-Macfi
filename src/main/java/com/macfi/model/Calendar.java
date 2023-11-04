package com.macfi.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    @JoinTable(name = "calendar_id", joinColumns = @JoinColumn(name = "calendarId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "eventId", referencedColumnName = "id"))
    private List<Event> events;
}
