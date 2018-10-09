package com.niles.http;

import okhttp3.logging.HttpLoggingInterceptor;

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

    private HttpConfig(String baseUrl, long connectTimeout, long readTimeout, long writeTimeout, HttpLoggingInterceptor.Logger logger) {
        mBaseUrl = baseUrl;
        mConnectTimeout = connectTimeout;
        mReadTimeout = readTimeout;
        mWriteTimeout = writeTimeout;
        mLogger = logger;
    }

    long getConnectTimeout() {
        return mConnectTimeout;
    }

    long getReadTimeout() {
        return mReadTimeout;
    }

    long getWriteTimeout() {
        return mWriteTimeout;
    }

    HttpLoggingInterceptor.Logger getLogger() {
        return mLogger;
    }

    String getBaseUrl() {
        return mBaseUrl;
    }

    public static final class Builder {

        private HttpLoggingInterceptor.Logger mLogger;
        private String mBaseUrl;
        private long mConnectTimeout = 10000;
        private long mReadTimeout = 10000;
        private long mWriteTimeout = 10000;

        public String getBaseUrl() {
            return mBaseUrl;
        }

        public Builder setBaseUrl(String baseUrl) {
            mBaseUrl = baseUrl;
            return this;
        }

        public HttpLoggingInterceptor.Logger getLogger() {
            return mLogger;
        }

        public Builder setLogger(HttpLoggingInterceptor.Logger logger) {
            mLogger = logger;
            return this;
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

        public HttpConfig build() {
            return new HttpConfig(
                    mBaseUrl,
                    mConnectTimeout,
                    mReadTimeout,
                    mWriteTimeout,
                    mLogger
            );
        }
    }
}
