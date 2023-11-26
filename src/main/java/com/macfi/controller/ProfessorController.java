package com.macfi.controller;


import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.payload.*;
import com.macfi.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("professor")
//@RequestMapping("Professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping //localhost:8080/professor
    public ResponseEntity<List<ProfessorDto>> getProfessors() {
        List<ProfessorDto> professorDto;

        try {
            professorDto = professorService.getProfessors();
            return ResponseEntity.ok(professorDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("{idProfessor}") //localhost:8080/professor/1
    public ResponseEntity<ProfessorDto> getProfessorById(@PathVariable("idProfessor") Long id) {

        ProfessorDto professorDto;

        try {
            professorDto = professorService.getProfessorById(id);
            return ResponseEntity.ok(professorDto);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }


    }

    @GetMapping("byIdentifier/{identifier}") //localhost:8080/professor/byIdentifier/1
    public ResponseEntity<ProfessorDto> getProfessorByIdentifier(@PathVariable("identifier") String identifier) {

        ProfessorDto professorDto;

        try {
            professorDto = professorService.getProfessorByIdentifier(identifier);
            return ResponseEntity.ok(professorDto);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }

    }

    @Operation(
            summary = "Create Professor REST API",
            description = "Create Professor REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping //localhost:8080/professor
    public ResponseEntity<ProfessorDto> createProfessor(@Valid @RequestBody ProfessorDto professor) {
        ProfessorDto professorDto;

        try {
            professorDto = professorService.createProfessor(professor);
            return new ResponseEntity<>(professorDto, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping //localhost:8080/professor
    public ResponseEntity<ProfessorDto> updateProfessor(@Valid @RequestBody ProfessorDto professor) {
        ProfessorDto professorDto;

        try {
            professorDto = professorService.updateProfessor(professor);
            return new ResponseEntity<>(professorDto, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }

    }

    @Operation(
            summary = "Add Classroom REST API",
            description = "Add Classroom REST API is used to add a classroom to a professor by Identifier"
    )
    @ApiResponse(responseCode = "200", description = "Http Status 200 OK")
    @PutMapping("{identifier}/class") //localhost:8080/professor/identifier/class
    public ResponseEntity<ProfessorDto> addClassroom(@Valid @RequestBody ClassroomDto classroom, @PathVariable("identifier") String identifier) {
        ProfessorDto professorDto;

        try {
            professorDto = professorService.addClassroom(classroom, identifier);
            return new ResponseEntity<>(professorDto, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PutMapping("setClassroom")
    public ResponseEntity<ProfessorDto> setClassroom(@RequestParam("idProfessor") Long idProfessor, @RequestParam("idClassroom") Long idClassroom) {
        ProfessorDto professorDto1;

        try {
            professorDto1 = professorService.setClassroom(idProfessor, idClassroom);
            return new ResponseEntity<>(professorDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping("setLocation")
    public ResponseEntity<ProfessorDto> setLocation(@RequestParam("idProfessor") Long idProfessor,  @RequestParam("idLocation") Long idLocation) {
        ProfessorDto professorDto1;

        try {
            professorDto1 = professorService.setLocation(idProfessor, idLocation);
            return new ResponseEntity<>(professorDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("addLocation") //localhost:8080/professor/addLocation?idProfessor=1
    public ResponseEntity<ProfessorDto> addLocation(@RequestParam Long idProfessor, @Valid @RequestBody LocationDto locationDto) {
        ProfessorDto professorDto1;

        try {
            professorDto1 = professorService.addLocation(idProfessor, locationDto);
            return new ResponseEntity<>(professorDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping("setSetting")
    public ResponseEntity<ProfessorDto> setSetting(@RequestParam("idProfessor") Long idProfessor, @RequestParam("idSetting") Long idSetting) {
        ProfessorDto professorDto1;

        try {
            professorDto1 = professorService.setSetting(idProfessor, idSetting);
            return new ResponseEntity<>(professorDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("addSetting")
    public ResponseEntity<ProfessorDto> addSetting(@RequestParam("idProfessor") Long idProfessor, @Valid @RequestBody  SettingDto settingDto) {
        ProfessorDto professorDto1;

        try {
            professorDto1 = professorService.addSetting(idProfessor, settingDto);
            return new ResponseEntity<>(professorDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping("setProfileImage")
    public ResponseEntity<ProfessorDto> setProfileImage(@RequestParam("idProfessor") Long idProfessor, @RequestParam("idProfileImage") Long idProfileImage) {
        ProfessorDto professorDto1;

        try {
            professorDto1 = professorService.setProfileImage(idProfessor, idProfileImage);
            return new ResponseEntity<>(professorDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping("addPicture") //localhost:8080/professor/addPicture?idProfessor=1
    public ResponseEntity<ProfessorDto> addProfileImage(@RequestParam("idProfessor") Long idProfessor, @Valid @RequestBody  PictureDto picture) {
        ProfessorDto professorDto1;

        try {
            professorDto1 = professorService.addProfileImage(idProfessor, picture);
            return new ResponseEntity<>(professorDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setRegister")
    public ResponseEntity<ProfessorDto> setRegister(@RequestParam("idProfessor") Long idProfessor, @RequestParam("idRegister") Long idRegister) {
        ProfessorDto professorDto1;

        try {
            professorDto1 = professorService.setRegister(idProfessor, idRegister);
            return new ResponseEntity<>(professorDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("addRegister")
    public ResponseEntity<ProfessorDto> addRegister(@RequestParam("idProfessor") Long idProfessor, @Valid @RequestBody  RegisterCollegeIDDto registerCollegeID) {
        ProfessorDto professorDto1;

        try {
            professorDto1 = professorService.addRegister(idProfessor, registerCollegeID);
            return new ResponseEntity<>(professorDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("addComment") //localhost:8080/professor/addComment?idProfessor=1&idComment=1
    public ResponseEntity<ProfessorDto> addComment(@RequestParam Long idProfessor, @Valid @RequestBody CommentDto commentDto) {
        ProfessorDto professorDto1;

        try {
            professorDto1 = professorService.addComment(idProfessor, commentDto);
            return new ResponseEntity<>(professorDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setComment") //localhost:8080/professor/setComment?idProfessor=1&idComment=1
    public ResponseEntity<ProfessorDto> setComment(@RequestParam Long idProfessor, @RequestParam Long idComment) {
        ProfessorDto professorDto1;

        try {
            professorDto1 = professorService.setComment(idProfessor, idComment);
            return new ResponseEntity<>(professorDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("addNotification") //localhost:8080/professor/addNotification?idProfessor=1
    public ResponseEntity<ProfessorDto> addNotification(@RequestParam Long idProfessor, @Valid @RequestBody  NotificationDto notification) {
        ProfessorDto professorDto1;

        try {
            professorDto1 = professorService.addNotification(idProfessor, notification);
            return new ResponseEntity<>(professorDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping("setNotification") //localhost:8080/professor/addNotification?idProfessor=1&idNotification=1
    public ResponseEntity<ProfessorDto> setNotification(@RequestParam Long idNotification, @RequestParam Long idProfessor) {
        ProfessorDto professorDto1;

        try {
            professorDto1 = professorService.setNotification(idNotification, idProfessor);
            return new ResponseEntity<>(professorDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


}
