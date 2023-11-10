package com.macfi.payload;

import com.macfi.model.Setting;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Setting}
 */
@Value
public class SettingDto implements Serializable {
    Long id;
    boolean automaticSaveLocalization;
    boolean usePredefinedCalendarForAttendance;
    boolean silentMode;
}