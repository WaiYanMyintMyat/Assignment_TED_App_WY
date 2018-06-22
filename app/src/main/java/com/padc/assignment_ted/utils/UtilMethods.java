package com.padc.assignment_ted.utils;

public class UtilMethods {
    public static String getHourMinuteSecond(Double seconds){

        long value = seconds.longValue();
        int hours = (int) value/3600;
        int remainder = (int) value - hours * 3600;
        int minutes = remainder/60;
        remainder = remainder - minutes * 60;
        int secs = remainder;
        StringBuilder stringBuilder = new StringBuilder();
        if (hours > 0){
            stringBuilder.append(hours).append(":");
        }
        if (minutes > 0 ){
            stringBuilder.append(minutes).append(":");
        }
        if(seconds > 0 ){
            stringBuilder.append(secs);
        }
        return stringBuilder.toString();
    }
}
