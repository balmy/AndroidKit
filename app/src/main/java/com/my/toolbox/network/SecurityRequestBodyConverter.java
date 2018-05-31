package com.my.toolbox.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author Create by yc.li09 on 2018/5/31.
 */
public class SecurityRequestBodyConverter<T> implements Converter<T, RequestBody> {

//    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private Converter<ResponseBody, T> gsonConverter;
//    public SecurityRequestBodyConverter(Converter<ResponseBody, T> responseBodyConverter) {
//        this.gsonConverter = responseBodyConverter;
//    }
//
//    @Override
//    public T convert(ResponseBody value) throws IOException {
////        String rawString = value.string();
////        value.close();
////        String decryptStr = decryptor.decrypt(rawString);
////        ResponseBody decryptBody = ResponseBody.create(MEDIA_TYPE,decryptStr);
//        return gsonConverter.convert(value);
//    }

    private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private Gson gson;
    private TypeAdapter<T> adapter;

    public SecurityRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }
//
//    @Override
//    public T convert(ResponseBody value) throws IOException {
////        return null;
////        String postBody = gson.toJson(encrypt(value.toString()));
////        return RequestBody.create(MEDIA_TYPE,   postBody);
//        return gsonConverter.convert(value);
////        return value;
//    }

    @Override
    public RequestBody convert(T value) throws IOException {
        Log.d("lyc", "SecurityRequestBodyConverter ");
        return RequestBody.create(MEDIA_TYPE,  (String) value);
    }
}
