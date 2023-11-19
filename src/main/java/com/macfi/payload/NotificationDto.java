package com.macfi.payload;

import com.macfi.model.Notification;
import com.macfi.model.utils.enums_class.StatusNotification;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link Notification}
 */
@Data
public class NotificationDto implements Serializable {
    Long id;
    @NotNull(message = "title must not be null")
    String title;
    @NotNull(message = "supportingText must not be null")
    String supportingText;
    @NotNull(message = "statusNotification must not be null")
    StatusNotification statusNotification;
    boolean isActive;
    boolean isRead;
    @NotNull(message = "person id must not be null")
    Long personId;
}