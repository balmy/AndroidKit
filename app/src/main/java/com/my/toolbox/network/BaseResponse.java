package com.my.toolbox.network;

/**
 * @author Create by yc.li09 on 2018/5/29.
 */
public class BaseResponse<T> {
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    private String msg;
    private T content;
}
