package com.niles.http.converter;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Niles
 * Date 2018/10/10 11:50
 * Email niulinguo@163.com
 */
public class StringResponseBodyConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(@NonNull ResponseBody value) throws IOException {
        return new String(value.bytes());
    }
}
