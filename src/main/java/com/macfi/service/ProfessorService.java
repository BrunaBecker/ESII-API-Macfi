package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.model.Classroom;
import com.macfi.model.Location;
import com.macfi.model.Notification;
import com.macfi.model.Setting;
import com.macfi.model.person.Professor;
import com.macfi.model.person.RegisterCollegeID;
import com.macfi.model.utils.Comment;
import com.macfi.model.utils.Picture;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.*;
import com.macfi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private SettingRepository settingRepository;
    @Autowired
    private RegisterCollegeIdRepository registerCollegeIDRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private PictureRepository pictureRepository;

    public ProfessorDto createProfessor(ProfessorDto professor) {
        return modelMapping.getInstance().mapToDto(professorRepository.save(modelMapping.getInstance().mapToEntity(professor, Professor.class)), ProfessorDto.class);
    }

    public ProfessorDto getProfessorById(Long id) {

        return modelMapping.getInstance().mapToDto(professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found")), ProfessorDto.class);
    }

    public ProfessorDto getProfessorByIdentifier(String identifier) {
        Professor p = professorRepository.findByIdentifier(identifier);
        if (p == null) {
            throw new EntityNotFoundException("Professor not found");
        }
        return modelMapping.getInstance().mapToDto(p, ProfessorDto.class);
    }

    public ProfessorDto updateProfessor(ProfessorDto professor) {
        Professor professor1 = professorRepository.findById(professor.getId()).orElseThrow(() -> new EntityNotFoundException("Professor not found"));
        return modelMapping.getInstance().mapToDto(professorRepository.save(professor1), ProfessorDto.class);
    }

    public List<ProfessorDto> getProfessors() {
        List<Professor> professors = professorRepository.findAllByRepository();
        return professors.stream().map(professor -> modelMapping.getInstance().mapToDto(professor, ProfessorDto.class)).collect(Collectors.toList());
    }


    public ProfessorDto login(String identifier, String password) {

        ProfessorDto professorDto = getProfessorByIdentifier(identifier);
        if (professorDto.getPassword().equals(password) && professorDto.getIsActive()) {
            return professorDto;
        } else {
            throw new UserUnauthorized("Password or identifier incorrect");
        }


    }

    public ProfessorDto addClassroom(ClassroomDto classroomDto, String identifier) {
        Professor professor = professorRepository.findByIdentifier(identifier);
        Classroom classroom = classroomRepository.findById(classroomDto.getId()).orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        professor.getClassrooms().add(classroom);
        return modelMapping.getInstance().mapToDto(professorRepository.save(professor), ProfessorDto.class);
    }

    public ProfessorDto setLocation(Long idProfessor, Long idLocation) {
        Location location = locationRepository.findById(idLocation).orElseThrow(() -> new EntityNotFoundException("Location not found"));
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new EntityNotFoundException("Professor not found"));
        professor.getLocations().add(location);
        return modelMapping.getInstance().mapToDto(professorRepository.save(professor), ProfessorDto.class);
    }

    public ProfessorDto addLocation(Long idProfessor, LocationDto locationDto) {
        Location location;
        try {
            location = locationRepository.findById(locationDto.getId()).orElseThrow(() -> new EntityNotFoundException("Location not found"));
        } catch (EntityNotFoundException e) {
            location = modelMapping.getInstance().mapToEntity(locationDto, Location.class);
        }
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new EntityNotFoundException("Professor not found"));
        professor.getLocations().add(location);
        return modelMapping.getInstance().mapToDto(professorRepository.save(professor), ProfessorDto.class);
    }

    public ProfessorDto setSetting(Long idProfessor, Long idSetting) {
        Setting setting = settingRepository.findById(idSetting).orElseThrow(() -> new EntityNotFoundException("Setting not found"));
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new EntityNotFoundException("Professor not found"));
        professor.setSetting(setting);
        return modelMapping.getInstance().mapToDto(professorRepository.save(professor), ProfessorDto.class);
    }

    public ProfessorDto setProfileImage(Long idProfessor, Long idProfileImage) {
        Picture picture = modelMapping.getInstance().mapToEntity(idProfileImage, Picture.class);
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new EntityNotFoundException("Professor not found"));
        professor.setProfileImage(picture);
        return modelMapping.getInstance().mapToDto(professorRepository.save(professor), ProfessorDto.class);
    }

    public ProfessorDto addRegister(Long idProfessor, RegisterCollegeIDDto registerCollegeID) {
        RegisterCollegeID registerCollegeID1;
        try {
            registerCollegeID1 = registerCollegeIDRepository.findById(registerCollegeID.getId()).orElseThrow(() -> new EntityNotFoundException("Register not found"));
        } catch (EntityNotFoundException e) {
            registerCollegeID1 = modelMapping.getInstance().mapToEntity(registerCollegeID, RegisterCollegeID.class);
        }
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new EntityNotFoundException("Professor not found"));
        professor.setRegister(registerCollegeID1);
        return modelMapping.getInstance().mapToDto(professorRepository.save(professor), ProfessorDto.class);
    }

    public ProfessorDto setComment(Long idComment, Long idProfessor) {
        Comment comment = commentRepository.findById(idComment).orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new EntityNotFoundException("Professor not found"));
        professor.getComments().add(comment);
        return modelMapping.getInstance().mapToDto(professorRepository.save(professor), ProfessorDto.class);
    }

    public ProfessorDto addComment(Long idProfessor, CommentDto commentDto) {
        Comment comment;
        try {
            comment = commentRepository.findById(commentDto.getId()).orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        } catch (EntityNotFoundException e) {
            comment = modelMapping.getInstance().mapToEntity(commentDto, Comment.class);
        }
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new EntityNotFoundException("Professor not found"));
        professor.getComments().add(comment);
        return modelMapping.getInstance().mapToDto(professorRepository.save(professor), ProfessorDto.class);


    }


    public ProfessorDto addNotification(Long idProfessor, NotificationDto notificationDto) {
        Notification notification;
        try {
            notification = modelMapping.getInstance().mapToEntity(notificationDto, Notification.class);
        } catch (EntityNotFoundException e) {
            notification = notificationRepository.findById(notificationDto.getId()).orElseThrow(() -> new EntityNotFoundException("Notification not found"));
        }
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new EntityNotFoundException("Professor not found"));
        professor.getNotifications().add(notification);
        return modelMapping.getInstance().mapToDto(professorRepository.save(professor), ProfessorDto.class);
    }

    public ProfessorDto setClassroom(Long idProfessor, Long idClassroom) {
        Classroom classroom = classroomRepository.findById(idClassroom).orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new EntityNotFoundException("Professor not found"));
        professor.getClassrooms().add(classroom);
        return modelMapping.getInstance().mapToDto(professorRepository.save(professor), ProfessorDto.class);
    }

    public ProfessorDto addSetting(Long idProfessor, SettingDto settingDto) {
        Setting setting;
        try {
            setting = settingRepository.findById(settingDto.getId()).orElseThrow(() -> new EntityNotFoundException("Setting not found"));
        } catch (EntityNotFoundException e) {
            setting = modelMapping.getInstance().mapToEntity(settingDto, Setting.class);
        }
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new EntityNotFoundException("Professor not found"));
        professor.setSetting(setting);
        return modelMapping.getInstance().mapToDto(professorRepository.save(professor), ProfessorDto.class);

    }

    public ProfessorDto addProfileImage(Long idProfessor, PictureDto picture) {
        Picture picture1;
        try {
            picture1 = modelMapping.getInstance().mapToEntity(picture, Picture.class);
        } catch (EntityNotFoundException e) {
            picture1 = pictureRepository.findById(picture.getId()).orElseThrow(() -> new EntityNotFoundException("Picture not found"));
        }
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new EntityNotFoundException("Professor not found"));
        professor.setProfileImage(picture1);
        return modelMapping.getInstance().mapToDto(professorRepository.save(professor), ProfessorDto.class);

    }

    public ProfessorDto setRegister(Long idProfessor, Long idRegister) {
        RegisterCollegeID registerCollegeID = registerCollegeIDRepository.findById(idRegister).orElseThrow(() -> new EntityNotFoundException("Register not found"));
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new EntityNotFoundException("Professor not found"));
        professor.setRegister(registerCollegeID);
        return modelMapping.getInstance().mapToDto(professorRepository.save(professor), ProfessorDto.class);
    }

    public ProfessorDto setNotification(Long idNotification, Long idProfessor) {
        Notification notification = notificationRepository.findById(idNotification).orElseThrow(() -> new EntityNotFoundException("Notification not found"));
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new EntityNotFoundException("Professor not found"));
        professor.getNotifications().add(notification);
        return modelMapping.getInstance().mapToDto(professorRepository.save(professor), ProfessorDto.class);
    }
}
