package com.phanduy.utils;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by duyuno on 7/15/17.
 */
public class DateUtil {
    private static SimpleDateFormat sdfFullDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

    public static String convertFullDate(Date date) {
        return sdfFullDate.format(date);
    }
    public static String convertOnlyDay(Date date) {
        return sdfDate.format(date);
    }
    public static String convertOnlyTime(Date date) {
        return sdfTime.format(date);
    }

    public static Date getConvertDate(String date) {
        return getConvertDate(date, sdfFullDate);
    }
    public static Date getConvertDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return getConvertDate(date, sdf);
    }
    public static Date getConvertDate(String date, SimpleDateFormat simpleDateFormat) {
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
