package com.macfi;

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
            generateProfessors();

            System.out.println("Criando Estudantes...");
            generateStudents();

            System.out.println("Criando CalendÃ¡rio...");
            createCalendar();

//                Attendance attendance = new Attendance(new Date(), "supportText", LocalTime.now(), LocalTime.now(), Duration.ofHours(2), false, false, null, classroom, new ArrayList<AttendanceStatus>());
//                AttendanceStatus attendanceStatus = new AttendanceStatus(StudentAtAttendanceState.present, true, student, attendance, new ArrayList<Ping>(), new ArrayList<Ping>(), null);
//                Location location = new Location("location " + i, "location", false, null, professor, new ArrayList<VirtualZone>());
//                Coordinate coordinate = new Coordinate(1234.0, 1234.0);
//                Event event = new Event("Evento "+i, new Date(), "evento", classroom, EventStatus.classNormal, new ArrayList<Calendar>());
//                Notification notificationOne = new Notification("Evento "+i, "support text", StatusNotification.normal, true, false, professor);
//                Notification notificationTwo = new Notification("Evento "+(i+10), "support text", StatusNotification.normal, true, false, student);
//                Ping pingS = new Ping("123.456.789.000", new Date(), StatusPing.successful, false, coordinate, attendanceStatus);
//                VirtualZone virtualZone = new VirtualZone(location, attendance);
//                Waiver waiver = new Waiver(new FileMacFI("https://file.pdf", "file.pdf", "pdf", 10, new Date()),
//                        "waiver", new Date(), new Date(), false, null, student, attendanceStatus);
//                Comment comment = new Comment("content", student, null, waiver);

//                attendanceStatus.getSuccessfulPings().add(pingS);
//                attendanceStatus.setWaiver(waiver);
//                attendance.getAttendancesStatuses().add(attendanceStatus);
//                location.getVirtualZones().add(virtualZone);
//                location.setCoordinate(coordinate);
//                classroom.setDefaultLocation(location);
//                classroom.getAttendances().add(attendance);

