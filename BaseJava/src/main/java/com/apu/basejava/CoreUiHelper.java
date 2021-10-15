package com.apu.basejava;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.apu.basejava.Constants.CoreConstants;
import com.apu.basejava.Supportive.CustomToolBar.ToolBarModel;

public class CoreUiHelper {
    /*
    Call,
    */

    public static boolean checkPermission(Context ctx, String val) {
        return ContextCompat.checkSelfPermission(ctx, val) == PackageManager.PERMISSION_GRANTED;
    }

    public static String[] checkPermissions(Context ctx, String[] val) {
        String[] deniedPermissions = new String[val.length];
        for (int i = 0; i < val.length; i++) {
            int result = ContextCompat.checkSelfPermission(ctx, val[i]);
            if (result != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions[i] = val[i];
            }
        }
        return deniedPermissions;
    }

    public static void requestPermissions(Activity ctx, String[] val) {
        ActivityCompat.requestPermissions(ctx, val, CoreConstants.MULTIPLE_PERMISSIONS_RQST);
    }


    public static void requestPermission(Activity ctx, String val, int rqstNo) {
        ActivityCompat.requestPermissions(ctx, new String[]{val}, rqstNo);
    }

    public static void StartActivityForResult(Activity ctx, Object obj, String objPARAM, Class cls, int param) {
        Intent intent = new Intent(ctx, cls);
        if (obj != null) {
            CoreUtility.setExtraParameter(intent, obj, objPARAM);
        }
        ctx.startActivityForResult(intent, param);
    }

    public static void StartActivity(Activity ctx, Object obj, String objPARAM, Class cls) {
        Intent intent = new Intent(ctx, cls);
        if (obj != null) {
            CoreUtility.setExtraParameter(intent, obj, objPARAM);
        }
        ctx.startActivity(intent);
    }

    //Call
    public static void dialCall(Activity ctx, String mobileNumber) {
        if (mobileNumber.trim().length() > 0) {
            String dial = "tel:" + mobileNumber;
            ctx.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        } else {
            Toast.makeText(ctx, "Dial number not valid !!!", Toast.LENGTH_SHORT).show();
        }
    }
}
