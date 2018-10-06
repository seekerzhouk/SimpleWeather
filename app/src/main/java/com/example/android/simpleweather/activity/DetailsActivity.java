package com.example.android.simpleweather.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.simpleweather.bean.JsonBean;
import com.example.android.simpleweather.utils.MyDateFormat;
import com.example.android.simpleweather.R;
import com.example.android.simpleweather.utils.SetIcon;
import com.example.android.simpleweather.utils.SpUtils;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ImageView dImageWeather = findViewById(R.id.iv_details_weather);
        TextView dDay = findViewById(R.id.tv_details_day);
        TextView dDate = findViewById(R.id.tv_details_date);
        TextView dMaxTemp = findViewById(R.id.tv_details_max_temp);
        TextView dWindPower = findViewById(R.id.tv_details_wind_discription);
        TextView dLocation = findViewById(R.id.tv_details_location);
        TextView dWeather = findViewById(R.id.tv_details_weather);

        dDay.getPaint().setFakeBoldText(true);
        dMaxTemp.getPaint().setFakeBoldText(true);

        JsonBean jsonBean = SpUtils.getObject(DetailsActivity.this, JsonBean.class);
        assert jsonBean != null;
        List<JsonBean.ResultBean.FutureBean> list = jsonBean.getResult().getFuture();
        int position = getIntent().getIntExtra("position",0);

        dDay.setText(list.get(position).getWeek());
        dDate.setText(MyDateFormat.strDate(list.get(position).getDate()));
        dMaxTemp.setText(list.get(position).getTemperature());
        dWindPower.setText(list.get(position).getWind());
        dLocation.setText(jsonBean.getResult().getToday().getCity());
        dWeather.setText(list.get(position).getWeather());

        SetIcon.setWeatherIcon(dImageWeather,list.get(position).getWeather_id().getFa());

    }

}
