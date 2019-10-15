package com.seekerzhouk.android.simpleweather.utils;

public class MyDateFormat {

    /**
     *  将得到的日期格式化成“yyyy-mm-dd”的形式
     *  @param date  “yyyymmdd”的String形式
     */
    public static String strDate(String date){
        return date.substring(0,4) + "-" + date.substring(4,6) + "-" + date.substring(6);
    }
}
