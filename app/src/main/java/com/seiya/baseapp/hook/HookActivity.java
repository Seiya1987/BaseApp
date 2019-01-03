package com.seiya.baseapp.hook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.seiya.baseapp.MainActivity;

public class HookActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try{
            HookUtils.hookAMS();
            Intent intent = new Intent(HookActivity.this, MainActivity.class);
            startActivity(intent);

        }catch (Exception e){
            Log.e("HookActivity", e.toString());
        }
    }



}
