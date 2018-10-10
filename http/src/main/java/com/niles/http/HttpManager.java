package com.niles.http;

/**
 * Created by Niles
 * Date 2018/10/10 10:05
 * Email niulinguo@163.com
 */
public class HttpManager {

    private HttpConfig mHttpConfig;

    public HttpConfig getHttpConfig() {
        return mHttpConfig;
    }

    public HttpManager setHttpConfig(HttpConfig httpConfig) {
        mHttpConfig = httpConfig;
        return this;
    }

    public <S> S createService(Class<S> serviceClass) {
        return RetrofitApiFactory.create(mHttpConfig, serviceClass);
    }
}
