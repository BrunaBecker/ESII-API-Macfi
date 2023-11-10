package com.macfi.payload;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.macfi.model.Waiver}
 */
@Data
public class WaiverDto implements Serializable {
    Long id;
    @NotNull
    FileMacFIDto file;
    @NotNull(message = "description must be not null")
    String description;
    @NotNull(message = "sendDate must be not null")
    @FutureOrPresent(message = "sendDate must not be in past")
    Date sendDate;
    Date acceptionDate;
    boolean isAccepted;
    @NotNull(message = "comment must be not null")
    CommentDto comment;
    @NotNull(message = "student must be not null")
    StudentDto student;
    @NotNull(message = "attendanceStatusId must be not null")
    Long attendanceStatusId;
}