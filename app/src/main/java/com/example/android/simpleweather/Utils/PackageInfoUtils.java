package com.example.android.simpleweather.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class PackageInfoUtils {


    /**
     * 获得版本名称
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {


// 获得包管理器
        PackageManager pm = context.getPackageManager();


        try {
            PackageInfo packageInfo = pm.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;


        } catch (PackageManager.NameNotFoundException e) {
// e.printStackTrace();
            return "未知版本号";
        }
    }


    /**
     * 获得版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
// 获得包管理器
        PackageManager pm = context.getPackageManager();


        try {
            PackageInfo packageInfo = pm.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;


        } catch (PackageManager.NameNotFoundException e) {
// e.printStackTrace();
            return -1;
        }
    }
}