package com.apu.basejava.Helper;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.apu.basejava.Supportive.CustomToolBar.CustomToolBar;
import com.apu.basejava.Supportive.CustomToolBar.ToolBarModel;

public class CoreActivityHelper {

    public static void ToolbarSetup(Activity ctx, ToolBarModel model) {
        new CustomToolBar(ctx).toolBar(model);
    }
}
