package com.macfi.payload;

import com.macfi.model.Waiver;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Waiver}
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
    CommentDto comment;
    @NotNull(message = "student must be not null")
    StudentDto student;
    @NotNull(message = "attendanceStatusId must be not null")
    Long attendanceStatusId;
}