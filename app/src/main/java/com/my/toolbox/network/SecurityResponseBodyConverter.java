package com.my.toolbox.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author Create by yc.li09 on 2018/5/31.
 */
public class SecurityResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private Converter<ResponseBody, T> gsonConverter;
    public SecurityResponseBodyConverter(Converter<ResponseBody, T> responseBodyConverter) {
        this.gsonConverter = responseBodyConverter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
//        String rawString = value.string();
//        value.close();
//        String decryptStr = decryptor.decrypt(rawString);
//        ResponseBody decryptBody = ResponseBody.create(MEDIA_TYPE,decryptStr);
        Log.d("lyc", "SecurityResponseBodyConverter ");
        return gsonConverter.convert(value);
    }
}
