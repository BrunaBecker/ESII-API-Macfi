package com.macfi;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.*;
import com.macfi.model.person.Professor;
import com.macfi.model.person.RegisterCollegeID;
import com.macfi.model.person.Student;
import com.macfi.model.utils.*;
import com.macfi.model.utils.enums_class.EventStatus;
import com.macfi.model.utils.enums_class.StatusNotification;
import com.macfi.model.utils.enums_class.StatusPing;
import com.macfi.model.utils.enums_class.StudentAtAttendanceState;
import com.macfi.repository.*;
import com.macfi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class MacfiApplication implements CommandLineRunner {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private WaiverRepository waiverRepository;


    public static void main(String[] args) {
        SpringApplication.run(MacfiApplication.class, args);
    }


    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
//            storageService.deleteAll();
//            storageService.init();
        };
    }


    /*
     *   Person
     *   Professor
     *   Student
     *   RegisterCollegeID
     *   RegisterProfessor
     *   RegisterStudent
     *   Classroom
     *   Coordinate
     *   Location
     *   Setting
     *   FileMacFi
     *   Picture
     *   Atendance
     *   AttendanceStatus
     *   Calendar
     *   Event
     *   Notification
     *   Ping
     *   VirtualZone
     *   Waiver
     */

    @Override
    public void run(String... args) {


        try {
            System.out.println("Criando Professores...");
            for (int i = 1; i <= 10; i++) {
                Professor professor = new Professor("Professor " + i, "Professore " + i, new Date(), true, "1234567890" + (i - 1), "professor" + i + "@ic.uff.br", "senha", null, null, null, new ArrayList<>(), new ArrayList<>(), new ArrayList<Location>(), new ArrayList<Classroom>());
                RegisterCollegeID registerProfessor = new RegisterCollegeID("123456" + (i - 1), new Date(), null, true, professor);
                Setting setting = new Setting(false, false, false, professor);
                Picture profileImage = new Picture("https://imagem.com", "imagem.png", "png", 10, new Date());

                professor.setRegister(registerProfessor);
                professor.setProfileImage(profileImage);
                professor.setSetting(setting);

                professorRepository.save(professor);
            }
            System.out.println("Criando Estudantes.");
//        Long id, String name, String socialName, Date birthDate, Boolean isActive, String cpf, String email, String password, RegisterCollegeID register, Setting setting, Picture profileImagem, List<Comment> commentList, List<Notification> notificationList, List<Classroom> classrooms, List< Waiver > waivers, List<Attendance> attendances
            for (int i = 1; i <= 10; i++) {
                Student student = new Student("Aluno " + (i + 10), "Alune " + (i + 10), new Date(), true, "123456789" + (i + 9), "aluno" + (i + 10) + "@id.uff.br", "senha", null, null, null, new ArrayList<Comment>(), new ArrayList<Notification>(), new ArrayList<Classroom>(), new ArrayList<Waiver>(), new ArrayList<AttendanceStatus>());
                RegisterCollegeID registerStudent = new RegisterCollegeID("1234567" + (i + 9), new Date(), null, true, student);
                Setting setting = new Setting(false, false, false, student);
                Picture profileImage = new Picture("https://imagem.com", "imagem.png", "png", 10, new Date());
                student.setRegister(registerStudent);
                student.setProfileImage(profileImage);
                student.setSetting(setting);

                studentRepository.save(student);
            }
            Calendar calendar = new Calendar(new ArrayList<Event>());
            calendarRepository.save(calendar);
//        Long id, String name, String code, String semester, Location defaultLocation, Professor professor, List<Student> students, List<Attendance> attendances
            for (int i = 1; i <= 10; i++) {
                Professor professor = professorRepository.findById((long) i).get();
                Student student = studentRepository.findById((long) (i + 10)).get();


                ArrayList<Student> students = new ArrayList<Student>();
                students.add(student);
                Classroom classroom = new Classroom(
                        "classroom " + i,
                        "A" + i,
                        "TCC:" + i,
                        "223",
                        LocalTime.of(9, 0),
                        LocalTime.of(11, 0),
                        new Location(),
                        professor,
                        students,
                        new ArrayList<Attendance>(),
                        new ArrayList<Event>()
                );
                Attendance attendance = new Attendance(new Date(), "supportText", LocalTime.now().plusHours(2), LocalTime.now(), false, true, null, classroom, new ArrayList<AttendanceStatus>());
                AttendanceStatus attendanceStatus = new AttendanceStatus(StudentAtAttendanceState.present, false, student, attendance, new ArrayList<Ping>(), new ArrayList<Ping>(), null);
                Location location = new Location("location " + i, "location", false, null, professor, new ArrayList<VirtualZone>());
                Coordinate coordinate = new Coordinate(1234.0, 1234.0);
                Event event = new Event("Evento " + i, new Date(), "evento", classroom, EventStatus.classNormal, calendarRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Calendar not found")));
                Notification notificationOne = new Notification("Evento " + i, "support text", StatusNotification.normal, true, false, professor);
                Notification notificationTwo = new Notification("Evento " + (i + 10), "support text", StatusNotification.normal, true, false, student);
                Ping pingS = new Ping("123.456.789.000", new Date(), StatusPing.successful, false, coordinate, attendanceStatus);
                VirtualZone virtualZone = new VirtualZone(location, attendance);
                Waiver waiver = new Waiver(new FileMacFI("https://file.pdf", "file.pdf", "pdf", 10, new Date()),
                        "waiver", new Date(), new Date(), false, null, student, attendanceStatus);
                Comment comment = new Comment("content", student, null, waiver);

                attendanceStatus.getSuccessfulPings().add(pingS);
                attendanceStatus.setWaiver(waiver);
                attendance.getAttendancesStatuses().add(attendanceStatus);
                attendance.setVirtualZone(virtualZone);
                location.getVirtualZones().add(virtualZone);
                location.setCoordinate(coordinate);
                classroom.setDefaultLocation(location);
                classroom.getAttendances().add(attendance);
                classroomRepository.save(classroom);

                eventRepository.save(event);

                calendar.getEvents().add(event);
                event.setCalendar(calendar);
                eventRepository.save(event);
                calendarRepository.save(calendar);

                if (professor != null) {
                    professor.getNotifications().add(notificationOne);
                    professor.getClassrooms().add(classroom);
                    professor.getLocations().add(location);
                    professorRepository.save(professor);
                }

                if (student != null) {
                    student.getNotifications().add(notificationTwo);
                    student.getClassrooms().add(classroom);
                    studentRepository.save(student);
                }

                waiver.setComment(comment);
                waiverRepository.save(waiver);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}