package com.zrf.reader.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * User: zrf (625001446aq@gmail.com)
 * DateTime: 2016-02-28 14:59
 * Http操作工具类 判断网络是否连接  httpGet  httpPost
 */
public class NetWorkUtils {

    public static final String NETWORK_ERROR = "not network connect";

    public static boolean isNetWorkConnected(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context can not be null");
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null) {
            return info.isAvailable();
        }
        return false;
    }


}

