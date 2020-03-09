package com.seekerzhouk.android.simpleweather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.seekerzhouk.android.simpleweather.R;

public class NetWorkStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                switch (networkInfo.getType()) {
                    case ConnectivityManager.TYPE_WIFI:
                        Toast.makeText(context, R.string.wifi_connected, Toast.LENGTH_SHORT).show();
                        break;
                    case ConnectivityManager.TYPE_MOBILE:
                        Toast.makeText(context, R.string.mobile_connected, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            } else {
                Toast.makeText(context, R.string.network_disconnected, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
