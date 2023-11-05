package com.macfi;

import com.macfi.controller.ProfessorController;
import com.macfi.model.*;
import com.macfi.model.person.Professor;
import com.macfi.model.person.RegisterProfessor;
import com.macfi.model.person.RegisterStudent;
import com.macfi.model.person.Student;
import com.macfi.model.utils.Comment;
import com.macfi.model.utils.Coordinate;
import com.macfi.model.utils.Picture;
import com.macfi.model.utils.RegisterCollegeID;
import com.macfi.repository.ClassroomRepository;
import com.macfi.repository.ProfessorRepository;
import com.macfi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    private ProfessorController professorController;

    public static void main(String[] args) {
        SpringApplication.run(MacfiApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 10; i++){
            Professor professor = new Professor(Long.valueOf(i), "Professor "+i, "Professore "+i, new Date(), true, "1234567890"+(i-1), "professor"+i+"@ic.uff.br", "senha", null, null, null, new ArrayList<Comment>(), new ArrayList<Notification>(), new ArrayList<Location>(), new ArrayList<Classroom>());
            RegisterProfessor registerProfessor = new RegisterProfessor("123456"+(i-1), new Date(), null, true, professor);
            Setting setting = new Setting(Long.valueOf(i), false, false, false, professor);
            Picture profileImage = new Picture(Long.valueOf(i), "https://imagem.com", "imagem.png", "png", 10, new Date());
            professor.setRegister(registerProfessor);
            professor.setProfileImage(profileImage);
            professor.setSetting(setting);
            professorRepository.save(professor);
        }

//        Long id, String name, String socialName, Date birthDate, Boolean isActive, String cpf, String email, String password, RegisterCollegeID register, Setting setting, Picture profileImagem, List<Comment> commentList, List<Notification> notificationList, List<Classroom> classrooms, List< Waiver > waivers, List<Attendance> attendances
        for (int i = 1; i <= 10; i++){
            Student student = new Student(Long.valueOf(i+10), "Aluno "+(i+10), "Alune "+(i+10), new Date(), true, "123456789"+(i+9), "aluno"+(i+10)+"@id.uff.br", "senha", null, null, null, new ArrayList<Comment>(), new ArrayList<Notification>(), new ArrayList<Classroom>(), new ArrayList< Waiver>(), new ArrayList<Attendance>());
            RegisterStudent registerStudent = new RegisterStudent("1234567"+(i+9), new Date(), null, true, student);
            Setting setting = new Setting(Long.valueOf(i+10), false, false, false, student);
            Picture profileImage = new Picture(Long.valueOf(i+10), "https://imagem.com", "imagem.png", "png", 10, new Date());
            student.setRegister(registerStudent);
            student.setProfileImage(profileImage);
            student.setSetting(setting);

            studentRepository.save(student);
        }

//        Long id, String name, String code, String semester, Location defaultLocation, Professor professor, List<Student> students, List<Attendance> attendances
        for (int i = 1; i <= 10; i++){
            Professor professor = professorController.getProfessorById(Long.valueOf(i));

            ArrayList<Student> students = new ArrayList<Student>();
            students.add(studentRepository.getReferenceById(Long.valueOf(i+10)));
            Classroom classroom = new Classroom(Long.valueOf(i), "classroom "+i, ""+i, "223", null, professor, students, new ArrayList<Attendance>());

            Location location = new Location(Long.valueOf(i), "location "+i, "location", false, null, professor, new ArrayList<VirtualZone>());
            Coordinate coordinate = new Coordinate((long) i, 1234.0, 1234.0);

            location.setCoordinate(coordinate);
            classroom.setDefaultLocation(location);

            classroomRepository.save(classroom);
        }


    }

}