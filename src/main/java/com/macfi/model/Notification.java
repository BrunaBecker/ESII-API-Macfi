package com.macfi.model;

import com.macfi.model.person.Person;
import com.macfi.model.utils.enums_class.StatusNotification;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;

    private String supportingText;

    private StatusNotification statusNotification;

    private boolean isActive;

    private boolean isRead;

    @ManyToOne
    private Person person;

}
