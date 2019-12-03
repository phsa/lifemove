package br.com.lifemove.utils;


import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtils {

    private static final String COMPLETE_DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";
    private static final String DISPLAY_DATE_FORMAT = "dd/MM/yyyy hh:mm";
    private static final String PHOTO_DATE_FORMAT = "yyyy-MM-dd_hh-mm-ss.SSS";
    private static final String MSG_TIME_FORMAT = "HH:mm";

    public static long getCurrentTimestamp() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static String timestampToFormattedDate(long timestamp) {
        return getFormattedDate(timestamp, COMPLETE_DATE_FORMAT);
    }

    public static String timestampToDisplayDate(long timestamp) {
        return getFormattedDate(timestamp, DISPLAY_DATE_FORMAT);
    }

    public static String getTimeInMsgFormat(long timestamp) {
        return getFormattedDate(timestamp, MSG_TIME_FORMAT);
    }

    public static String getTimeInPhotoFormat(long timestamp) {
        return getFormattedDate(timestamp, PHOTO_DATE_FORMAT);
    }

    private static String getFormattedDate(long timestamp, String format) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(timestamp);

    }
}
