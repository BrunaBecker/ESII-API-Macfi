package com.macfi.model.utils;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@Entity
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Double latitude;
    private Double longitude;


}
