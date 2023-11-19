package com.macfi.payload;

import com.macfi.model.utils.Comment;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link Comment}
 */
@Data
public class CommentDto implements Serializable {
    Long id;
    @NotNull(message = "content must not be null")
    String content;

    @NotNull(message = "personId must not be null")
    Long personId;

    CommentDto comment;
}