package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.model.AttendanceStatus;
import com.macfi.model.Classroom;
import com.macfi.model.Waiver;
import com.macfi.model.person.Student;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.AttendanceStatusDto;
import com.macfi.payload.ClassroomDto;
import com.macfi.payload.StudentDto;
import com.macfi.payload.WaiverDto;
import com.macfi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private WaiverRepository waiverRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private AttendanceStatusRepository attendanceStatusRepository;


    public StudentDto createStudent(StudentDto studentDto) {
        Student student = modelMapping.getInstance().mapToEntity(studentDto, Student.class);
        return modelMapping.getInstance().mapToDto(studentRepository.save(student), StudentDto.class);
    }


    public StudentDto getStudentById(Long id) {
        return modelMapping.getInstance().mapToDto(studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found")), StudentDto.class);
    }

    public StudentDto getStudentByIdentifier(String identifier) {
        Student s = studentRepository.findByIdentifier(identifier);
        if (s == null) {
            throw new EntityNotFoundException("Student not found");
        }
        return modelMapping.getInstance().mapToDto(s, StudentDto.class);
    }

    public StudentDto updateStudent(StudentDto studentDto) {
        studentRepository.findById(studentDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        Student student1 = modelMapping.getInstance().mapToEntity(studentDto, Student.class);
        return modelMapping.getInstance().mapToDto(studentRepository.save(student1), StudentDto.class);
    }

    public List<StudentDto> getStudents() {
        List<Student> students = studentRepository.findAllByRepository();
        return students.stream().map(student -> modelMapping.getInstance().mapToDto(student, StudentDto.class)).collect(Collectors.toList());
    }

    public List<StudentDto> getStudentsByClassroom(Long id) {
        classroomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        List<Student> students = studentRepository.findByClassroomId(id);
        return students.stream().map(student -> modelMapping.getInstance().mapToDto(student, StudentDto.class)).collect(Collectors.toList());
    }

    public StudentDto getStudentByWaiver(Long idWaiver) {
        waiverRepository.findById(idWaiver).orElseThrow(() -> new EntityNotFoundException("Waiver not found"));
        return modelMapping.getInstance().mapToDto(studentRepository.findByWaiverId(idWaiver), StudentDto.class);
    }

    public List<StudentDto> getStudentsByAttendance(Long idAttendance) {
        attendanceRepository.findById(idAttendance).orElseThrow(() -> new EntityNotFoundException("Attendance not found"));
        List<Student> students = studentRepository.findAllByAttendanceId(idAttendance);
        return students.stream().map(student -> modelMapping.getInstance().mapToDto(student, StudentDto.class)).collect(Collectors.toList());
    }

    public List<StudentDto> getStudentsByAttendanceHappening(Long idAttendance) {
        List<Student> students = studentRepository.findAllByAttendanceIsHappeningId(idAttendance);
        return students.stream().map(student -> modelMapping.getInstance().mapToDto(student, StudentDto.class)).collect(Collectors.toList());
    }

    public StudentDto getStudentByEmail(String email) {
        return modelMapping.getInstance().mapToDto(studentRepository.findByEmail(email), StudentDto.class);
    }


    public StudentDto login(String identifier, String password) {
        StudentDto studentDto = getStudentByIdentifier(identifier);
        if (studentDto.getPassword().equals(password) && studentDto.getIsActive()) {
            return studentDto;
        } else {
            throw new UserUnauthorized("password or identify incorrect");
        }
    }

    public StudentDto setClassroom(Long idClass, String identifier) {
        Student student = studentRepository.findByIdentifier(identifier);
        Classroom classroom = classroomRepository.findById(idClass).orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        student.getClassrooms().add(classroom);
        return modelMapping.getInstance().mapToDto(studentRepository.save(student), StudentDto.class);
    }

    public List<StudentDto> getStudentsByClassroomCode(String code, String className, String semester) {
        List<Student> student = studentRepository.findByClassroomsCodeAndClassroomsClassNameAndClassroomsSemester(code, className, semester);
        return student.stream().map(student1 -> modelMapping.getInstance().mapToDto(student1, StudentDto.class)).collect(Collectors.toList());
    }

    public StudentDto addClassroom(ClassroomDto classroomDto, String identifier) {
        Student student = studentRepository.findByIdentifier(identifier);
        Classroom classroom = classroomRepository.findById(classroomDto.getId()).orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        student.getClassrooms().add(classroom);
        return modelMapping.getInstance().mapToDto(studentRepository.save(student), StudentDto.class);
    }

    public StudentDto addAttendance(AttendanceStatusDto attendanceStatusDto, String identifier) {
        Student student = studentRepository.findByIdentifier(identifier);
        if (student == null) {
            throw new EntityNotFoundException("Student not found");
        }

        AttendanceStatus attendanceStatus;
        try {
            attendanceStatus = attendanceStatusRepository.findById(attendanceStatusDto.getId()).orElseThrow(() -> new EntityNotFoundException("Attendance not found"));
        } catch (EntityNotFoundException e) {
            attendanceStatus = modelMapping.getInstance().mapToEntity(attendanceStatusDto, AttendanceStatus.class);
        }
        student.getAttendanceStatuses().add(attendanceStatus);
        return modelMapping.getInstance().mapToDto(studentRepository.save(student), StudentDto.class);
    }

    public StudentDto setAttendance(Long idAttendance, String idStudent) {
        Student student = studentRepository.findByIdentifier(idStudent);
        if (student == null) {
            throw new EntityNotFoundException("Student not found");
        }
        AttendanceStatus attendance = attendanceStatusRepository.findById(idAttendance).orElseThrow(() -> new EntityNotFoundException("Attendance not found"));
        student.getAttendanceStatuses().add(attendance);
        return modelMapping.getInstance().mapToDto(studentRepository.save(student), StudentDto.class);
    }

    public StudentDto addWaiver(WaiverDto waiverDto, String identifier) {
        Student student = studentRepository.findByIdentifier(identifier);
        if (student == null) {
            throw new EntityNotFoundException("Student not found");
        }
        Waiver waiver;
        try {
            waiver = waiverRepository.findById(waiverDto.getId()).orElseThrow(() -> new EntityNotFoundException("Waiver not found"));

        } catch (EntityNotFoundException e) {
            waiver = modelMapping.getInstance().mapToEntity(waiverDto, Waiver.class);
        }
        student.getWaivers().add(waiver);
        return modelMapping.getInstance().mapToDto(studentRepository.save(student), StudentDto.class);

    }

    public StudentDto setWaiver(Long idWaiver, String idStudent) {
        Student student = studentRepository.findByIdentifier(idStudent);
        if (student == null) {
            throw new EntityNotFoundException("Student not found");
        }
        Waiver waiver = waiverRepository.findById(idWaiver).orElseThrow(() -> new EntityNotFoundException("Waiver not found"));
        student.getWaivers().add(waiver);
        return modelMapping.getInstance().mapToDto(studentRepository.save(student), StudentDto.class);
    }
}
