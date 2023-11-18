package com.macfi.payload;

import com.macfi.model.utils.Picture;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Picture}
 */
@Data
public class PictureDto implements Serializable {
    Long id;

    @NotNull(message = "linkFile must not be null")
    @NotEmpty(message = "linkFile must not be empty")
    String linkFile;

    @NotNull(message = "nameFile must not be null")
    @NotEmpty(message = "nameFile must not be empty")
    String nameFile;

    @NotNull(message = "typeFile must not be null")
    @NotEmpty(message = "typeFile must not be empty")
    String typeFile;

    @NotNull(message = "sizeFile must not be null")
    @PositiveOrZero(message = "sizeFile must be positive or zero")

    Integer sizeFile;
    @NotNull(message = "dateFile must not be null")
    Date dateFile;
}