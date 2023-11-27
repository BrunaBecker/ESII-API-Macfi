package com.macfi.model.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateformater {
    static String DATE_FORMAT_MACFI = "yyyy-MM-dd HH:mm:ss";

    static String DATE_FORMAT_MACFI_WITHOUT_TIME = "yyyy-MM-dd";

    static String DATE_ADD_START_DATE = " 00:00:00";

    static String DATE_ADD_END_DATE = " 23:59:59";

    static SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_MACFI);

    public static Date parse(String date, boolean addHour, boolean is_end_date) throws ParseException {

        if (addHour) {
            if (is_end_date) {
                date = date + DATE_ADD_END_DATE;
            } else {
                date = date + DATE_ADD_START_DATE;
            }
        }


        if (date.length() > 10) {
            return parse(date, DATE_FORMAT_MACFI);
        } else {
            System.out.println(date);
            return parse(date, DATE_FORMAT_MACFI_WITHOUT_TIME);
        }
    }

    public static String format(Date date) {
        return formatter.format(date);
    }

    public static Date parse(String date, String data_format_macfi) throws ParseException {

        if (data_format_macfi == null) {
            data_format_macfi = DATE_FORMAT_MACFI;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(data_format_macfi);
        return formatter.parse(date);
    }
}
