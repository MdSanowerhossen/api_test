package com.example.testapp.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

public class Utils {
    public static boolean broadcastIntent(Context context, View view) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
        if (activeInfo != null) {
            if (activeInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            } else if (activeInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        } else {
            return false;
        }

        return false;
    }
}
