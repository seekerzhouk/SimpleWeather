package com.example.android.simpleweather.Utils;

public class ConfigURL {
    final static String key =
            "&key=5789543e92b113c9d506e3d8274f11a8";
    public final static String citiesURL =
            "http://v.juhe.cn/weather/citys?key=5789543e92b113c9d506e3d8274f11a8";
    private  static String weatherURL1 =
            "http://v.juhe.cn/weather/index?format=2&cityname=";

    public static String getWeatherURL(String cityId){
        return weatherURL1 + cityId + key;
    }
}
