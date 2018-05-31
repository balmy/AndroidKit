package com.my.toolbox.network;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Create by yc.li09 on 2018/5/31.
 */
public class SecurityConverterFactory extends Converter.Factory {

    private GsonConverterFactory gsonConverterFactory;

    private SecurityConverterFactory() {
        gsonConverterFactory = GsonConverterFactory.create();
    }


    public static SecurityConverterFactory create() {
        return new SecurityConverterFactory();
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        Log.d("lyc", "responseBodyConverter ");
        return new SecurityResponseBodyConverter<>(
                gsonConverterFactory.responseBodyConverter(type, annotations, retrofit));
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {
        Log.d("lyc", "requestBodyConverter ");
//        return gsonConverterFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
        Gson gson = new Gson();
        TypeAdapter<?> adapter = new Gson().getAdapter(TypeToken.get(type));
        return new SecurityRequestBodyConverter<>(gson, adapter);
    }

    @Nullable
    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations,
                                                Retrofit retrofit) {
        return gsonConverterFactory.stringConverter(type, annotations, retrofit);
    }
}
