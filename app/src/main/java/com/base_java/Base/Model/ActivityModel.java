package com.base_java.Base.Model;

import com.apu.basejava.Model.CoreModel;
import com.base_java.Base.Enum.ActivityType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityModel extends CoreModel {
    ActivityType activityType;
    ActivityType previousActivity;
    Object pushModel;
}
