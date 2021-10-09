package com.base_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.apu.basejava.CoreActivity;

public class MainActivity extends CoreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HelloWorld();
    }
}