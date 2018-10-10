package com.niles.httpmodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.niles.http.HttpConfig;
import com.niles.http.HttpManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpConfig httpConfig = new HttpConfig.Builder()
                .setBaseUrl("http://www.baidu.com")
                .build();

        HttpManager.setHttpConfig(httpConfig);
        ApiService apiService = HttpManager.createService(ApiService.class);
    }
}
