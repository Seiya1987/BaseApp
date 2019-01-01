package com.seiya.base.app;

import android.content.res.Configuration;
import android.support.annotation.NonNull;

public class BaseAppLogic {
    protected BaseApplication mApplication;
    public BaseAppLogic(){}
    public void setApplication(@NonNull BaseApplication application){
        this.mApplication = application;
    }

    public void onCreate(){}
    public void onTerminate(){}
    public void onLowMemory(){}
    public void onTrimMemory(int level){}
    public void onConfigurationChanged(Configuration newConfig){}
}
