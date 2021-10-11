package com.base_java;

import android.os.Bundle;

import com.apu.basejava.Supportive.CustomToolBar.ToolbarType;
import com.base_java.Core.BaseActivity;
import com.base_java.Core.Enum.ActivityType;
import com.base_java.Core.Model.ActivityModel;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public ActivityModel getActivityModel() {
        toolBarModel.setTitle("Base Project");
        toolBarModel.setToolbarType(ToolbarType.Title);

        activityModel.setActivityType(ActivityType.Main);
        activityModel.setToolBarModel(toolBarModel);
        return activityModel;
    }

}