package com.macfi.payload;

import com.macfi.model.Waiver;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Waiver}
 */
@Value
public class WaiverDto implements Serializable {
    Long id;
    String description;
    Date sendDate;
    Date acceptionDate;
    boolean isAccepted;
}