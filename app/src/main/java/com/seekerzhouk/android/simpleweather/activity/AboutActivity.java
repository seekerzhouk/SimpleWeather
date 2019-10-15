package com.seekerzhouk.android.simpleweather.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.seekerzhouk.android.simpleweather.utils.PackageInfoUtils;
import com.seekerzhouk.android.simpleweather.R;

public class AboutActivity extends AppCompatActivity {

    private TextView appName,version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        appName = findViewById(R.id.tv_app_name);
        version = findViewById(R.id.tv_version_name);

        version.setText(PackageInfoUtils.getVersionName(this));
        appName.getPaint().setFakeBoldText(true);
    }
}
