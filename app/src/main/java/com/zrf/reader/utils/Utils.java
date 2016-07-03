package com.zrf.reader.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * User: zrf (625001446aq@gmail.com)
 * DateTime: 2016-02-28 15:28
 * 应用工具类：常用操作  Toast SnackBar Dialog
 */
public class Utils {


    public static void ToastMsg(Context context,String msg,int type){
        if(type!=Toast.LENGTH_LONG&&type!=Toast.LENGTH_SHORT){
            throw new IllegalArgumentException("Toast Type must be " +
                    "Toast.LENGTH_LONG or Toast.LENGTH_SHORT");
        }
        switch (type){
            case Toast.LENGTH_LONG:
                Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
                break;
            case Toast.LENGTH_SHORT:
                Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }


}
