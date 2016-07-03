package com.zrf.reader;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zrf.reader.http.MyHttpClient;

/**
 * User: zrf (625001446aq@gmail.com)
 * DateTime: 2016-03-11 20:17
 * 初始化ImageLoader 采用默认设置
 * 初始化Stetho：通过Chrome抓包
 */
public class ReaderApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
        Stetho.initializeWithDefaults(this);
        MyHttpClient.initInstance();
    }

    private void initImageLoader(Context context) {
        ImageLoaderConfiguration config=ImageLoaderConfiguration.createDefault(context);
        ImageLoader.getInstance().init(config);
    }
}
