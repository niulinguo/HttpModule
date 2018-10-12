package com.niles.httpmodule;

import android.app.Application;
import android.util.Log;

import com.niles.http.HttpConfig;
import com.niles.http.HttpManager;
import com.niles.http.converter.StringConverterFactory;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Niles
 * Date 2018/10/12 13:06
 * Email niulinguo@163.com
 */
public class MyApp extends Application {

    private HttpManager mHttpManager = new HttpManager();

    @Override
    public void onCreate() {
        super.onCreate();

        HttpConfig httpConfig = new HttpConfig.Builder()
                .setBaseUrl("http://www.baidu.com")
                .setLogger(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.e("http", message);
                    }
                })
                .addConverterFactory(StringConverterFactory.create())
                .build();

        mHttpManager = new HttpManager()
                .setHttpConfig(httpConfig);
    }

    public <S> S getService(Class<S> serviceClass) {
        return mHttpManager.getService(serviceClass);
    }
}
