package com.fork.util;

public class Utils {

    public static String fixLengthStr(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }

}
