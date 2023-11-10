package com.macfi.payload;

import com.macfi.model.Setting;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link Setting}
 */
@Data
public class SettingDto implements Serializable {
    @NotNull(message = "id must not be null")
    Long id;
    @NotNull(message = "automaticSaveLocalization must not be null")
    boolean automaticSaveLocalization;
    @NotNull(message = "automaticSaveAttendance must not be null")
    boolean usePredefinedCalendarForAttendance;
    @NotNull(message = "silentMode must not be null")
    boolean silentMode;
}