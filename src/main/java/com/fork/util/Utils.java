package com.fork.util;

public class Utils {

    public static double round(double value, double floatValue){
        return Math.round( value * floatValue ) / floatValue;
    }

}
