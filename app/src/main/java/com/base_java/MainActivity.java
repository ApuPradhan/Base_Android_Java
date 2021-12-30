package com.base_java;

import android.os.Bundle;

import com.apu.basejava.Supportive.CustomToolBar.ToolbarType;
import com.base_java.Base.BaseActivity;
import com.base_java.Base.Enum.ActivityType;
import com.base_java.Base.Model.ActivityModel;

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