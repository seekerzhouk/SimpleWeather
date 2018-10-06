package com.example.android.simpleweather.utils;

import android.util.Log;
import android.widget.ImageView;

import com.example.android.simpleweather.R;

public class SetIcon {
    public static void setWeatherIcon(ImageView imageView, String weatherId){
        switch (weatherId){
            case "00":
                imageView.setImageResource(R.drawable.ic_00);
                break;
            case "01":
                imageView.setImageResource(R.drawable.ic_01);
                break;
            case "02":
                imageView.setImageResource(R.drawable.ic_02);
                break;
            case "03":
                imageView.setImageResource(R.drawable.ic_03);
                break;
            case "04":
                imageView.setImageResource(R.drawable.ic_04);
                break;
            case "05":
                imageView.setImageResource(R.drawable.ic_05);
                break;
            case "06":
                imageView.setImageResource(R.drawable.ic_06);
                break;
            case "07":
                imageView.setImageResource(R.drawable.ic_07);
                break;
            case "08":
                imageView.setImageResource(R.drawable.ic_08);
                break;
            case "09":
                imageView.setImageResource(R.drawable.ic_09);
                break;
            case "10":
                imageView.setImageResource(R.drawable.ic_10);
                break;
            case "11":
                imageView.setImageResource(R.drawable.ic_11);
                break;
            case "12":
                imageView.setImageResource(R.drawable.ic_12);
                break;
            case "13":
                imageView.setImageResource(R.drawable.ic_13);
                break;
            case "14":
                imageView.setImageResource(R.drawable.ic_14);
                break;
            case "15":
                imageView.setImageResource(R.drawable.ic_15);
                break;
            case "16":
                imageView.setImageResource(R.drawable.ic_16);
                break;
            case "17":
                imageView.setImageResource(R.drawable.ic_17);
                break;
            case "18":
                imageView.setImageResource(R.drawable.ic_18);
                break;
            case "19":
                imageView.setImageResource(R.drawable.ic_19);
                break;
            case "20":
                imageView.setImageResource(R.drawable.ic_20);
                break;
            case "21":
                imageView.setImageResource(R.drawable.ic_21);
                break;
            case "22":
                imageView.setImageResource(R.drawable.ic_22);
                break;
            case "23":
                imageView.setImageResource(R.drawable.ic_23);
                break;
            case "24":
                imageView.setImageResource(R.drawable.ic_24);
                break;
            case "25":
                imageView.setImageResource(R.drawable.ic_25);
                break;
            case "26":
                imageView.setImageResource(R.drawable.ic_26);
                break;
            case "27":
                imageView.setImageResource(R.drawable.ic_27);
                break;
            case "28":
                imageView.setImageResource(R.drawable.ic_28);
                break;
            case "29":
                imageView.setImageResource(R.drawable.ic_29);
                break;
            case "30":
                imageView.setImageResource(R.drawable.ic_30);
                break;
            case "31":
                imageView.setImageResource(R.drawable.ic_31);
                break;
            case "53":
                imageView.setImageResource(R.drawable.ic_53);
                break;
                default:
                    Log.d("SetIcon!!!!", "------------wrong weatehrId-------------- ");
                    break;

        }

    }
}
