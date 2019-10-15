package com.example.android.simpleweather.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.simpleweather.bean.JsonBean;
import com.example.android.simpleweather.R;
import com.example.android.simpleweather.utils.SetIcon;
import com.example.android.simpleweather.utils.MyDateFormat;
import com.example.android.simpleweather.utils.SpUtils;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    //Item设置监听器
    public interface OnItemClickListener{
        void onClick(int position);
        void setLocationClick();
    }
    private OnItemClickListener mOnItemClickListener;
    private Context mContext;
    private JsonBean jsonBean;

    public RecyclerViewAdapter(Context context, OnItemClickListener onItemClickListener){
        this.mContext = context;
        this.mOnItemClickListener = onItemClickListener;
        this.jsonBean = SpUtils.getObject(context,JsonBean.class);
    }

    /**
     *根据不同的position，设置不同的ViewType
     *position表示当前是第几个Item，通过position拿到当前的Item对象，然后判断这个item对象需要那种视图
     */
    public int getItemViewType(int positon){
        return positon;
    }


    @NonNull
    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder(当RecyclerView需要一个ViewHolder时会回调该方法，
    // 如果有可复用的View不会回调)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            View view = LayoutInflater.from(mContext).inflate(R.layout.today_item, parent, false);
            return new TodayViewHolder(view);
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
            return new LinearViewHolder(view,viewType);
        }
    }

    //填充onCreateViewHolder方法返回的holder中的控件(当一个View需要出现在屏幕上时，
    // 该方法会被回调，我们需要再该方法中根据数据来更改视图)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        if (mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });
        }
    }

    //获取数据的数量(告诉RecyclerView有多少个视图需要显示)
    @Override
    public int getItemCount() {
        return 7;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private ImageView iImageWeather;
        private TextView iTextDay, iTextDate, iTextWeather, iTextMaxTemp;

        LinearViewHolder(View itemView, int viewType) {
            super(itemView);
            iImageWeather = itemView.findViewById(R.id.iv_item_weather);
            iTextDay = itemView.findViewById(R.id.tv_item_day);
            iTextDate = itemView.findViewById(R.id.tv_item_date);
            iTextWeather = itemView.findViewById(R.id.tv_item_weather);
            iTextMaxTemp = itemView.findViewById(R.id.tv_item_max_temp);

            List<JsonBean.ResultBean.FutureBean> list = jsonBean.getResult().getFuture();


            iTextDay.setText(list.get(viewType).getWeek());
            iTextDate.setText(MyDateFormat.strDate(list.get(viewType).getDate()));
            iTextWeather.setText(list.get(viewType).getWeather());
            iTextMaxTemp.setText(list.get(viewType).getTemperature());

            SetIcon.setWeatherIcon(iImageWeather,list.get(viewType).getWeather_id().getFa());

        }
    }

    class TodayViewHolder extends RecyclerView.ViewHolder{
        private ImageView tImageLocation, tImageWeather;
        private TextView tTextDay,tTextDate,tTextWeather,tTextMaxTemp,tTextLocation;

        TodayViewHolder(View itemView) {
            super(itemView);
            tImageLocation = itemView.findViewById(R.id.iv_today_location);
            tImageWeather = itemView.findViewById(R.id.iv_today_weather);
            tTextDay = itemView.findViewById(R.id.tv_today_day);
            tTextDate = itemView.findViewById(R.id.tv_today_date);
            tTextWeather = itemView.findViewById(R.id.tv_today_weather);
            tTextMaxTemp = itemView.findViewById(R.id.tv_today_max_temp);
            tTextLocation = itemView.findViewById(R.id.tv_today_location);

            tTextDay.getPaint().setFakeBoldText(true);
            tTextMaxTemp.getPaint().setFakeBoldText(true);

            //点击位置图标和位置TextView跳转到设置城市界面
            tImageLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   mOnItemClickListener.setLocationClick();
                }
            });
            tTextLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.setLocationClick();
                }
            });

            tTextDay.setText(jsonBean.getResult().getToday().getWeek());
            tTextDate.setText(jsonBean.getResult().getToday().getDate_y());
            tTextWeather.setText(jsonBean.getResult().getToday().getWeather());
            tTextMaxTemp.setText(jsonBean.getResult().getToday().getTemperature());
            tTextLocation.setText(jsonBean.getResult().getToday().getCity());
            SetIcon.setWeatherIcon(tImageWeather,jsonBean.getResult().getToday().getWeather_id().getFa());
        }
    }

}
