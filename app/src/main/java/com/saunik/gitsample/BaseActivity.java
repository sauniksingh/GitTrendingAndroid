package com.saunik.gitsample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Saunik Singh on 27/8/18.
 * Cars24 Services Private Limited.
 */
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    public static boolean isNetworkEnabled(Context pContext) {
        NetworkInfo activeNetwork = getActiveNetwork(pContext);
        return activeNetwork != null && activeNetwork.isConnected();
    }

    /**
     * @param pContext {@link Context}
     * @return NetworkInfo
     * note android.permission.ACCESS_NETWORK_STATE is required
     */
    public static NetworkInfo getActiveNetwork(Context pContext) {
        ConnectivityManager conMngr = (ConnectivityManager) pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return conMngr == null ? null : conMngr.getActiveNetworkInfo();
    }
}
