package com.seiya.baseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.seiya.baseapp.hook.HookActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ARouter.getInstance().build("/test/hello").navigation();

        Intent intent = new Intent(MainActivity.this, HookActivity.class);
        startActivity(intent);

    }
}
