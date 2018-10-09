package com.niles.http;

import android.util.Log;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Niles
 * Date 2018/10/9 14:06
 * Email niulinguo@163.com
 */
public class RetrofitApiFactory {

    private static final HashMap<HttpConfig, Retrofit> RETROFIT_MAP = new HashMap<>();
    private static final HashMap<HttpConfig, HashMap<Class, Object>> SERVICE_MAP = new HashMap<>();
    private static final HttpLoggingInterceptor HTTP_LOGGING_INTERCEPTOR = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            if (BuildConfig.DEBUG) {
                Log.e("http", message);
            }
        }
    });
    private static final Converter.Factory GSON_CONVERTER_FACTORY = GsonConverterFactory.create();

    static {
        HTTP_LOGGING_INTERCEPTOR.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private static Retrofit createRetrofit(HttpConfig httpConfig) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(httpConfig.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(httpConfig.getReadTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(httpConfig.getWriteTimeout(), TimeUnit.MILLISECONDS);
        if (httpConfig.getLogger() == null) {
            builder
                    .addInterceptor(HTTP_LOGGING_INTERCEPTOR);
        } else {
            builder
                    .addInterceptor(new HttpLoggingInterceptor(httpConfig.getLogger()));
        }
        OkHttpClient okHttpClient = builder.build();

        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(httpConfig.getBaseUrl())
                .addConverterFactory(GSON_CONVERTER_FACTORY)
                .build();
    }

    public static <S> S create(HttpConfig httpConfig, Class<S> serviceClass) {

        HashMap<Class, Object> serviceMap = SERVICE_MAP.get(httpConfig);
        if (serviceMap == null) {
            SERVICE_MAP.put(httpConfig, serviceMap = new HashMap<>());
        }

        //noinspection unchecked
        S service = (S) serviceMap.get(serviceClass);
        if (service == null) {

            Retrofit retrofit = RETROFIT_MAP.get(httpConfig);
            if (retrofit == null) {
                RETROFIT_MAP.put(httpConfig, retrofit = createRetrofit(httpConfig));
            }

            serviceMap.put(serviceClass, service = retrofit.create(serviceClass));
        }

        return service;
    }
}
