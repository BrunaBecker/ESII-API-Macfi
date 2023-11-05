package com.macfi.model;

import com.macfi.model.utils.Coordinate;
import com.macfi.model.utils.GeoLocation;
import com.macfi.model.utils.enums_class.StatusPing;
import jakarta.persistence.*;
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
@Table(name = "ping")
public class Ping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String ip;


    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private StatusPing status;

    private boolean isContinuos;

    @OneToOne
    @JoinColumn(name = "coordinate_id", referencedColumnName = "id")
    private Coordinate coordinate;

    @ManyToOne
    @JoinColumn(name = "attendance_status_id", referencedColumnName = "id")
    private AttendanceStatus attendanceStatus;

    public Ping(Long id, String ip, Date date, StatusPing status, boolean isContinuos, Coordinate coordinate, AttendanceStatus attendanceStatus) {
        this.id = id;
        this.ip = ip;
        this.date = date;
        this.status = status;
        this.isContinuos = isContinuos;
        this.coordinate = coordinate;
        this.attendanceStatus = attendanceStatus;
    }

    public boolean inCorrectLocation(double lat1, double lon1, double lat2, double lon2) {
        return GeoLocation.inRadiusMacfi(lat1, lon1, lat2, lon2);
    }
}
