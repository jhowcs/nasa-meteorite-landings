package com.jhowcs.nasameteoritelandings.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by jonathan_campos on 25/10/2016.
 */

public class DateUtil {

    /**
     * Method that parses a year in a complete date format yyyy-MM-dd'T'HH:mm:ss
     *
     * @param year
     * @return A String formated as yyyy-MM-dd'T'HH:mm:ss
     */
    public static String getCompleteStringDate(int year) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Calendar cal = GregorianCalendar.getInstance();
        cal.set(year, 0, 1, 0, 0, 0);

        return sdf.format(cal.getTime());
    }
}
