package com.apu.basejava.Supportive.CustomToolBar;

import android.graphics.Color;

import com.apu.basejava.R;

import lombok.Data;

@Data
public class ToolBarModel {
    ToolbarType toolbarType = ToolbarType.DefaultToolbar;
    String title = null;
    int logo = R.drawable.ic_android_24dp;
    int backButtonColor = Color.BLACK;
    int toolBarColor = Color.WHITE;
    int textTitleColor = Color.BLUE;
}
