package com.macfi.payload;

import com.macfi.model.utils.FileMacFI;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link FileMacFI}
 */
@Data
public class FileMacFIDto implements Serializable {
    Long id;
    @NotNull(message = "link must not be null")
    String linkFile;
    @NotNull(message = "name must not be null")
    @NotEmpty(message = "name must not be empty")
    String nameFile;
    @NotNull(message = "type  must not be null")
    @NotEmpty(message = "type must not be empty")
    String typeFile;
    @PositiveOrZero(message = "size must be a positive or zero value")
    Integer sizeFile;
    @NotNull(message = "date must have a value")
    Date dateFile;

}