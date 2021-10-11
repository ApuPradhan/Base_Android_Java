package com.apu.basejava;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.appcompat.app.AppCompatActivity;

public class CoreHelper {

    public static Activity getActivity(Context context) {
        if (context == null) {
            return null;
        } else if (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            } else {
                return getActivity(((ContextWrapper) context).getBaseContext());
            }
        }
        return null;
    }

    public static AppCompatActivity getAppCompatActivityActivity(Activity activity) {
        if (activity == null) {
            return null;
        } else if (activity instanceof AppCompatActivity) {
            return (AppCompatActivity) activity;
        } else {
            return getAppCompatActivityActivity((Activity) activity.getBaseContext());
        }
    }

}
