package com.zrf.reader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.victor.loading.book.BookLoading;
import com.zrf.reader.http.MyHttpClient;
import com.zrf.reader.http.MyHttpUrl;
import com.zrf.reader.utils.SPUtils;
import com.zrf.reader.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shem.com.materiallogin.MaterialLoginView;
import shem.com.materiallogin.MaterialLoginViewListener;

/**
 * User: zrf (625001446aq@gmail.com)
 * DateTime: 2016-02-23 19:24
 * 登录界面：处理登录和注册
 */
public class LoginActivity extends Activity {

    @Bind(R.id.mlv_login)
    MaterialLoginView mlv_login;
    @Bind(R.id.bl_loading)
    BookLoading bl_loading;

    private EditText mRegisterName, mRegisterUser, mRegisterPass, mRegisterPassRep;
    private EditText mLoginUser, mLoginPass;
    private TextView mRegisterBtn, mLoginBtn;
    private Boolean mIsRegisterViewGone = false;

    private final int LOGIN_FAIL = 0;
    private final int LOGIN_FAIL_PASS_ERROR = 1;
    private final int LOGIN_SUCCESS = 2;
    private final int REGISTER_FAIL = 3;
    private final int REGISTER_FAIL_EXIST = 4;
    private final int REGISTER_SUCCESS = 5;
    private final int LOADING_START=6;

