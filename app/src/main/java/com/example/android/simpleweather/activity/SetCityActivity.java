package com.example.android.simpleweather.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.android.simpleweather.bean.CityBean;
import com.example.android.simpleweather.R;
import com.example.android.simpleweather.utils.ConfigURL;
import com.example.android.simpleweather.utils.SpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SetCityActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerProvince, spinnerCity, spinnerDistrict;
    public static String strDistrict;
    CityBean cityBean = null;

    List<CityBean.ResultBean> citieslist = null;
    List<String> pList = new ArrayList<>();//存放省
    List<String> cList = new ArrayList<>();//存放市
    List<String> dList = new ArrayList<>();//存放县

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_city);
        spinnerProvince = findViewById(R.id.spinner_province);
        spinnerCity = findViewById(R.id.spinner_city);
        spinnerDistrict = findViewById(R.id.spinner_district);
        Button confirm = findViewById(R.id.button_confirm);

        //设置spinner的监听器
        spinnerProvince.setOnItemSelectedListener(this);
        spinnerCity.setOnItemSelectedListener(this);
        spinnerDistrict.setOnItemSelectedListener(this);

        //程序第一次运行时，cityBean==null
        //程序不是第一次运行，直接从SpUtils获得cityBean对象；
        cityBean = SpUtils.getObject(SetCityActivity.this, CityBean.class);
        loadCitiesData();


        //点击“确定”按钮，返回主界面并刷新天气信息
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent(SetCityActivity.this, MainActivity.class);
                data.putExtra("selectedDistrict", strDistrict);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }


    private void loadCitiesData() {
        //程序第一次运行时，cityBean==null，通过URL获取城市JSON数据
        //程序不是第一次运行，说明已经直接从SpUtils获得cityBean对象；
        if (cityBean == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();
            Request request = new Request.Builder()
                    .url(ConfigURL.citiesURL)
                    .build();

            Call call = okHttpClient.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    String string = response.body().string();
                    Log.i("---", "onResponse: " + string);

                    //获得CityBean对象
                    Gson gson = new Gson();
                    final CityBean cityBean = gson.fromJson(string, CityBean.class);
                    //保存cityBean对象
                    SpUtils.putObject(SetCityActivity.this, cityBean);

                    //在UI线程中对控件进行刷新
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            processCitiesData(cityBean);
                        }
                    });
                }
            });
        } else {
            //处理得到的城市数据
            processCitiesData(cityBean);
        }

    }


    private void processCitiesData(CityBean cityBean) {
        //获得城市列表
        citieslist = cityBean.getResult();

        //借助HashSet去除重复的省
        HashSet<String> hashSet = new HashSet<>();
        //遍历出省份,把省份配置到spinnerProvince；
        for (int i = 0; i < citieslist.size(); i++) {
            CityBean.ResultBean element = citieslist.get(i);
            if (hashSet.add(element.getProvince())) {
                pList.add(element.getProvince());
            }
        }
        setSpinnerAdapter(this, spinnerProvince, pList);


    }

    //遍历出市区,把市区配置到spinnerCity；
    private void selectCity(String strProvince) {
        //遍历出市级单位
        HashSet<String> hashSet = new HashSet<>();
        for (int i = 0; i < citieslist.size(); i++) {
            CityBean.ResultBean element = citieslist.get(i);
            if (element.getProvince().equals(strProvince)) {
                if (hashSet.add(element.getCity())) {
                    cList.add(element.getCity());
                }
            }
        }
        setSpinnerAdapter(this, spinnerCity, cList);

    }

    //遍历出县区,把县区配置到spinnerDistrict；
    private void selectDisrict(String strCity) {
        //遍历出市级单位
        HashSet<String> hashSet = new HashSet<>();
        for (int i = 0; i < citieslist.size(); i++) {
            CityBean.ResultBean element = citieslist.get(i);
            if (element.getCity().equals(strCity)) {
                if (hashSet.add(element.getDistrict())) {
                    dList.add(element.getDistrict());
                }
            }
        }
        setSpinnerAdapter(this, spinnerDistrict, dList);
    }

    //spinner的事件响应
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_province:
                cList.clear();
                String strProvince = pList.get(position);
                selectCity(strProvince);
                break;
            case R.id.spinner_city:
                dList.clear();
                String strCity = cList.get(position);
                selectDisrict(strCity);
                break;
            case R.id.spinner_district:
                strDistrict = dList.get(position);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setSpinnerAdapter(Context context, Spinner spinner, List<String> list) {
        //1.数据源为list;

        //2.新建ArrayAdapter 数组适配器
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, list);

        //3.ArrayAdapter设置一个下拉列表样式
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //4.spinner加载适配器
        spinner.setAdapter(arrayAdapter);
    }

}
