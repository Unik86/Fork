package com.fork.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;

public class Utils {

    private Utils() {
    }

    public static double round(double value, double floatValue){
        return Math.round( value * floatValue ) / floatValue;
    }

    public static double notNullDouble(Double rate) {
        return isNull(rate) ? 0 : rate;
    }

    public static double calcPercent(Double notRoundRate) {
        return round((1 - notRoundRate) * 100, 100);
    }

    public static Double calcPercentBet(Double rate) {
        if(isNull(rate)) return null;
        // P = 1/k * 100
        return round((1 / rate) * 100, 100);
    }

    public static String dateFormater(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return date.format(formatter);
    }

    public static String reverseWords(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        String[] arr = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; --i) {
            if (!arr[i].equals("")) {
                sb.append(arr[i]).append(" ");
            }
        }
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }
}
