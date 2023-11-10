package com.macfi.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.macfi.model.utils.Picture}
 */
@Data
public class PictureDto implements Serializable {
    @NotNull(message = "id must not be null")
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