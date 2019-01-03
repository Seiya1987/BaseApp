package com.seiya.baseapp.hook;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AMSInvocationHandler implements InvocationHandler {

    private static final String TAG = "AMSInvocationHandler";

    Object iamObject;

    public AMSInvocationHandler(Object iamObject){
        this.iamObject = iamObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("startActivity".equals(method.getName())){
            Log.i("AMS", "ready to startActivity");
            for(Object object : args){
                Log.d("AMS", "invoke object" + object.toString());
            }
        }
        return method.invoke(iamObject, args);
    }
}
