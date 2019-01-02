package com.seiya.baseapp.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class HookActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //hook Activity 的 startActivity instrumentation
    public static void replaceInstrumentation(Activity activity) throws Exception{
            Class<?> tmp = Activity.class;

            Field field = tmp.getDeclaredField("mInstrumentation");
            field.setAccessible(true);
            Instrumentation instrumentation = (Instrumentation) field.get(activity);

            Instrumentation instrumentationProxy = new ActivityProxyInstrumentation(instrumentation);
            field.set(activity, instrumentationProxy);

    }

    //hook getApplicationContext instrumentation
    public static void attachContext() throws Exception{
        //获取ActivityThread
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
        currentActivityThreadMethod.setAccessible(true);
        Object currentActivityThread = currentActivityThreadMethod.invoke(null);

        //拿到instrumentation
        Field mInstrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
        mInstrumentationField.setAccessible(true);
        Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(currentActivityThread);

        Instrumentation evilInstrumentation = new ApplicationInstrumentation(mInstrumentation);

        mInstrumentationField.set(currentActivityThread, evilInstrumentation);

    }

}
