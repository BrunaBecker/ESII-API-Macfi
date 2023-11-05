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
    @JoinColumn(name="location_id", referencedColumnName = "id")
    private Location location;

    @OneToOne(mappedBy = "virtualZone")
    private Attendance attendance;

    public VirtualZone(Long id, Location location, Attendance attendance) {
        this.id = id;
        this.location = location;
        this.attendance = attendance;
    }
}
