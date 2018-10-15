package com.niles.httpmodule;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Niles
 * Date 2018/10/9 16:08
 * Email niulinguo@163.com
 */
public interface ApiService {
    @GET("/")
    Call<String> baidu(@Query("name") String name, @Query("age") int age);
}
