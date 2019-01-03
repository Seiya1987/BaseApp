package com.seiya.net.subscriber;

import com.seiya.net.callback.ACallback;

/**
 * @Description: 包含下载进度回调的订阅者
 */
public class DownCallbackSubscriber<T> extends ApiCallbackSubscriber<T> {
    public DownCallbackSubscriber(ACallback<T> callBack) {
        super(callBack);
    }

    @Override
    public void onComplete() {
        super.onComplete();
        callBack.onSuccess(super.data);
    }
}
