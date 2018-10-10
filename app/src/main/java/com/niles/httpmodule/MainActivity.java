package com.niles.httpmodule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.niles.http.HttpConfig;
import com.niles.http.HttpManager;
import com.niles.http.converter.StringConverterFactory;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private HttpManager mHttpManager = new HttpManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        mHttpManager.setHttpConfig(httpConfig);
        ApiService apiService = mHttpManager.createService(ApiService.class);
        Call<String> baidu = apiService.baidu();
        baidu.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                Log.e("http", response.body());
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.e("http", t.getMessage());
            }
        });
    }
}
