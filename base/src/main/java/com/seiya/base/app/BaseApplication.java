package com.seiya.base.app;

import android.app.Application;
import android.content.res.Configuration;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseApplication extends Application {

    private List<Class<? extends BaseAppLogic>> logicList = new ArrayList<>();
    private List<BaseAppLogic> logicClassList = new ArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();

        initLogic();
        logicCreate();
    }

    protected  abstract void initLogic();

    protected void registerApplciationLogic(Class<? extends BaseAppLogic> loginClass){
        logicList.add(loginClass);
    }

    private void logicCreate(){
        for(Class<? extends BaseAppLogic> logicClass : logicList){
            try{
                BaseAppLogic appLogic = logicClass.newInstance();
                logicClassList.add(appLogic);
                appLogic.onCreate();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for(BaseAppLogic logic : logicClassList){
            logic.onTerminate();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for(BaseAppLogic logic : logicClassList){
            logic.onLowMemory();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        for(BaseAppLogic logic : logicClassList){
            logic.onTrimMemory(level);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        for(BaseAppLogic logic : logicClassList){
            logic.onConfigurationChanged(newConfig);
        }
    }

}
