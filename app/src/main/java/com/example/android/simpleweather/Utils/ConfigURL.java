package com.example.android.simpleweather.Utils;

public class ConfigURL {
    final static String key =
            "&key=649471936300090a4d76086198fa40a1";
    public final static String citiesURL =
            "http://v.juhe.cn/weather/citys?key=649471936300090a4d76086198fa40a1";
    private  static String weatherURL1 =
            "http://v.juhe.cn/weather/index?format=2&cityname=";

    public static String getWeatherURL(String cityId){
        return weatherURL1 + cityId + key;
    }
}
