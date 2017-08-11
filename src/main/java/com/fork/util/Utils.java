package com.fork.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String fixLengthStr(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }

    public static Double round(Double value){
        return Math.round( value * 100.0 ) / 100.0;
    }

//    public static boolean checkRegExp(String str, String regex){
//        Pattern p = Pattern.compile(replaceSlesh(regex));
//        Matcher m = p.matcher(replaceSlesh(str));
//        return m.matches();
//    }

    public static String replaceSlesh(String str){
        return str.replaceAll("/", " ");
    }

}
