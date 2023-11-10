//package com.macfi.controllertests;
//
//
//import com.macfi.controller.*;
//import com.macfi.model.Attendance;
//import com.macfi.model.AttendanceStatus;
//import com.macfi.model.Classroom;
//import com.macfi.model.VirtualZone;
//import com.macfi.repository.*;
//import com.macfi.service.*;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.Duration;
//import java.time.LocalTime;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.hamcrest.Matchers.hasSize;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = AttendanceController.class)
//public class AttendanceControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AttendanceService attendanceService;
//    @MockBean
//    private AttendanceStatusService attendanceStatusService;
//    @MockBean
//    private CalendarService calendarService;
//    @MockBean
//    private ClassroomService classroomService;
//    @MockBean
//    private EventService eventService;
//    @MockBean
//    private LocationService locationService;
//    @MockBean
//    private NotificationService notificationService;
//    @MockBean
//    private PingService pingService;
//    @MockBean
//    private ProfessorService professorService;
//    @MockBean
//    private SettingService settingService;
//    @MockBean
//    private StudentService studentService;
//    @MockBean
//    private WaiverService waiverService;
//    @MockBean
//    private AttendanceStatusRepository attendanceStatusRepository;
//    @MockBean
//    private CalendarRepository calendarRepository;
//    @MockBean
//    private ClassroomRepository classroomRepository;
//    @MockBean
//    private EventRepository eventRepository;
//    @MockBean
//    private LocationRepository locationRepository;
//    @MockBean
//    private NotificationRepository notificationRepository;
//    @MockBean
//    private PingRepository pingRepository;
//    @MockBean
//    private ProfessorRepository professorRepository;
//    @MockBean
//    private SettingRepository settingRepository;
//    @MockBean
//    private StudentRepository studentRepository;
//    @MockBean
//    private WaiverRepository waiverRepository;
//    @MockBean
//    private AttendanceStatusController attendanceStatusController;
//    @MockBean
//    private CalendarController calendarController;
//    @MockBean
//    private ClassroomController classroomController;
//    @MockBean
//    private EventController eventController;
//    @MockBean
//    private LocationController locationController;
//    @MockBean
//    private NotificationController notificationController;
//    @MockBean
//    private PingController pingController;
//    @MockBean
//    private ProfessorController professorController;
//    @MockBean
//    private SettingController settingController;
//    @MockBean
//    private StudentController studentController;
//    @MockBean
//    private WaiverController waiverController;
//
//
//    @Test
//    public void testCreateAttendance() throws Exception {
//
//        // Arrange
//        Date date = new Date(); // Use the current date for simplicity
//        String supportingText = "Test Attendance";
//        String supportingText2 = "Test Attendance2";
//        LocalTime startHour = LocalTime.of(9, 0); // 9 AM
//        LocalTime endHour = LocalTime.of(10, 30); // 10:30 AM
//        Duration duration = Duration.between(startHour, endHour); // Calculate duration
//        boolean isAutomatic = false;
//        boolean isHappening = true;
//
//        // Mock the dependencies
//        VirtualZone virtualZone = mock(VirtualZone.class);
//        Classroom classroom = mock(Classroom.class);
//        AttendanceStatus attendanceStatusMock = mock(AttendanceStatus.class);
//        List<AttendanceStatus> statusStudentAttendance = Collections.singletonList(attendanceStatusMock);
//
//        // Act
//        Attendance attendance = new Attendance(
//                date,
//                supportingText,
//                startHour,
//                endHour,
//                duration,
//                isAutomatic,
//                isHappening,
//                virtualZone,
//                classroom,
//                statusStudentAttendance);
//
//        // Assert
//        assertNotNull(attendance);
//        assertEquals(date, attendance.getDate());
//        assertEquals(supportingText, attendance.getSupportingText());
//        assertEquals(startHour, attendance.getStartHour());
//        assertEquals(endHour, attendance.getEndHour());
//        assertEquals(duration, attendance.getDuration());
//        assertFalse(attendance.isAutomatic());
//        assertTrue(attendance.isHappening());
//        assertSame(virtualZone, attendance.getVirtualZone());
//        assertSame(classroom, attendance.getClassroom());
//        assertEquals(statusStudentAttendance, attendance.getAttendancesStatuses());
//
//
//        List<Attendance> attendances = List.of(attendance);
//
//
//        when(attendanceService.getAttendances()).thenReturn(attendances);
//
//
//        this.mockMvc.perform(get("/attendance")).andExpect(status().isOk())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].supportingText", containsString(supportingText)));
//
//
//    }
//
//
//}
