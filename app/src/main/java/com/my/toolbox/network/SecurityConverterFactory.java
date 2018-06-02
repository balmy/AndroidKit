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

/**
 * @author Create by yc.li09 on 2018/5/31.
 */
public class SecurityConverterFactory extends Converter.Factory {

    public static SecurityConverterFactory create() {
        return create(new Gson());
    }

    public static SecurityConverterFactory create(Gson gson) {
        return new SecurityConverterFactory(gson);
    }

    private final Gson gson;

    private SecurityConverterFactory(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }

        this.gson = gson;
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        Log.d("lyc", "responseBodyConverter ");
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new SecurityResponseBodyConverter<>(gson, adapter);
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {
        Log.d("lyc", "requestBodyConverter ");
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new SecurityRequestBodyConverter<>(gson, adapter);
    }
}
