package com.portalprojects.util;

import java.sql.Timestamp;

/**
 * @author thangncph26123
 */

public class DateTimeUtil {

    public static Long convertDateToTimeStampSecond() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }

}
