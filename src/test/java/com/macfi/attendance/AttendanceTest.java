package com.macfi.attendance;

import com.macfi.model.Attendance;
import com.macfi.model.AttendanceStatus;
import com.macfi.model.Classroom;
import com.macfi.model.VirtualZone;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.AttendanceDto;
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
        assertEquals(duration, attendance.getDuration(attendance.getStartHour(), attendance.getEndHour()), "Duration should match");
        assertFalse(attendance.isAutomatic(), "isAutomatic should be false");
        assertTrue(attendance.isHappening(), "isHappening should be true");
        assertSame(virtualZone, attendance.getVirtualZone(), "VirtualZone object should match");
        assertSame(classroom, attendance.getClassroom(), "Classroom object should match");
        assertEquals(statusStudentAttendance, attendance.getAttendancesStatuses(), "Attendance statuses should match");
    }
    @Test
    void whenConvertAttendanceEntityToAttendanceDto_thenCorrect() {

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
                isAutomatic,
                isHappening,
                virtualZone,
                classroom,
                statusStudentAttendance);

        AttendanceDto attendanceDto = modelMapping.getInstance().mapToDto(attendance, AttendanceDto.class);

        assertEquals(attendance.getDate(), attendanceDto.getDate(), "Date should match");
        assertEquals(attendance.getSupportingText(), attendanceDto.getSupportingText(), "Supporting text should match");
        assertEquals(attendance.getStartHour(), attendanceDto.getStartHour(), "Start hour should match");
        assertEquals(attendance.getEndHour(), attendanceDto.getEndHour(), "End hour should match");
        assertEquals(attendance.isAutomatic(), attendanceDto.isAutomatic(), "isAutomatic should match");
        assertEquals(attendanceDto.isHappening(), attendance.isHappening(), "isHappening should match");
//        assertEquals(attendance.getVirtualZone().getId(), attendanceDto.getVirtualZone().getId(), "VirtualZone object should match");
//        assertSame(attendance.getClassroom(), attendanceDto.getClassroom(), "Classroom object should match");
    }
}