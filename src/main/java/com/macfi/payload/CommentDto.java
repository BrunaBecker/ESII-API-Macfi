package com.macfi.payload;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.macfi.model.utils.Comment}
 */
@Value
public class CommentDto implements Serializable {
    Long id;
    String content;
}