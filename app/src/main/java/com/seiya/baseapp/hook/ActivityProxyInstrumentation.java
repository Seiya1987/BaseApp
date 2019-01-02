package com.seiya.baseapp.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;

public class ActivityProxyInstrumentation extends Instrumentation {
    private static final String TAG = "ActivityProxyInstrumentation";

    Instrumentation mBase;
    public ActivityProxyInstrumentation(Instrumentation base){
        mBase = base;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
                                            Intent intent, int requestCode, Bundle options){
        // Hook之前, 可以输出你想要的!
        Log.d("activity","xxxx: 执行了startActivity, 参数如下: " + "who = [" + who + "], " +
                "contextThread = [" + contextThread + "], token = [" + token + "], " +
                "target = [" + target + "], intent = [" + intent +
                "], requestCode = [" + requestCode + "], options = [" + options + "]");

        try{
            Method execStartActivity = Instrumentation.class.getDeclaredMethod("execStartActivity",
                    Context.class, IBinder.class, IBinder.class, Activity.class,
                    Intent.class, int.class, Bundle.class);

            execStartActivity.setAccessible(true);
            return (ActivityResult) execStartActivity.invoke(mBase, who,
                    contextThread, token, target, intent, requestCode, options);

        }catch (Exception e){

        }


    }

}
