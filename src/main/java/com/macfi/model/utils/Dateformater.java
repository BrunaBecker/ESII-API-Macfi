package com.macfi.model.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateformater {
    static String DATE_FORMAT_MACFI = "yyyy-MM-dd HH:mm:ss";

    static SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_MACFI);

    public static Date format(String date) throws ParseException {
        return formatter.parse(date);
    }
}
