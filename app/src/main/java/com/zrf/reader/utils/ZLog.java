package com.zrf.reader.utils;

import android.util.Log;

/**
 * Iwds日志类.
 */
public class ZLog {
    private static final String LOG_TAG_PREFIX = "ZZZ---";

    /**
     * 调试.
     *
     * @param who
     *            对象
     * @param message
     *            消息
     */
    public static void d(Object who, String message) {
            Log.d(LOG_TAG_PREFIX + who.getClass().getSimpleName(), message);
    }

    /**
     * 信息.
     *
     * @param who
     *            对象
     * @param message
     *            消息
     */
    public static void i(Object who, String message) {
        Log.i(LOG_TAG_PREFIX + who.getClass().getSimpleName(), message);
    }

    /**
     * 详细信息.
     *
     * @param who
     *            对象
     * @param message
     *            消息
     */
    public static void v(Object who, String message) {
        Log.v(LOG_TAG_PREFIX + who.getClass().getSimpleName(), message);
    }

    /**
     * 错误.
     *
     * @param who
     *            对象
     * @param message
     *            消息
     */
    public static void e(Object who, String message) {
        Log.e(LOG_TAG_PREFIX + who.getClass().getSimpleName(), message);
    }

    /**
     * 警告.
     *
     * @param who
     *            对象
     * @param message
     *            消息
     */
    public static void w(Object who, String message) {
        Log.w(LOG_TAG_PREFIX + who.getClass().getSimpleName(), message);
    }

    /**
     * 调试.
     *
     * @param who
     *            对象
     * @param message
     *            消息
     * @param tr
     *            异常
     */
    public static void d(Object who, String message, Throwable tr) {
            Log.d(LOG_TAG_PREFIX + who.getClass().getSimpleName(), message, tr);
    }

    /**
     * 信息.
     *
     * @param who
     *            对象
     * @param message
     *            消息
     * @param tr
     *            异常
     */
    public static void i(Object who, String message, Throwable tr) {
        Log.i(LOG_TAG_PREFIX + who.getClass().getSimpleName(), message, tr);
    }

    /**
     * 详细信息.
     *
     * @param who
     *            对象
     * @param message
     *            消息
     * @param tr
     *            异常
     */
    public static void v(Object who, String message, Throwable tr) {
        Log.v(LOG_TAG_PREFIX + who.getClass().getSimpleName(), message, tr);
    }

    /**
     * 错误.
     *
     * @param who
     *            对象
     * @param message
     *            消息
     * @param tr
     *            异常
     */
    public static void e(Object who, String message, Throwable tr) {
        Log.e(LOG_TAG_PREFIX + who.getClass().getSimpleName(), message, tr);
    }

    /**
     * 警告.
     *
     * @param who
     *            对象
     * @param message
     *            消息
     * @param tr
     *            异常
     */
    public static void w(Object who, String message, Throwable tr) {
        Log.w(LOG_TAG_PREFIX + who.getClass().getSimpleName(), message, tr);
    }

    /**
     * 调试.
     *
     * @param tag
     *            标签
     * @param message
     *            消息
     */
    public static void d(String tag, String message) {
            Log.d(LOG_TAG_PREFIX + tag, message);
    }

    /**
     * 信息.
     *
     * @param tag
     *            标签
     * @param message
     *            消息
     */
    public static void i(String tag, String message) {
        Log.i(LOG_TAG_PREFIX + tag, message);
    }

    /**
     * 详细信息.
     *
     * @param tag
     *            标签
     * @param message
     *            消息
     */
    public static void v(String tag, String message) {
        Log.v(LOG_TAG_PREFIX + tag, message);
    }

    /**
     * 错误.
     *
     * @param tag
     *            标签
     * @param message
     *            消息
     */
    public static void e(String tag, String message) {
        Log.e(LOG_TAG_PREFIX + tag, message);
    }

    /**
     * 警告.
     *
     * @param tag
     *            标签
     * @param message
     *            消息
     */
    public static void w(String tag, String message) {
        Log.w(LOG_TAG_PREFIX + tag, message);
    }

    /**
     * 调试.
     *
     * @param tag
     *            标签
     * @param message
     *            消息
     * @param tr
     *            异常
     */
    public static void d(String tag, String message, Throwable tr) {
            Log.d(LOG_TAG_PREFIX + tag, message, tr);
    }

    /**
     * 信息.
     *
     * @param tag
     *            标签
     * @param message
     *            消息
     * @param tr
     *            异常
     */
    public static void i(String tag, String message, Throwable tr) {
        Log.i(LOG_TAG_PREFIX + tag, message, tr);
    }

    /**
     * 详细信息.
     *
     * @param tag
     *            标签
     * @param message
     *            消息
     * @param tr
     *            异常
     */
    public static void v(String tag, String message, Throwable tr) {
        Log.v(LOG_TAG_PREFIX + tag, message, tr);
    }

    /**
     * 错误.
     *
     * @param tag
     *            标签
     * @param message
     *            消息
     * @param tr
     *            异常
     */
    public static void e(String tag, String message, Throwable tr) {
        Log.e(LOG_TAG_PREFIX + tag, message, tr);
    }

    /**
     * 警告.
     *
     * @param tag
     *            标签
     * @param message
     *            消息
     * @param tr
     *            异常
     */
    public static void w(String tag, String message, Throwable tr) {
        Log.w(LOG_TAG_PREFIX + tag, message, tr);
    }
}
