package com.zrf.reader.model;

/**
 * User: zrf (625001446aq@gmail.com)
 * DateTime: 2016-02-28 19:11
 * 用户信息实体类 ：name email password introduction icon
 */
public class UserInfo {
    private String mName;
    private String mEmail;
    private String mPassword;
    private String mIntroduction;
    private String mIcon;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getIntroduction() {
        return mIntroduction;
    }

    public void setIntroduction(String introduction) {
        mIntroduction = introduction;
    }
}