//                eventRepository.save(event);
//                calendarRepository.save(calendar);
//                calendar.getEvents().add(event);
//                event.getCalendars().add(calendar);
//                eventRepository.save(event);
//
//
//                if (professor != null) {
//                    professor.getNotifications().add(notificationOne);
//                    professor.getClassrooms().add(classroom);
//                    professor.getLocations().add(location);
//                    professorRepository.save(professor);
//                }
//
//                if (student != null) {
//                    student.getNotifications().add(notificationTwo);
//                    student.getClassrooms().add(classroom);
//                    studentRepository.save(student);
//                }
//
//                waiver.setComment(comment);
//                waiverRepository.save(waiver);
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }

    }

    private Professor createProfessor(
            String name,
            String socialName,
            String cpf,
            String email,
            String password,
            String identifier,
            String picture
    ) {
        Professor professor = new Professor(
                name,
                socialName,
                new Date(),
                false,
                cpf,
                email,
                password,
                null,
                null,
                null,
                new ArrayList<Comment>(),
                new ArrayList<Notification>(),
                new ArrayList<Location>(),
                new ArrayList<Classroom>()
        );
        RegisterCollegeID registerProfessor = new RegisterCollegeID(
                identifier,
                new Date(),
                null,
                true,
                professor
        );
        Setting setting = new Setting(
                false,
                false,
                false,
                professor
        );
        Picture profileImage = new Picture(picture,
                "image.jpg",
                "jpg",
                10,
                new Date()
        );

        professor.setRegister(registerProfessor);
        professor.setProfileImage(profileImage);
        professor.setSetting(setting);

        professorRepository.save(professor);

        return professor;
    }

    private Student createStudent(
            String name,
            String socialName,
            String cpf,
            String email,
            String password,
            String identifier,
            String picture
    ) {
        Student student = new Student(
                name,
                socialName,
                new Date(),
                false,
                cpf,
                email,
                password,
                null,
                null,
                null,
                new ArrayList<Comment>(),
                new ArrayList<Notification>(),
                new ArrayList<Classroom>(),
                new ArrayList<Waiver>(),
                new ArrayList<AttendanceStatus>()
        );
        RegisterCollegeID registerStudent = new RegisterCollegeID(
                identifier,
                new Date(),
                null,
                true,
                student
        );
        Setting setting = new Setting(
                false,
                false,
                false,
                student
        );
        Picture profileImage = new Picture(picture,
                "image.jpg",
                "jpg",
                10,
                new Date()
        );

        student.setRegister(registerStudent);
        student.setProfileImage(profileImage);
        student.setSetting(setting);

        studentRepository.save(student);

        return student;
    }

    private Calendar createCalendar() {
        Calendar calendar = new Calendar(new ArrayList<Event>());
        calendarRepository.save(calendar);
        return calendar;
    }

    private Attendance createAttendance(
            String supportText,
            Long hours,
            Classroom classroom
    ) {
        Attendance attendance = new Attendance(
                new Date(),
                supportText,
                LocalTime.now(),
                LocalTime.now().plusHours(2),
                Duration.ofHours(hours),
                false,
                false,
                null,
                classroom,
                new ArrayList<AttendanceStatus>()
        );
        return attendance;
    }

    private Classroom createClassroom(
            String courseName,
            String className,
            String code,
            String semester,
            Professor professor,
            ArrayList<Student> students
    ) {
        Classroom classroom = new Classroom(
                courseName,
                className,
                code,
                semester,
                LocalTime.now(),
                LocalTime.now(),
                new Location(),
                professor,
                students,
                new ArrayList<Attendance>(),
                new ArrayList<Event>()
        );
        classroomRepository.save(classroom);
        return classroom;
    }

    private void generateProfessors() {
        createProfessor(
                "Lorena Cristiane Simone Farias",
                "",
                "31539604705",
                "lorena@ic.uff.br",
                "senha",
                "0000001",
                "https://this-person-does-not-exist.com/img/avatar-gen9d349657736354783df527d84d78d0ef.jpg"
        );
        createProfessor(
                "Fernando Vinicius Ribeiro",
                "",
                "51068223782",
                "fernando@ic.uff.br",
                "senha",
                "0000002",
                "https://this-person-does-not-exist.com/img/avatar-gen1181312b3e8318b1d28ed2b113ba5e56.jpg"
        );
        createProfessor(
                "Severino Kevin Fogaça",
                "Kevin",
                "75379842712",
                "kevin@ic.uff.br",
                "senha",
                "0000003",
                "https://this-person-does-not-exist.com/img/avatar-gen1136deaf30fb5b38d39e4bd2fdd9d3b5.jpg"
        );
        createProfessor(
                "Lúcia Marli Oliveira",
                "",
                "80585097798",
                "lucia@ic.uff.br",
                "senha",
                "0000004",
                "https://this-person-does-not-exist.com/img/avatar-gend1f3669fc780688b40d93917f434dca5.jpg"
        );
        createProfessor(
                "Heloise Julia Elza Drumond",
                "Julia",
                "85203056790",
                "julia@ic.uff.br",
                "senha",
                "0000005",
                "https://this-person-does-not-exist.com/img/avatar-gen77696b6fd3402e6f8e1cbfbf4c898964.jpg"
        );
        createProfessor(
                "Pedro Leonardo Bryan Araújo",
                "",
                "30106076795",
                "pedro@ic.uff.br",
                "senha",
                "0000006",
                "https://this-person-does-not-exist.com/img/avatar-gen11be75daf6ceef36a95877560f709fb7.jpg"
        );
        createProfessor(
                "Milena Lorena Bianca Bernardes",
                "",
                "38094563737",
                "milena@ic.uff.br",
                "senha",
                "0000007",
                "https://this-person-does-not-exist.com/img/avatar-gen1180b63aeaf0647b940d68ede5ffc363.jpg"
        );
        createProfessor(
                "Rafiq Joshi",
                "Henrique",
                "17081593712",
                "henrique@ic.uff.br",
                "senha",
                "0000008",
                "https://this-person-does-not-exist.com/img/avatar-gen2ee59733a85991088ee548d6bb582d35.jpg"
        );
        createProfessor(
                "Isabela Giovana Melo",
                "",
                "29544038612",
                "isabela@ic.uff.br",
                "senha",
                "0000009",
                "https://this-person-does-not-exist.com/img/avatar-gen7d9e28b431ddc5b6e63c7e24b4186841.jpg"
        );
        createProfessor(
                "Rayssa Emanuelly Sebastiana Sales",
                "",
                "72144496684",
                "rayssa@ic.uff.br",
                "senha",
                "0000010",
                "https://this-person-does-not-exist.com/img/avatar-gen1c8601986ad58eb7bae1068b7a7f4b60.jpg"
        );
    }

    private void generateStudents() {
        createStudent(
                "Lolita da Conceição",
                "",
                "02632361702",
                "lolita@id.uff.br",
                "senha",
                "000000001",
                ""
        );
        createStudent(
                "Jonas Silveira",
                "",
                "69815979850",
                "jonas@id.uff.br",
                "senha",
                "000000002",
                ""
        );
        createStudent(
                "Hermínio da Conceição",
                "",
                "18712333646",
                "herminio@id.uff.br",
                "senha",
                "000000003",
                ""
        );
        createStudent(
                "Juliano Dias",
                "",
                "57755405575",
                "juliano@id.uff.br",
                "senha",
                "000000004",
                ""
        );
        createStudent(
                "Bartolomeu Oliveira",
                "",
                "31768054380",
                "bartolomeu@id.uff.br",
                "senha",
                "000000005",
                ""
        );
        createStudent(
                "Leontina Freitas",
                "",
                "12876784599",
                "leontina@id.uff.br",
                "senha",
                "000000006",
                ""
        );
        createStudent(
                "Lisuarte Souza",
                "",
                "94067530145",
                "lisuarte@id.uff.br",
                "senha",
                "000000007",
                ""
        );
        createStudent(
                "Ginestal Moraes",
                "",
                "67775645008",
                "ginestal@id.uff.br",
                "senha",
                "000000008",
                ""
        );
        createStudent(
                "Milo Teixeira",
                "",
                "99244398214",
                "milo@id.uff.br",
                "senha",
                "000000009",
                ""
        );
        createStudent(
                "Marilice Nogueira",
                "",
                "09702014000",
                "marilice@id.uff.br",
                "senha",
                "000000010",
                ""
        );
        createStudent(
                "Núria Porto",
                "",
                "27436280651",
                "nuria@id.uff.br",
                "senha",
                "000000011",
                ""
        );
        createStudent(
                "Zilá da Costa",
                "",
                "22064583815",
                "zila@id.uff.br",
                "senha",
                "000000012",
                ""
        );
        createStudent(
                "Dinarte Moraes",
                "",
                "34210057029",
                "dinarte@id.uff.br",
                "senha",
                "000000013",
                ""
        );
        createStudent(
                "Nena Cavalcanti",
                "",
                "75301092359",
                "nena@id.uff.br",
                "senha",
                "000000014",
                ""
        );
        createStudent(
                "Belisário Teixeira",
                "",
                "14235307720",
                "belisario@id.uff.br",
                "senha",
                "000000015",
                ""
        );
        createStudent(
                "Geraldine Viana",
                "",
                "78713391682",
                "geraldine@id.uff.br",
                "senha",
                "000000016",
                ""
        );
        createStudent(
                "Bento da Paz",
                "",
                "68460304507",
                "bento@id.uff.br",
                "senha",
                "000000017",
                ""
        );
        createStudent(
                "Procópio das Neves",
                "",
                "09884411794",
                "procopio@id.uff.br",
                "senha",
                "000000018",
                ""
        );
        createStudent(
                "Anelise da Cunha",
                "",
                "30116148810",
                "anelise@id.uff.br",
                "senha",
                "000000019",
                ""
        );
        createStudent(
                "Marilei Nascimento",
                "",
                "31586239198",
                "marilei@id.uff.br",
                "senha",
                "000000020",
                ""
        );
        createStudent(
                "Elsa da Costa",
                "",
                "74722760900",
                "elsa@id.uff.br",
                "senha",
                "000000021",
                ""
        );
        createStudent(
                "Sadraque Pires",
                "",
                "46307616245",
                "sadraque@id.uff.br",
                "senha",
                "000000022",
                ""
        );
        createStudent(
                "Lindamar AragÃ£o",
                "",
                "19527948622",
                "lindamar@id.uff.br",
                "senha",
                "000000023",
                ""
        );
        createStudent(
                "Rosivalda Porto",
                "",
                "02597709140",
                "rosivalda@id.uff.br",
                "senha",
                "000000024",
                ""
        );
        createStudent(
                "Israel Barbosa",
                "",
                "89248514626",
                "israel@id.uff.br",
                "senha",
                "000000025",
                ""
        );
        createStudent(
                "Patrizia Sales",
                "",
                "73997088357",
                "patrizia@id.uff.br",
                "senha",
                "000000026",
                ""
        );
        createStudent(
                "Maurícia Gonçalves",
                "",
                "79692772500",
                "mauricia@id.uff.br",
                "senha",
                "000000027",
                ""
        );
        createStudent(
                "Giovani Martins",
                "",
                "42047559502",
                "giovani@id.uff.br",
                "senha",
                "000000028",
                ""
        );
        createStudent(
                "Jofre Almeida",
                "",
                "29356510903",
                "jofre@id.uff.br",
                "senha",
                "000000029",
                ""
        );
        createStudent(
                "Arcanjo Martins",
                "",
                "02765144087",
                "arcanjo@id.uff.br",
                "senha",
                "000000030",
                ""
        );
        createStudent(
                "Thaíse da Mota",
                "",
                "56471763212",
                "thaise@id.uff.br",
                "senha",
                "000000031",
                ""
        );
        createStudent(
                "Edileusa Vieira",
                "",
                "84096198811",
                "edileusa@id.uff.br",
                "senha",
                "000000032",
                ""
        );
        createStudent(
                "Simauro Barbosa",
                "",
                "07745261813",
                "simauro@id.uff.br",
                "senha",
                "000000033",
                ""
        );
        createStudent(
                "Jeremias Nogueira",
                "",
                "10350885095",
                "jeremias@id.uff.br",
                "senha",
                "000000034",
                ""
        );
        createStudent(
                "Verena Mendes",
                "",
                "74675713130",
                "verena@id.uff.br",
                "senha",
                "000000035",
                ""
        );
        createStudent(
                "Julieta Sales",
                "",
                "05382912220",
                "julieta@id.uff.br",
                "senha",
                "000000036",
                ""
        );
        createStudent(
                "Nenrode da Cruz",
                "",
                "40099145308",
                "nenrode@id.uff.br",
                "senha",
                "000000037",
                ""
        );
        createStudent(
                "Minervina da Rosa",
                "",
                "96768545302",
                "minervina@id.uff.br",
                "senha",
                "000000038",
                ""
        );
        createStudent(
                "Araceli Dias",
                "",
                "09812458034",
                "araceli@id.uff.br",
                "senha",
                "000000039",
                ""
        );
        createStudent(
                "Castor Fogaça",
                "",
                "23957713927",
                "castor@id.uff.br",
                "senha",
                "000000040",
                ""
        );
        createStudent(
                "Saladino da Rosa",
                "",
                "54979800319",
                "saladino@id.uff.br",
                "senha",
                "000000041",
                ""
        );
        createStudent(
                "Ana Barbosa",
                "",
                "74144954773",
                "ana@id.uff.br",
                "senha",
                "000000042",
                ""
        );
        createStudent(
                "Salazar Melo",
                "",
                "94473495280",
                "salazar@id.uff.br",
                "senha",
                "000000043",
                ""
        );
        createStudent(
                "Nina da Rocha",
                "",
                "83168959278",
                "nina@id.uff.br",
                "senha",
                "000000044",
                ""
        );
        createStudent(
                "Alaídes Lima",
                "",
                "87472069426",
                "alaides@id.uff.br",
                "senha",
                "000000045",
                ""
        );
        createStudent(
                "Onata Caldeira",
                "",
                "07603503531",
                "onata@id.uff.br",
                "senha",
                "000000046",
                ""
        );
        createStudent(
                "Ema Monteiro",
                "",
                "90279252838",
                "ema@id.uff.br",
                "senha",
                "000000047",
                ""
        );
        createStudent(
                "Nicola Costa",
                "",
                "14244905456",
                "nicola@id.uff.br",
                "senha",
                "000000048",
                ""
        );
        createStudent(
                "Filipo Rocha",
                "",
                "15480366254",
                "filipo@id.uff.br",
                "senha",
                "000000049",
                ""
        );
        createStudent(
                "Ícaro da Luz",
                "",
                "27360101166",
                "icaro@id.uff.br",
                "senha",
                "000000050",
                ""
        );
        createStudent(
                "Queila Caldeira",
                "",
                "53869387890",
                "queila@id.uff.br",
                "senha",
                "000000051",
                ""
        );
        createStudent(
                "Cândida Duarte",
                "",
                "79889059614",
                "candida@id.uff.br",
                "senha",
                "000000052",
                ""
        );
        createStudent(
                "Estéfano Cardoso",
                "",
                "45781828940",
                "estefano@id.uff.br",
                "senha",
                "000000053",
                ""
        );
        createStudent(
                "Matias Monteiro",
                "",
                "02667005600",
                "matias@id.uff.br",
                "senha",
                "000000054",
                ""
        );
        createStudent(
                "Eduardo da Cunha",
                "",
                "92208676130",
                "eduardo@id.uff.br",
                "senha",
                "000000055",
                ""
        );
        createStudent(
                "Edineusa Souza",
                "",
                "11701121107",
                "edineusa@id.uff.br",
                "senha",
                "000000056",
                ""
        );
        createStudent(
                "Graciete Martins",
                "",
                "19834657471",
                "graciete@id.uff.br",
                "senha",
                "000000057",
                ""
        );
        createStudent(
                "Roriz Ramos",
                "",
                "67999699616",
                "roriz@id.uff.br",
                "senha",
                "000000058",
                ""
        );
        createStudent(
                "Catrina Pires",
                "",
                "02722625288",
                "catrina@id.uff.br",
                "senha",
                "000000059",
                ""
        );
        createStudent(
                "Adelina Caldeira",
                "",
                "70026235676",
                "adelina@id.uff.br",
                "senha",
                "000000060",
                ""
        );
        createStudent(
                "Cauani Santos",
                "",
                "48253359853",
                "cauani@id.uff.br",
                "senha",
                "000000061",
                ""
        );
        createStudent(
                "Nely Cardoso",
                "",
                "89260137071",
                "nely@id.uff.br",
                "senha",
                "000000062",
                ""
        );
        createStudent(
                "Betina Freitas",
                "",
                "33282438958",
                "betina@id.uff.br",
                "senha",
                "000000063",
                ""
        );
        createStudent(
                "Joscelino Ribeiro",
                "",
                "94127942746",
                "joscelino@id.uff.br",
                "senha",
                "000000064",
                ""
        );
        createStudent(
                "Alcindo Mendes",
                "",
                "38199715804",
                "alcindo@id.uff.br",
                "senha",
                "000000065",
                ""
        );
        createStudent(
                "Itatiara Cardoso",
                "",
                "42629151409",
                "itatiara@id.uff.br",
                "senha",
                "000000066",
                ""
        );
        createStudent(
                "Lupicino Fernandes",
                "",
                "88360175926",
                "lupicino@id.uff.br",
                "senha",
                "000000067",
                ""
        );
        createStudent(
                "Iúri Caldeira",
                "",
                "35930421773",
                "iuri@id.uff.br",
                "senha",
                "000000068",
                ""
        );
        createStudent(
                "Sisenando Gonçalves",
                "",
                "97734634192",
                "sisenando@id.uff.br",
                "senha",
                "000000069",
                ""
        );
        createStudent(
                "Jocélia da Paz",
                "",
                "70260102474",
                "jocelia@id.uff.br",
                "senha",
                "000000070",
                ""
        );
        createStudent(
                "Rodolfo da Mota",
                "",
                "24787119583",
                "rodolfo@id.uff.br",
                "senha",
                "000000071",
                ""
        );
        createStudent(
                "Idário da Cruz",
                "",
                "55913322428",
                "idario@id.uff.br",
                "senha",
                "000000072",
                ""
        );
        createStudent(
                "Marina da Conceição",
                "",
                "06213386998",
                "marina@id.uff.br",
                "senha",
                "000000073",
                ""
        );
        createStudent(
                "Nathielle Oliveira",
                "",
                "00193543559",
                "nathielle@id.uff.br",
                "senha",
                "000000074",
                ""
        );
        createStudent(
                "Clemêncio Pinto",
                "",
                "93299581333",
                "clemencio@id.uff.br",
                "senha",
                "000000075",
                ""
        );
        createStudent(
                "Genilsa Ribeiro",
                "",
                "22057038033",
                "genilsa@id.uff.br",
                "senha",
                "000000076",
                ""
        );
        createStudent(
                "Mimoso Castro",
                "",
                "38811347866",
                "mimoso@id.uff.br",
                "senha",
                "000000077",
                ""
        );
        createStudent(
                "Marvão Silveira",
                "",
                "14162071470",
                "marvao@id.uff.br",
                "senha",
                "000000078",
                ""
        );
        createStudent(
                "Micael de Souza",
                "",
                "53809009504",
                "micael@id.uff.br",
                "senha",
                "000000079",
                ""
        );
        createStudent(
                "Apolinário Almeida",
                "",
                "13437977210",
                "apolinario@id.uff.br",
                "senha",
                "000000080",
                ""
        );
        createStudent(
                "Gastão Moura",
                "",
                "96424017623",
                "gastao@id.uff.br",
                "senha",
                "000000081",
                ""
        );
        createStudent(
                "Arquimino da ConceiÃ§Ã£o",
                "",
                "18351212334",
                "arquimino@id.uff.br",
                "senha",
                "000000082",
                ""
        );
        createStudent(
                "Albana Jesus",
                "",
                "46570115707",
                "albana@id.uff.br",
                "senha",
                "000000083",
                ""
        );
        createStudent(
                "Frede da Luz",
                "",
                "45095317532",
                "frede@id.uff.br",
                "senha",
                "000000084",
                ""
        );
        createStudent(
                "Lucinara de Souza",
                "",
                "63147857310",
                "lucinara@id.uff.br",
                "senha",
                "000000085",
                ""
        );
        createStudent(
                "Zenira Alves",
                "",
                "58180139018",
                "zenira@id.uff.br",
                "senha",
                "000000086",
                ""
        );
        createStudent(
                "Anolido FogaÃ§a",
                "",
                "67390958413",
                "anolido@id.uff.br",
                "senha",
                "000000087",
                ""
        );
        createStudent(
                "Lilaine Mendes",
                "",
                "38954435220",
                "lilaine@id.uff.br",
                "senha",
                "000000088",
                ""
        );
        createStudent(
                "Nandielly Lopes",
                "",
                "63143214252",
                "nandielly@id.uff.br",
                "senha",
                "000000089",
                ""
        );
        createStudent(
                "Rigoberto Moreira",
                "",
                "21210952840",
                "rigoberto@id.uff.br",
                "senha",
                "000000090",
                ""
        );
        createStudent(
                "Nilo Fernandes",
                "",
                "91759654965",
                "nilo@id.uff.br",
                "senha",
                "000000091",
                ""
        );
        createStudent(
                "Aracy Costa",
                "",
                "02027506047",
                "aracy@id.uff.br",
                "senha",
                "000000092",
                ""
        );
        createStudent(
                "Umbelina Barbosa",
                "",
                "93392323560",
                "umbelina@id.uff.br",
                "senha",
                "000000093",
                ""
        );
        createStudent(
                "Valdinéia Melo",
                "",
                "36012798008",
                "valdineia@id.uff.br",
                "senha",
                "000000094",
                ""
        );
        createStudent(
                "Salete da Paz",
                "",
                "17825089294",
                "salete@id.uff.br",
                "senha",
                "000000095",
                ""
        );
        createStudent(
                "Elioenai Viana",
                "",
                "39700769941",
                "elioenai@id.uff.br",
                "senha",
                "000000096",
                ""
        );
        createStudent(
                "Aprigio Barbosa",
                "",
                "56144211810",
                "aprigio@id.uff.br",
                "senha",
                "000000097",
                ""
        );
        createStudent(
                "Lécio da Conceição",
                "",
                "81387396730",
                "lecio@id.uff.br",
                "senha",
                "000000098",
                ""
        );
        createStudent(
                "Eulogio das Neves",
                "",
                "65274478093",
                "eulogio@id.uff.br",
                "senha",
                "000000099",
                ""
        );
        createStudent(
                "Gaspar Alves",
                "",
                "91315027925",
                "gaspar@id.uff.br",
                "senha",
                "000000100",
                ""
        );
    }

    private void generateClassrooms() {
        Professor professor;
        ArrayList<Student> students;
        Attendance attendance;
        AttendanceStatus attendanceStatus;
        Location location;
        Coordinate coordinate;
        Event event;
        Notification notification01;
        Notification notification02;
        Ping successfulPing;
        VirtualZone virtualZone;
        Waiver waiver;
        Comment comment;

        professor = professorRepository.findById(1L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Programação de Computadores I",
                "A1",
                "TCC00308",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(1L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Laboratório de Resolução de Problemas",
                "A1",
                "TCC00346",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(2L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Cálculo I-A",
                "H1",
                "TCC00019",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(2L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Geometria Analt. e Cálculo Vetorial",
                "F1",
                "GAN00167",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(3L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Estrutura de Dados e seus Alg.",
                "B1",
                "TCC00348",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(3L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Engenharia de Soft. I",
                "A1",
                "TCC00292",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(4L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Engenharia de Soft. I",
                "B1",
                "TCC00292",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(4L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Arquitetura de Computadores",
                "B1",
                "TCC00286",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(5L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Banco de Dados II",
                "A1",
                "TCC00288",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(5L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Interface Homem-Máquina",
                "A1",
                "TCC00298",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(6L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Avaliação de Desempenho",
                "A1",
                "TCC00349",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(6L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Avaliação de Desempenho",
                "B1",
                "TCC00349",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(7L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Física Experimental I",
                "DA",
                "GFI00161",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(7L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Física II",
                "A3",
                "GFI00159",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(8L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Circuitos Digitais",
                "A1",
                "TET00347",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(8L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Laboratório de Resolução de Problemas",
                "AA",
                "TCC00346",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(9L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Laboratório de Resolução de Problemas",
                "AB",
                "TCC00346",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(9L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Programação Estruturada",
                "D1",
                "TCC00347",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(10L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Análise e Projeto de Algoritmos",
                "A1",
                "TCC00285",
                "120",
                professor,
                students
        );

        professor = professorRepository.findById(10L).get();
        students = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            students.add((Student) studentRepository.findById((long) i).get());
        }
        createClassroom(
                "Sistemas Distribuídos",
                "A1",
                "TCC00315",
                "120",
                professor,
                students
        );
    }
}
