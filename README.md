# HttpModule
http wrap

## 配置HTTP

``` java
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
```

## 管理器

``` java
mHttpManager = new HttpManager()
            .setHttpConfig(httpConfig);
```

## 接口服务

``` java
public interface ApiService {
    @GET("/")
    Call<String> baidu();
}
```

## 请求

``` java
mHttpManager.getService(ApiService.class)
        .baidu()
        .enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                Log.e("http", response.body());
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.e("http", t.getMessage());
            }
        });
```