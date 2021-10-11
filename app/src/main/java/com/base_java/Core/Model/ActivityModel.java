package com.base_java.Core.Model;

import com.apu.basejava.Model.CoreModel;
import com.base_java.Core.Enum.ActivityType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityModel extends CoreModel {
    ActivityType activityType;
}
