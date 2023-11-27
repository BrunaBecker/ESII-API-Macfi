package com.macfi.model;

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
@Table(name = "virtual_zone")
public class VirtualZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Location location;

    @OneToOne(mappedBy = "virtualZone", cascade = CascadeType.ALL)
    private Attendance attendance;


    public VirtualZone(Location location, Attendance attendance) {
        this.location = location;
        this.attendance = attendance;
    }
}
