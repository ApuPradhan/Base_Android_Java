package com.apu.basejava;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.apu.basejava.Supportive.CustomToolBar.ToolBarModel;

abstract public class CoreActivity extends AppCompatActivity implements com.apu.basejava.Interface.CoreActivity {

    protected ToolBarModel toolBarModel = new ToolBarModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected CoreActivity getThis() {
        return CoreActivity.this;
    }
}
