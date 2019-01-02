package com.seiya.baseapp.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Build;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HookUtils {
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

    //hook AMS version > 26
    public static void hookAMSAfter26() throws Exception{
        Class<?> aClass = Class.forName("android.app.ActivityManager");
        Field declaredField = aClass.getDeclaredField("IActivityManagerSingleton");
        declaredField.setAccessible(true);
        Object value = declaredField.get(null);

        Class<?> singletionClz = Class.forName("android.util.Singleton");
        Field instanceField = singletionClz.getDeclaredField("mInstance");
        instanceField.setAccessible(true);
        Object iActivityManagerObject = instanceField.get(value);

        Class<?> iActivity = Class.forName("android.app.IActivityManager");
        InvocationHandler handler = new AMSInvocationHandler(iActivityManagerObject);
        Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class<?>[]{iActivity}, handler);

        instanceField.set(value, proxy);

    }

    //hook AMS <= 26
    public static void hookAmsBefore26() throws Exception {
        // 第一步：获取 IActivityManagerSingleton
        Class<?> forName = Class.forName("android.app.ActivityManagerNative");
        Field defaultField = forName.getDeclaredField("gDefault");
        defaultField.setAccessible(true);
        Object defaultValue = defaultField.get(null);

        Class<?> forName2 = Class.forName("android.util.Singleton");
        Field instanceField = forName2.getDeclaredField("mInstance");
        instanceField.setAccessible(true);
        Object iActivityManagerObject = instanceField.get(defaultValue);

        // 第二步：获取我们的代理对象，这里因为 IActivityManager 是接口，我们使用动态代理的方式
        Class<?> iActivity = Class.forName("android.app.IActivityManager");
        InvocationHandler handler = new AMSInvocationHandler(iActivityManagerObject);
        Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[]{iActivity}, handler);

        // 第三步：偷梁换柱，将我们的 proxy 替换原来的对象
        instanceField.set(defaultValue, proxy);
    }

    public static void hookAMS() throws Exception{
        if(Build.VERSION.SDK_INT > 26){
            hookAMSAfter26();
        }else{
            hookAmsBefore26();
        }
    }


}
