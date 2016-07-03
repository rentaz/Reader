package com.zrf.reader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.zrf.reader.http.NetWorkUtils;
import com.zrf.reader.utils.SPUtils;
import com.zrf.reader.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: zrf (625001446aq@gmail.com)
 * DateTime: 2016-02-23 15:13
 * 欢迎界面：判断网络环境 处理是转到登录界面还是主界面
 */
public class WelcomeActivity extends Activity {

    @Bind(R.id.iv_splash)
    ImageView iv_splash;
    private boolean mIsLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //是否已经登录  如果之前登录过  直接进入主界面  如果没有 则进入登录界面
        mIsLogin=SPUtils.get(this,SPUtils.EMAIL)==null?false:true;
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash);
        iv_splash.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!NetWorkUtils.isNetWorkConnected(WelcomeActivity.this)) {
                    Utils.ToastMsg(WelcomeActivity.this, NetWorkUtils.NETWORK_ERROR,
                            Toast.LENGTH_LONG);
                }
                if (mIsLogin) {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
