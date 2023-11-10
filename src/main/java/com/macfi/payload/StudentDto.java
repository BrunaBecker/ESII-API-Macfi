package com.macfi.payload;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.macfi.model.person.Student}
 */
@Data
public class StudentDto implements Serializable {
    Long id;
    String name;
    String socialName;
    Date birthDate;
    Boolean isActive;
    String cpf;
    String email;
    String password;
    RegisterCollegeIDDto register;
    SettingDto setting;
    PictureDto profileImage;
    List<CommentDto> comments;
    List<WaiverDto> waivers;
}

