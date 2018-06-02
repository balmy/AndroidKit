package com.my.toolbox.network;

import android.content.Context;

import com.my.base.ui.BaseActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author Create by yc.li09 on 2018/5/29.
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    private Context context;

    public BaseObserver(Context ctx){
        this.context = ctx;
    }


    @Override
    public void onSubscribe(Disposable d) {
        requestStart();
    }

    private void requestStart() {
        if (context != null && isShowLoading()) {
            ((BaseActivity)context).showLoading(true);
        }
    }

    private void requestEnd() {
        if (context != null && isShowLoading()) {
            ((BaseActivity)context).showLoading(false);
        }
    }

    @Override
    public void onNext(BaseResponse<T> response) {
       // provided 0 is success
        requestEnd();
        if (response.getStatus() == 0) {
           success(response);
        } else {
           failure(false, response.getMsg());
        }
    }

    @Override
    public void onError(Throwable e) {
        requestEnd();
        failure(true, e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    /**
     * 接口请求成功callback
     * @param response
     */
    protected abstract void success(BaseResponse<T> response);

    /**
     * 接口请求失败callback
     * @param isException 是否为异常错误或业务错误
     * @param msg
     */
    protected abstract void failure(boolean isException, String msg);


    protected boolean isShowLoading() {
        return true;
    }

    protected boolean isShowToast() {
        return true;
    }

}
