package com.macfi.model;

import com.macfi.model.utils.enums_class.StatusPing;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Ping}
 */
@Value
public class PingDto implements Serializable {
    Long id;
    String ip;
    Date date;
    StatusPing status;
    boolean isContinuos;
}