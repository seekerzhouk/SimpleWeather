package com.example.android.simpleweather.utils;

public class ConfigURL {
    final static String key =
            "&key=649471936300090a4d76086198fa40a1";
    public final static String citiesURL =
            "http://v.juhe.cn/weather/citys?key=5789543e92b113c9d506e3d8274f11a8";
    private  static String weatherURL1 =
            "http://v.juhe.cn/weather/index?format=2&cityname=";

    public static String getWeatherURL(String cityId){
        return weatherURL1 + cityId + key;
    }
}
