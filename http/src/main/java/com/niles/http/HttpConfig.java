package com.niles.http;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/**
 * Created by Niles
 * Date 2018/10/9 14:17
 * Email niulinguo@163.com
 */
public class HttpConfig {

    private final String mBaseUrl;
    private final long mConnectTimeout;
    private final long mReadTimeout;
    private final long mWriteTimeout;
    private final HttpLoggingInterceptor.Logger mLogger;
    private final HttpLoggingInterceptor.Level mLevel;
    private final List<Interceptor> mInterceptorList;
    private final List<Interceptor> mNetworkInterceptorList;
    private final List<CallAdapter.Factory> mCallAdapterFactoryList;
    private final List<Converter.Factory> mConverterFactoryList;
    private final HostnameVerifier mHostnameVerifier;
    private final SSLSocketFactory mSSLSocketFactory;
    private final X509TrustManager mX509TrustManager;
    private final Cache mCache;

    private HttpConfig(String baseUrl, long connectTimeout, long readTimeout, long writeTimeout, HttpLoggingInterceptor.Logger logger, HttpLoggingInterceptor.Level level, List<Interceptor> interceptorList, List<Interceptor> networkInterceptorList, List<CallAdapter.Factory> callAdapterFactoryList, List<Converter.Factory> converterFactoryList, HostnameVerifier hostnameVerifier, SSLSocketFactory sslSocketFactory, X509TrustManager x509TrustManager, Cache cache) {
        mBaseUrl = baseUrl;
        mConnectTimeout = connectTimeout;
        mReadTimeout = readTimeout;
        mWriteTimeout = writeTimeout;
        mLogger = logger;
        mLevel = level;
        mInterceptorList = interceptorList;
        mNetworkInterceptorList = networkInterceptorList;
        mCallAdapterFactoryList = callAdapterFactoryList;
        mConverterFactoryList = converterFactoryList;
        mHostnameVerifier = hostnameVerifier;
        mSSLSocketFactory = sslSocketFactory;
        mX509TrustManager = x509TrustManager;
        mCache = cache;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public Cache getCache() {
        return mCache;
    }

    public List<CallAdapter.Factory> getCallAdapterFactoryList() {
        return mCallAdapterFactoryList;
    }

    public HostnameVerifier getHostnameVerifier() {
        return mHostnameVerifier;
    }

    public List<Interceptor> getInterceptorList() {
        return mInterceptorList;
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return mSSLSocketFactory;
    }

    public X509TrustManager getX509TrustManager() {
        return mX509TrustManager;
    }

    public List<Interceptor> getNetworkInterceptorList() {
        return mNetworkInterceptorList;
    }

    public List<Converter.Factory> getConverterFactoryList() {
        return mConverterFactoryList;
    }

    public long getConnectTimeout() {
        return mConnectTimeout;
    }

    public long getReadTimeout() {
        return mReadTimeout;
    }

    public long getWriteTimeout() {
        return mWriteTimeout;
    }

    public HttpLoggingInterceptor.Level getLevel() {
        return mLevel;
    }

    public HttpLoggingInterceptor.Logger getLogger() {
        return mLogger;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public static final class Builder {

        private HttpLoggingInterceptor.Logger mLogger = new DefaultHttpLogger();
        private HttpLoggingInterceptor.Level mLevel = HttpLoggingInterceptor.Level.BODY;
        private String mBaseUrl;
        private long mConnectTimeout = 60000;
        private long mReadTimeout = 30000;
        private long mWriteTimeout = 30000;
        private List<Interceptor> mInterceptorList = new ArrayList<>();
        private List<Interceptor> mNetworkInterceptorList = new ArrayList<>();
        private List<CallAdapter.Factory> mCallAdapterFactoryList = new ArrayList<>();
        private List<Converter.Factory> mConverterFactoryList = new ArrayList<>();
        private HostnameVerifier mHostnameVerifier = OkHostnameVerifier.INSTANCE;
        private SSLSocketFactory mSSLSocketFactory;
        private X509TrustManager mX509TrustManager;
        private Cache mCache;

        public Builder() {
        }

        public Builder(HttpConfig config) {
            mLogger = config.getLogger();
            mLevel = config.getLevel();
            mBaseUrl = config.getBaseUrl();
            mConnectTimeout = config.getConnectTimeout();
            mReadTimeout = config.getReadTimeout();
            mWriteTimeout = config.getWriteTimeout();
            mInterceptorList.addAll(config.getInterceptorList());
            mNetworkInterceptorList.addAll(config.getNetworkInterceptorList());
            mCallAdapterFactoryList.addAll(config.getCallAdapterFactoryList());
            mConverterFactoryList.addAll(config.getConverterFactoryList());
            mHostnameVerifier = config.getHostnameVerifier();
            mSSLSocketFactory = config.getSSLSocketFactory();
            mX509TrustManager = config.getX509TrustManager();
            mCache = config.getCache();
        }

        public String getBaseUrl() {
            return mBaseUrl;
        }

        public Builder setBaseUrl(String baseUrl) {
            mBaseUrl = baseUrl;
            return this;
        }

        public Cache getCache() {
            return mCache;
        }

        public Builder setCache(Cache cache) {
            mCache = cache;
            return this;
        }

        public X509TrustManager getX509TrustManager() {
            return mX509TrustManager;
        }

        public SSLSocketFactory getSSLSocketFactory() {
            return mSSLSocketFactory;
        }

        public HostnameVerifier getHostnameVerifier() {
            return mHostnameVerifier;
        }

        public Builder setHostnameVerifier(HostnameVerifier hostnameVerifier) {
            mHostnameVerifier = hostnameVerifier;
            return this;
        }

        public List<CallAdapter.Factory> getCallAdapterFactoryList() {
            return mCallAdapterFactoryList;
        }

        public HttpLoggingInterceptor.Level getLevel() {
            return mLevel;
        }

        public Builder setLevel(HttpLoggingInterceptor.Level level) {
            mLevel = level;
            return this;
        }

        public Builder sslSocketFactory(SSLSocketFactory sslSocketFactory, X509TrustManager x509TrustManager) {
            mSSLSocketFactory = sslSocketFactory;
            mX509TrustManager = x509TrustManager;
            return this;
        }

        public HttpLoggingInterceptor.Logger getLogger() {
            return mLogger;
        }

        public Builder setLogger(HttpLoggingInterceptor.Logger logger) {
            mLogger = logger;
            return this;
        }

        public List<Interceptor> getNetworkInterceptorList() {
            return mNetworkInterceptorList;
        }

        public long getConnectTimeout() {
            return mConnectTimeout;
        }

        public Builder setConnectTimeout(long connectTimeout) {
            mConnectTimeout = connectTimeout;
            return this;
        }

        public long getReadTimeout() {
            return mReadTimeout;
        }

        public Builder setReadTimeout(long readTimeout) {
            mReadTimeout = readTimeout;
            return this;
        }

        public long getWriteTimeout() {
            return mWriteTimeout;
        }

        public Builder setWriteTimeout(long writeTimeout) {
            mWriteTimeout = writeTimeout;
            return this;
        }

        public Builder addConverterFactory(Converter.Factory factory) {
            mConverterFactoryList.add(factory);
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            mInterceptorList.add(interceptor);
            return this;
        }

        public Builder addCallAdapterFactory(CallAdapter.Factory factory) {
            mCallAdapterFactoryList.add(factory);
            return this;
        }

        public Builder addNetworkInterceptor(Interceptor interceptor) {
            mInterceptorList.add(interceptor);
            return this;
        }

        public List<Interceptor> getInterceptorList() {
            return mInterceptorList;
        }

        public List<Converter.Factory> getConverterFactoryList() {
            return mConverterFactoryList;
        }

        public HttpConfig build() {
            return new HttpConfig(
                    mBaseUrl,
                    mConnectTimeout,
                    mReadTimeout,
                    mWriteTimeout,
                    mLogger,
                    mLevel,
                    mInterceptorList,
                    mNetworkInterceptorList,
                    mCallAdapterFactoryList,
                    mConverterFactoryList,
                    mHostnameVerifier,
                    mSSLSocketFactory,
                    mX509TrustManager,
                    mCache
            );
        }
    }
}
