package com.base_java.Core;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.apu.basejava.CoreActivity;
import com.apu.basejava.Helper.CoreActivityHelper;
import com.apu.basejava.Model.CoreModel;
import com.apu.basejava.Supportive.CustomToolBar.ToolBarModel;
import com.base_java.Core.Enum.ActivityType;
import com.base_java.Core.Model.ActivityModel;

public abstract class BaseActivity extends CoreActivity {

    public final ActivityModel activityModel = new ActivityModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolBarSetup((ActivityModel) getActivityModel());
    }

    public void toolBarSetup(ActivityModel activityDataModel) {
        if (activityDataModel.getToolBarModel().getTitle() == null) {
            activityDataModel.getToolBarModel().setTitle(getActivityName());
            activityModel.setToolBarModel(activityDataModel.getToolBarModel());
        }

        CoreActivityHelper.ToolbarSetup(getThis(), activityDataModel.getToolBarModel());
    }

    public ActivityType getActivityType() {
        return ((ActivityModel) getActivityModel()).getActivityType();
    }

    public String getActivityName() {
        return getActivityType().name().replace("_", " ");
    }
}
