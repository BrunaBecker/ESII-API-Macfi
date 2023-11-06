package com.macfi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AttendanceTest {

    @Test
    void addStatusStudentAttendance() {

    }

    @Test
    void removeStatusStudentAttendance() {
    }

    @Test
    void calculateDuration() {
    }

    @Test
    void getId() {
    }

    @Test
    void getDate() {
    }

    @Test
    void getSupportingText() {
    }

    @Test
    void getStartHour() {
    }

    @Test
    void getEndHour() {
    }

    @Test
    void getDuration() {
    }

    @Test
    void isAutomatic() {
    }

    @Test
    void isHappening() {
    }

    @Test
    void getVirtualZone() {
    }

    @Test
    void getClassroom() {
    }

    @Test
    void getStatusStudentAttendance() {
    }

    @Test
    void setId() {
    }

    @Test
    void setDate() {
    }

    @Test
    void setSupportingText() {
    }

    @Test
    void setStartHour() {
    }

    @Test
    void setEndHour() {
    }

    @Test
    void setDuration() {
    }

    @Test
    void setAutomatic() {
    }

    @Test
    void setHappening() {
    }

    @Test
    void setVirtualZone() {
    }

    @Test
    void setClassroom() {
    }

    @Test
    void setStatusStudentAttendance() {
    }

    @Test
    void testToString() {
    }

    @Test
    void createAttendance() {

        Date date = new Date(); // Use the current date for simplicity
        String supportingText = "Test Attendance";
        LocalTime startHour = LocalTime.of(9, 0); // 9 AM
        LocalTime endHour = LocalTime.of(10, 30); // 10:30 AM
        Duration duration = Duration.between(startHour, endHour); // Calculate duration
        boolean isAutomatic = false;
        boolean isHappening = true;

        VirtualZone virtualZone = mock(VirtualZone.class);
        Classroom classroom = mock(Classroom.class);
        AttendanceStatus attendanceStatusMock = mock(AttendanceStatus.class);
        List<AttendanceStatus> statusStudentAttendance = Collections.singletonList(attendanceStatusMock);

        Attendance attendance = new Attendance(
                date,
                supportingText,
                startHour,
                endHour,
                duration,
                isAutomatic,
                isHappening,
                virtualZone,
                classroom,
                statusStudentAttendance);

        // Assert
        assertNotNull(attendance);
        Assertions.assertEquals(date, attendance.getDate());
        Assertions.assertEquals(supportingText, attendance.getSupportingText());
        Assertions.assertEquals(startHour, attendance.getStartHour());
        Assertions.assertEquals(endHour, attendance.getEndHour());
        Assertions.assertEquals(duration, attendance.getDuration());
        assertFalse(attendance.isAutomatic());
        assertTrue(attendance.isHappening());
        assertSame(virtualZone, attendance.getVirtualZone());
        assertSame(classroom, attendance.getClassroom());
        Assertions.assertEquals(statusStudentAttendance, attendance.getAttendancesStatuses());
    }
}