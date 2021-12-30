package com.base_java.Base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.apu.basejava.CoreActivity;
import com.apu.basejava.Helper.CoreActivityHelper;
import com.base_java.Base.Enum.ActivityType;
import com.base_java.Base.Model.ActivityModel;

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
