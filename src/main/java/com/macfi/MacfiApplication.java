package com.macfi;

import com.macfi.controller.ProfessorController;
import com.macfi.controller.StudentController;
import com.macfi.model.*;
import com.macfi.model.person.Professor;
import com.macfi.model.person.RegisterProfessor;
import com.macfi.model.person.RegisterStudent;
import com.macfi.model.person.Student;
import com.macfi.model.utils.*;
import com.macfi.model.utils.enums_class.EventStatus;
import com.macfi.model.utils.enums_class.StatusNotification;
import com.macfi.model.utils.enums_class.StatusPing;
import com.macfi.model.utils.enums_class.StudentAtAttendanceState;
import com.macfi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
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

    @Autowired
    private StudentController studentController;

    @Autowired
    private ProfessorController professorController;

    public static void main(String[] args) {
        SpringApplication.run(MacfiApplication.class, args);
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

//        try {
            for (int i = 1; i <= 10; i++) {
                Professor professor = new Professor("Professor " + i, "Professore " + i, new Date(), true, "1234567890" + (i - 1), "professor" + i + "@ic.uff.br", "senha", null, null, null, new ArrayList<Comment>(), new ArrayList<Notification>(), new ArrayList<Location>(), new ArrayList<Classroom>());
                RegisterProfessor registerProfessor = new RegisterProfessor("123456" + (i - 1), new Date(), null, true, professor);
                Setting setting = new Setting(false, false, false, professor);
                Picture profileImage = new Picture("https://imagem.com", "imagem.png", "png", 10, new Date());

                professor.setRegister(registerProfessor);
                professor.setProfileImage(profileImage);
                professor.setSetting(setting);

                professorRepository.save(professor);
            }

//        Long id, String name, String socialName, Date birthDate, Boolean isActive, String cpf, String email, String password, RegisterCollegeID register, Setting setting, Picture profileImagem, List<Comment> commentList, List<Notification> notificationList, List<Classroom> classrooms, List< Waiver > waivers, List<Attendance> attendances
            for (int i = 1; i <= 10; i++) {
                Student student = new Student("Aluno " + (i + 10), "Alune " + (i + 10), new Date(), true, "123456789" + (i + 9), "aluno" + (i + 10) + "@id.uff.br", "senha", null, null, null, new ArrayList<Comment>(), new ArrayList<Notification>(), new ArrayList<Classroom>(), new ArrayList<Waiver>(), new ArrayList<Attendance>());
                RegisterStudent registerStudent = new RegisterStudent("1234567" + (i + 9), new Date(), null, true, student);
                Setting setting = new Setting(false, false, false, student);
                Picture profileImage = new Picture("https://imagem.com", "imagem.png", "png", 10, new Date());
                student.setRegister(registerStudent);
                student.setProfileImage(profileImage);
                student.setSetting(setting);

                studentRepository.save(student);
            }

//        Long id, String name, String code, String semester, Location defaultLocation, Professor professor, List<Student> students, List<Attendance> attendances
            for (int i = 1; i <= 10; i++) {
                Professor professor = professorController.getProfessorById(Long.valueOf(i));
                Student student = studentController.getStudentById(Long.valueOf(i + 10));

                ArrayList<Student> students = new ArrayList<Student>();
                students.add(student);
                Classroom classroom = new Classroom("classroom " + i, "" + i, "223", null, professor, students, new ArrayList<Attendance>());

                Attendance attendance = new Attendance(new Date(), "supportText", LocalTime.now(), LocalTime.now(), Duration.ofHours(2), false, false, null, classroom, new ArrayList<AttendanceStatus>());
                AttendanceStatus attendanceStatus = new AttendanceStatus(StudentAtAttendanceState.present, true, student, attendance, new ArrayList<Ping>(), new ArrayList<Ping>(), null);
                Location location = new Location("location " + i, "location", false, null, professor, new ArrayList<VirtualZone>());
                Coordinate coordinate = new Coordinate(1234.0, 1234.0);
                Calendar calendar = new Calendar(new ArrayList<Event>());
                Event event = new Event("Evento "+i, new Date(), "evento", classroom, EventStatus.classNormal, new ArrayList<Calendar>());
                Notification notificationOne = new Notification("Evento "+i, "support text", StatusNotification.normal, true, false, professor);
                Notification notificationTwo = new Notification("Evento "+(i+10), "support text", StatusNotification.normal, true, false, student);
                Ping pingS = new Ping("123.456.789.000", new Date(), StatusPing.successful, false, coordinate, attendanceStatus);
                VirtualZone virtualZone = new VirtualZone(location, attendance);
                Waiver waiver = new Waiver(new FileMacFI("https://file.pdf", "file.pdf", "pdf", 10, new Date()),
                        "waiver", new Date(), new Date(), false, null, student, attendanceStatus);
                Comment comment = new Comment("content", student, null, waiver);

                attendanceStatus.getSuccessfulPings().add(pingS);
                attendanceStatus.setWaiver(waiver);
                attendance.getStatusStudentAttendance().add(attendanceStatus);
                location.getVirtualZones().add(virtualZone);
                location.setCoordinate(coordinate);
                classroom.setDefaultLocation(location);
                classroom.getAttendances().add(attendance);
                classroomRepository.save(classroom);

                eventRepository.save(event);
                calendarRepository.save(calendar);
                calendar.getEvents().add(event);
                event.getCalendars().add(calendar);
                eventRepository.save(event);
                calendarRepository.save(calendar);

                professor.getNotifications().add(notificationOne);
                professor.getClassrooms().add(classroom);
                professor.getLocations().add(location);
                professorRepository.save(professor);


                student.getNotifications().add(notificationTwo);
                student.getClassrooms().add(classroom);
                studentRepository.save(student);

                waiver.setComment(comment);
                waiverRepository.save(waiver);
            }
//        } catch (Exception e){
//            System.out.println(e.getLocalizedMessage());
//        }


    }

}