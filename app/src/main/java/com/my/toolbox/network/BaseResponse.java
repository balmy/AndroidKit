package com.my.toolbox.network;

/**
 * @author Create by yc.li09 on 2018/5/29.
 */
public class BaseResponse<T> {
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 0 成功  1 失败
     */
    private int status;


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
