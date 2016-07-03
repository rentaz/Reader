package com.zrf.reader.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * User: zrf (625001446aq@gmail.com)
 * DateTime: 2016-03-02 15:16
 * SharedPreferences工具类
 * save(Context context, Map<String,String> data)  String get(Context context,String key)
 */
public class SPUtils {

    private static final String SP_TAG="READER_APP";
    public static final String EMAIL="email";

    public static final void save(Context context, Map<String,String> data){
        SharedPreferences sp=context.getSharedPreferences(SP_TAG,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        if(!MapUtils.isEmpty(data)){
            for (Map.Entry<String,String> entry : data.entrySet()) {
                editor.putString(entry.getKey(),entry.getValue());
            }
            editor.commit();
        }
    }

    public static final String get(Context context,String key){
        SharedPreferences sp=context.getSharedPreferences(SP_TAG,Context.MODE_PRIVATE);
        return sp.getString(key,null);
    }
}
