package com.apu.basejava;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

abstract public class CoreActivity extends AppCompatActivity {

    protected void HelloWorld(){
        Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show();
    }

}
