package com.macfi.payload;

import com.macfi.model.Setting;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link Setting}
 */
@Data
public class SettingDto implements Serializable {
    Long id;
    boolean automaticSaveLocalization;
    boolean usePredefinedCalendarForAttendance;
    boolean silentMode;
}