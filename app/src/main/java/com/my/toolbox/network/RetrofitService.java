package com.my.toolbox.network;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author Create by yc.li09 on 2018/5/29.
 */
public interface RetrofitService {
//    String BASE_URL = "https://jsonplaceholder.typicode.com/";
//
//    @GET("posts/1")
//    Observable<BaseResponse<TestBean>> requestTest();

    String BASE_URL = "http://192.168.18.8:3000/";

    @GET("test")
    Observable<BaseResponse<TestBean>> requestTest();
}
