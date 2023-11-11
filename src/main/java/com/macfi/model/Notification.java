package com.macfi.model;

import com.macfi.model.person.Person;
import com.macfi.model.utils.enums_class.StatusNotification;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "notification")
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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "person_id")
    private Person person;

    public Notification(String title, String supportingText, StatusNotification statusNotification, boolean isActive, boolean isRead, Person person) {
        this.title = title;
        this.supportingText = supportingText;
        this.statusNotification = statusNotification;
        this.isActive = isActive;
        this.isRead = isRead;
        this.person = person;
    }

}
