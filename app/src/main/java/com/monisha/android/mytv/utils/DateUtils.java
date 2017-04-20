package com.monisha.android.mytv.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by monisha on 19/04/17.
 */

public class DateUtils {

    public static String getCurrentDateAndTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentDateandTime = sdf.format(new Date());

        return currentDateandTime;
    }

    public static String addMinuteToCurrentTime(int minutes){

        Calendar now = Calendar.getInstance();

        now.add(Calendar.MINUTE, minutes);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return  df.format(now.getTime());

    }

    public static String addHourToCurrentTime(int hours){

        Calendar now = Calendar.getInstance();

        now.add(Calendar.HOUR, hours);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return  df.format(now.getTime());

    }


    public static String addHourToTime(String time1, int hour){

        Calendar now = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {

            Date d1 = df.parse(time1);
            now.setTime(d1);
            now.add(Calendar.HOUR, hour);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  df.format(now.getTime());

    }

    public static String addMinutesToTime(String time1, int min){

        Calendar now = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {

            Date d1 = df.parse(time1);
            now.setTime(d1);
            now.add(Calendar.MINUTE, min);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  df.format(now.getTime());

    }

    public static boolean compareTime(String startDateTime, String endDateTime){

        Calendar now = Calendar.getInstance();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date strDate = null;
        Date endDate = null;

        try {
            strDate = sdf.parse(startDateTime);
            start.setTime(strDate);
            endDate = sdf.parse(endDateTime);
            end.setTime(endDate);

            now.setTime(new Date());
            Date x = now.getTime();
            if (x.after(start.getTime()) && x.before(end.getTime()))
            {
                return true;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        return false;
    }
}
