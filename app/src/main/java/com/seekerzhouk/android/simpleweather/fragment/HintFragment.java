package com.seekerzhouk.android.simpleweather.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.seekerzhouk.android.simpleweather.R;

public class HintFragment extends Fragment {

    private final String TAG = HintFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button setCity = view.findViewById(R.id.btn_set_city);
        setCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ((SetCityCallBack) getActivity()).setCity();
                } catch (Exception e) {
                    Log.e(TAG, "The activity this fragment located must implement SetCityCallBack.");
                }
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    public interface SetCityCallBack {
        void setCity();
    }

}
