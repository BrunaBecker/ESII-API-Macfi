package com.macfi.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "calendar")
@NoArgsConstructor
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @ManyToMany
    @JoinTable(name = "calendar_id", joinColumns = @JoinColumn(name = "calendarId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "eventId", referencedColumnName = "id"))
    @ToString.Exclude
    private List<Event> events;

    public Calendar(List<Event> events) {
        this.events = events;
    }

    public boolean addEvent(Event event) {
        return events.add(event);
    }


}
