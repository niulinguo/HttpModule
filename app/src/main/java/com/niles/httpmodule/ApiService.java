package com.niles.httpmodule;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Niles
 * Date 2018/10/9 16:08
 * Email niulinguo@163.com
 */
public interface ApiService {

    @GET("/index")
    Call<String> baidu();
}
