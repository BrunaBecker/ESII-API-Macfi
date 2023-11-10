package com.macfi.payload;

import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.macfi.model.utils.Comment}
 */
@Data
public class CommentDto implements Serializable {
    Long id;
    String content;
}