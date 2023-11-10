package com.macfi.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.macfi.model.utils.FileMacFI}
 */
@Data
public class FileMacFIDto implements Serializable {
    @NotNull
    Long id;
    String linkFile;
    @NotEmpty(message = "name must not be null")
    String nameFile;
    @NotNull(message = "type  must not be null")
    @NotEmpty(message = "type must not be empty")
    String typeFile;
    @PositiveOrZero(message = "size must be a positive or zero value")
    Integer sizeFile;
    @NotNull(message = "date must have a value")
    Date dateFile;

}