package com.niles.http;

/**
 * Created by Niles
 * Date 2018/10/10 10:05
 * Email niulinguo@163.com
 */
public class HttpManager {

    private static HttpConfig sHttpConfig;

    public static void setHttpConfig(HttpConfig httpConfig) {
        sHttpConfig = httpConfig;
    }

    public static <S> S createService(Class<S> serviceClass) {
        return RetrofitApiFactory.create(sHttpConfig, serviceClass);
    }
}