    private Handler mHandler = new Handler() {
        //处理okhttp httpPost 的异步回调
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOADING_START:
                    bl_loading.setVisibility(View.VISIBLE);
                    bl_loading.start();
                    break;
                case LOGIN_FAIL:
                    Utils.ToastMsg(LoginActivity.this, "login fail", Toast.LENGTH_SHORT);
                    setLoginEnable(true);
                    bl_loading.stop();
                    bl_loading.setVisibility(View.GONE);
                    break;
                case LOGIN_FAIL_PASS_ERROR:
                    Utils.ToastMsg(LoginActivity.this, "email or password not right",
                            Toast.LENGTH_SHORT);
                    setLoginEnable(true);
                    bl_loading.stop();
                    bl_loading.setVisibility(View.GONE);
                    break;
                case LOGIN_SUCCESS:
                    Utils.ToastMsg(LoginActivity.this, "login ok", Toast.LENGTH_SHORT);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    bl_loading.stop();
                    bl_loading.setVisibility(View.GONE);
                    //                    setLoginEnable(true);
                    break;
                case REGISTER_FAIL:
                    mIsRegisterViewGone = false;
                    setRegisterEnable(true);
                    Utils.ToastMsg(LoginActivity.this, "register fail", Toast.LENGTH_SHORT);
                    break;
                case REGISTER_FAIL_EXIST:
                    mIsRegisterViewGone = false;
                    setRegisterEnable(true);
                    Utils.ToastMsg(LoginActivity.this, "name or email is exist",
                            Toast.LENGTH_SHORT);
                    break;
                case REGISTER_SUCCESS:
                    mIsRegisterViewGone = true;
                    Utils.ToastMsg(LoginActivity.this, "register ok", Toast.LENGTH_SHORT);
                    setRegisterEnable(true);
                    break;
                default:
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mlv_login.setListener(new MaterialLoginViewListener() {
            //注册的回调 注册：name email pass passRep
            @Override
            public Boolean onRegister(TextInputLayout registerName, TextInputLayout registerUser,
                                      TextInputLayout registerPass,
                                      TextInputLayout registerPassRep, TextView registerBtn) {
                initRegisterView(registerName, registerUser, registerPass,
                        registerPassRep, registerBtn);

                final String name = registerName.getEditText().getText().toString();
                final String email = registerUser.getEditText().getText().toString();
                final String pass = registerPass.getEditText().getText().toString();
                final String passRep = registerPassRep.getEditText().getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email)
                        || TextUtils.isEmpty(pass) || TextUtils.isEmpty(passRep)) {
                    Utils.ToastMsg(LoginActivity.this, "name,email,pass,passRep must not be empty",
                            Toast.LENGTH_SHORT);
                    return false;
                }
                if (!(email.endsWith("@qq.com") || email.endsWith("@gmail.com") ||
                        email.endsWith("@foxmail.com") || email.endsWith("@163.com") ||
                        email.endsWith("@yahoo.com"))) {
                    if (!pass.equals(passRep)) {
                        Utils.ToastMsg(LoginActivity.this, "email format error \n" +
                                "pass and passRep not Consistency", Toast.LENGTH_LONG);
                    } else {
                        Utils.ToastMsg(LoginActivity.this, "email format error",
                                Toast.LENGTH_LONG);
                    }
                    return false;
                } else if (!pass.equals(passRep)) {
                    Utils.ToastMsg(LoginActivity.this, "pass and passRep not Consistency",
                            Toast.LENGTH_SHORT);
                    return false;
                }

                setRegisterEnable(false);
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", pass);

                //httpPost的回调 异步（可以封装一下）
                MyHttpClient.httpPost(MyHttpUrl.HTTP_URL + MyHttpUrl.REGISTER, params,
                        new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mHandler.sendEmptyMessage(REGISTER_FAIL);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            if (jsonObject.getString("register").equals("true")) {
                                mHandler.sendEmptyMessage(REGISTER_SUCCESS);
                            } else {
                                mHandler.sendEmptyMessage(REGISTER_FAIL_EXIST);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return mIsRegisterViewGone;
            }

            //登录的回调  登录：email password
            @Override
            public Boolean onLogin(TextInputLayout loginUser, TextInputLayout loginPass,
                                   TextView loginBtn) {

                initLoginView(loginUser, loginPass, loginBtn);

                final String email = loginUser.getEditText().getText().toString();
                final String password = loginPass.getEditText().getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Utils.ToastMsg(LoginActivity.this, "email or password must not be empty",
                            Toast.LENGTH_SHORT);
                    return false;
                }

                setLoginEnable(false);
                mHandler.sendEmptyMessage(LOADING_START);
                final Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                //异步
                MyHttpClient.httpPost(MyHttpUrl.HTTP_URL +
                        MyHttpUrl.LOGIN, params, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mHandler.sendEmptyMessage(LOGIN_FAIL);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            if (jsonObject.getString("login").equals("true")) {
                                SPUtils.save(LoginActivity.this, params);
                                mHandler.sendEmptyMessage(LOGIN_SUCCESS);
                            } else {
                                mHandler.sendEmptyMessage(LOGIN_FAIL_PASS_ERROR);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return true;
            }
        });
    }

    private void initRegisterView(TextInputLayout registerName, TextInputLayout registerUser,
                                  TextInputLayout registerPass, TextInputLayout registerPassRep,
                                  TextView registerBtn) {
        mRegisterName = registerName.getEditText();
        mRegisterUser = registerUser.getEditText();
        mRegisterPass = registerPass.getEditText();
        mRegisterPassRep = registerPassRep.getEditText();
        mRegisterBtn = registerBtn;
    }

    private void initLoginView(TextInputLayout loginUser, TextInputLayout loginPass,
                               TextView loginBtn) {
        mLoginUser = loginUser.getEditText();
        mLoginPass = loginPass.getEditText();
        mLoginBtn = loginBtn;
    }

    private void setRegisterEnable(Boolean enable) {
        mRegisterName.setEnabled(enable);
        mRegisterUser.setEnabled(enable);
        mRegisterPass.setEnabled(enable);
        mRegisterPassRep.setEnabled(enable);
        mRegisterBtn.setEnabled(enable);
    }

    private void setLoginEnable(Boolean enable) {
        mLoginUser.setEnabled(enable);
        mLoginPass.setEnabled(enable);
        mLoginBtn.setEnabled(enable);
    }
}
