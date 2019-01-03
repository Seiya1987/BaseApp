package com.seiya.net.callback;

public interface UCallback {
    void onProgress(long currentLength, long totalLength, float percent);

    void onFail(int errCode, String errMsg);
}
