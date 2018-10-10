package com.niles.http.converter;

import android.support.annotation.NonNull;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by Niles
 * Date 2018/10/10 11:49
 * Email niulinguo@163.com
 */
public class StringRequestBodyConverter implements Converter<String, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("text/plain; charset=UTF-8");
//    private static final Charset UTF_8 = Charset.forName("UTF-8");

    @Override
    public RequestBody convert(@NonNull String value) {
        return RequestBody.create(MEDIA_TYPE, value);
    }
}
