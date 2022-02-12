package com.getir.readingGood.util;

import java.util.UUID;
import java.text.DateFormatSymbols;
/**
 * Created by ozgurokka on 2/11/22 6:35 PM
 */
public class ReadingGoodUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    public static String getMonth(String month) {
        return new DateFormatSymbols().getMonths()[Integer.parseInt(month)-1];
    }
}
