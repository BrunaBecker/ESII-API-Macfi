package com.macfi.payload;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.macfi.model.utils.Picture}
 */
@Data
public class PictureDto implements Serializable {
    Long id;
    String linkFile;
    String nameFile;
    String typeFile;
    Integer sizeFile;
    Date dateFile;
}