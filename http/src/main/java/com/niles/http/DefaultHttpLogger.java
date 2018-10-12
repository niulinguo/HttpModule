package com.niles.http;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Niles
 * Date 2018/10/12 11:36
 * Email niulinguo@163.com
 */
public class DefaultHttpLogger implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        if (BuildConfig.DEBUG) {
            Log.e("http", message);
        }
    }
}
