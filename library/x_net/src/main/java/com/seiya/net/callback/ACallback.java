package com.seiya.net.callback;


public abstract class ACallback<T> {
    public abstract void onSuccess(T data);

    public abstract void onFail(int errCode, String errMsg);
}
