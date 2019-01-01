package com.seiya.base.app;

import android.app.Activity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.orhanobut.logger.Logger;


@Route(path = "/test/hello")
public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("Hello TestActivity");
    }
}
