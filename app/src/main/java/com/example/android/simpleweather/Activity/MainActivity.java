package com.example.android.simpleweather.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.android.simpleweather.AFragment;
import com.example.android.simpleweather.Utils.ConfigURL;
import com.example.android.simpleweather.Models.JsonBean;
import com.example.android.simpleweather.R;
import com.example.android.simpleweather.Adapter.RecyclerViewAdapter;
import com.example.android.simpleweather.Utils.SpUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mrecyclerView;
    static String district = null;
    public static final int REQUEST_CODE_TO_SET_CITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mrecyclerView = findViewById(R.id.recycler_view);

        refresh();

        //设置分割线
        mrecyclerView.addItemDecoration(new MyDecoration());
    }

    private void refresh(){
        Log.d("---TAG---", "refresh: "+district);
        //如果district不为空，主界面设置RecyclerView
        if (!(district ==null)){
            //通过URL获取选定district的天气信息
            String weatherUrl = ConfigURL.getWeatherURL(district);
            OkHttpUtils.get().url(weatherUrl).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    //获得JsonBean对象
                    final JsonBean jsonBean = JSON.parseObject(response,JsonBean.class);
                    //保存jsonBean对象
                    SpUtils.putObject(MainActivity.this,jsonBean);

                    //设置点击事件
                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(
                            MainActivity.this, new RecyclerViewAdapter.OnItemClickListener() {
                        //跳转到天气详情界面
                        @Override
                        public void onClick(int position) {
                            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                            intent.putExtra("position", position);
                            startActivity(intent);
                        }
                        //点击位置图标和位置TextView跳转到设置城市界面
                        @Override
                        public void setLocationClick() {
                            Intent intent = new Intent(MainActivity.this, SetCityActivity.class);
                            startActivityForResult(intent,REQUEST_CODE_TO_SET_CITY);
                        }
                    });
                    //布局管理器
                    mrecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    //设置适配器
                    mrecyclerView.setAdapter(recyclerViewAdapter);
                }
            });

        //当district为空，主界面显示Fragment
        }else {
            AFragment afragment = new AFragment();
            getFragmentManager().beginTransaction().add(R.id.main_fragment, afragment).commitAllowingStateLoss();

        }
    }

    //分割线MyDecoration
    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight),0,0);
        }
    }

    //加载自定义标题栏
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //响应Action按钮的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_refresh:
                if (district == null) {
                    Toast.makeText(this, R.string.havent_set_location, Toast.LENGTH_SHORT).show();
                }else {
                    refresh();
                }
                return true;
            case R.id.action_set_location:
                Intent intent = new Intent(MainActivity.this, SetCityActivity.class);
                startActivityForResult(intent,REQUEST_CODE_TO_SET_CITY);
                return true;
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private long mBackPressedTime;
    //返回键的时间响应
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        long curTime = SystemClock.uptimeMillis();
        if ((curTime - mBackPressedTime) < (3 * 1000)) {
            finish();
        } else {
            mBackPressedTime = curTime;
            Toast.makeText(this, R.string.tip_double_click_exit, Toast.LENGTH_LONG).show();
//            Toast toast = new Toast(this);
//            TextView textView = new TextView(this);
//            textView.setText(R.string.tip_double_click_exit);
//            toast.setView(textView);
//            toast.show();
        }
    }

    //从设置城市界面回来时，得到所选地区
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("---TAG---", "onActivityResult1: "+district);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_TO_SET_CITY) {
            if (data == null) {
                return;
            }
            district = data.getStringExtra("selectedDistrict");
            Log.d("---TAG---", "onActivityResult2: "+district);
            refresh();
        }
    }
}
