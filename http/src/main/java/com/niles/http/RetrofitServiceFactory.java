package com.niles.http;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Niles
 * Date 2018/10/9 14:06
 * Email niulinguo@163.com
 */
public class RetrofitServiceFactory {

    private static final HashMap<HttpConfig, Retrofit> RETROFIT_MAP = new HashMap<>();
    private static final HashMap<HttpConfig, HashMap<Class, Object>> SERVICE_MAP = new HashMap<>();

    private static Retrofit getRetrofit(HttpConfig config) {
        Retrofit retrofit = RETROFIT_MAP.get(config);
        if (retrofit != null) {
            return retrofit;
        }

        /*
        Build OkHttp Client
         */
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true);
        // Cache
        if (config.getCache() != null) {
            okHttpBuilder.cache(config.getCache());
        }
        // SSL
        if (config.getSSLSocketFactory() != null) {
            if (config.getX509TrustManager() != null) {
                okHttpBuilder.sslSocketFactory(config.getSSLSocketFactory(), config.getX509TrustManager());
            } else {
                //noinspection deprecation
                okHttpBuilder.sslSocketFactory(config.getSSLSocketFactory());
            }
        }
        // Hostname Verifier
        okHttpBuilder.hostnameVerifier(config.getHostnameVerifier());
        // Timeout
        okHttpBuilder
                .connectTimeout(config.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(config.getReadTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(config.getWriteTimeout(), TimeUnit.MILLISECONDS);
        // Set Interceptor
        List<Interceptor> interceptorList = config.getInterceptorList();
        if (!interceptorList.isEmpty()) {
            for (Interceptor interceptor : interceptorList) {
                okHttpBuilder.addInterceptor(interceptor);
            }
        }
        // Set Network Interceptor
        List<Interceptor> networkInterceptorList = config.getNetworkInterceptorList();
        if (!networkInterceptorList.isEmpty()) {
            for (Interceptor interceptor : networkInterceptorList) {
                okHttpBuilder.addNetworkInterceptor(interceptor);
            }
        }
        // Set Log Interceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(config.getLogger()).setLevel(config.getLevel());
        okHttpBuilder.addInterceptor(loggingInterceptor);
        // Build
        OkHttpClient okHttpClient = okHttpBuilder.build();

        /*
        Build Retrofit
         */
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(config.getBaseUrl());
        // CallAdapter Factory
        List<CallAdapter.Factory> callAdapterFactoryList = config.getCallAdapterFactoryList();
        if (!callAdapterFactoryList.isEmpty()) {
            for (CallAdapter.Factory factory : callAdapterFactoryList) {
                retrofitBuilder.addCallAdapterFactory(factory);
            }
        }
        // Converter Factory
        List<Converter.Factory> converterFactoryList = config.getConverterFactoryList();
        if (!converterFactoryList.isEmpty()) {
            for (Converter.Factory factory : converterFactoryList) {
                retrofitBuilder.addConverterFactory(factory);
            }
        }
        // Build
        RETROFIT_MAP.put(config, retrofit = retrofitBuilder.build());
        return retrofit;
    }

    public static <S> S getService(HttpConfig config, Class<S> serviceClass) {
        HashMap<Class, Object> serviceMap = SERVICE_MAP.get(config);
        if (serviceMap == null) {
            SERVICE_MAP.put(config, serviceMap = new HashMap<>());
        }

        //noinspection unchecked
        S service = (S) serviceMap.get(serviceClass);
        if (service == null) {
            serviceMap.put(serviceClass, service = getRetrofit(config).create(serviceClass));
        }

        return service;
    }
}
