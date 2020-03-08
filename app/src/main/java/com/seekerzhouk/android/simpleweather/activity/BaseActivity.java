package com.seekerzhouk.android.simpleweather.activity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TimePicker;

import com.seekerzhouk.android.simpleweather.R;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarAndStatusBarColor();
    }

    private void setActionBarAndStatusBarColor() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        int statusColor;
        int actionBarColor;
        TimePicker timePicker = new TimePicker(this);
        int hour = timePicker.getHour();
        if (hour >= 6 && hour <= 11) {
            statusColor = getColor(R.color.morning_status);
            actionBarColor = getColor(R.color.morning_actionbar);
        } else if (hour > 11 && hour < 14) {
            statusColor = getColor(R.color.noon_status);
            actionBarColor = getColor(R.color.noon_actionbar);
        } else if (hour >= 14 && hour <=18) {
            statusColor = getColor(R.color.afternoon_status);
            actionBarColor = getColor(R.color.afternoon_actionbar);
        } else {
            statusColor = getColor(R.color.night_status);
            actionBarColor = getColor(R.color.night_actionbar);
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        getWindow().setStatusBarColor(statusColor); // 设置状态栏颜色
        ColorDrawable drawable = new ColorDrawable();
        drawable.setColor(actionBarColor);
        actionBar.setBackgroundDrawable(drawable); // 设置actionbar颜色
    }
}
