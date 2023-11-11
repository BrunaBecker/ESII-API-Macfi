package com.macfi.attendance;

import com.macfi.model.Attendance;
import com.macfi.model.AttendanceStatus;
import com.macfi.model.Classroom;
import com.macfi.model.VirtualZone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class AttendanceTest {
    @Test
    void createAndValidateAttendance() {
        // Arrange
        Date date = new Date(); // Use the current date for simplicity
        String supportingText = "Test Attendance";
        LocalTime startHour = LocalTime.of(9, 0); // 9 AM
        LocalTime endHour = LocalTime.of(10, 30); // 10:30 AM
        Duration duration = Duration.between(startHour, endHour); // Calculate duration
        boolean isAutomatic = false;
        boolean isHappening = true;

        VirtualZone virtualZone = new VirtualZone(); // Replace with mock if needed
        Classroom classroom = new Classroom(); // Replace with mock if needed
        AttendanceStatus attendanceStatusMock = new AttendanceStatus(); // Replace with mock if needed
        List<AttendanceStatus> statusStudentAttendance = Collections.singletonList(attendanceStatusMock);

        // Act
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
        assertNotNull(attendance, "Attendance object should not be null");
        assertEquals(date, attendance.getDate(), "Date should match");
        assertEquals(supportingText, attendance.getSupportingText(), "Supporting text should match");
        assertEquals(startHour, attendance.getStartHour(), "Start hour should match");
        assertEquals(endHour, attendance.getEndHour(), "End hour should match");
        assertEquals(duration, attendance.getDuration(), "Duration should match");
        assertFalse(attendance.isAutomatic(), "isAutomatic should be false");
        assertTrue(attendance.isHappening(), "isHappening should be true");
        assertSame(virtualZone, attendance.getVirtualZone(), "VirtualZone object should match");
        assertSame(classroom, attendance.getClassroom(), "Classroom object should match");
        assertEquals(statusStudentAttendance, attendance.getAttendancesStatuses(), "Attendance statuses should match");
    }
    @Test
    void whenConvertAttendanceEntityToAttendanceDto_thenCorrect() {

    }
}