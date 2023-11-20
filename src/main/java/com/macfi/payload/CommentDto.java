package com.macfi.payload;

import com.macfi.model.utils.Comment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link Comment}
 */
@Data
public class CommentDto implements Serializable {
    Long id;
    @NotNull(message = "content must not be null")
    @Size(min = 1, max = 155, message = "content must be between 1 and 63 characters")
    String content;

    @NotNull(message = "personId must not be null")
    Long personId;

    CommentDto comment;
}