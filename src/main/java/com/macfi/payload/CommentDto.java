package com.macfi.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.macfi.model.utils.Comment}
 */
@Data
public class CommentDto implements Serializable {
    @NotNull(message = "id must not be null")
    Long id;
    @NotNull(message = "content must not be null")
    String content;

    @NotNull(message = "personId must not be null")
    Long personId;

    CommentDto comment;
}