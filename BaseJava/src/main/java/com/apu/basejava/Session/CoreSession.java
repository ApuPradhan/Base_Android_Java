package com.apu.basejava.Session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.apu.basejava.CoreUtility;


public class CoreSession {

    public SharedPreferences prefs;
    protected final String ApplicationKey = "ApplicationKey";

    public CoreSession(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    /*Start Application Model*/
    public void setApplicationModel(ApplicationModel applicationModel) {
        prefs.edit().putString(ApplicationKey, CoreUtility.SerilizeObject(applicationModel)).apply();
    }

    public ApplicationModel getApplicationModel() {
        String applicationModel = prefs.getString(ApplicationKey, null);
        if (CoreUtility.isEmptyOrNull(applicationModel)) {
            return null;
        }
        return CoreUtility.DeserilizeObject(applicationModel, ApplicationModel.class);
    }
    /*End Application Model*/

    /*public void setReferrer(Referrer data) {
        prefs.edit().putString(ReferrerKey, BaseUtility.SerilizeObject(data)).apply();
    }

    public Referrer getReferrer() {
        String data = prefs.getString(ReferrerKey, null);
        return (Referrer) BaseUtility.DeserilizeObject(data, Referrer.class);
    }*/

    /*public void setAppVersion(AppVersionResponseModel data) {
        prefs.edit().putString(AppVersionKey, CUUtility.SerilizeObject(data)).commit();
    }

    public AppVersionResponseModel getAppVersion() {
        String data = prefs.getString(AppVersionKey,null);
        return (AppVersionResponseModel) CUUtility.DeserilizeObject(data,AppVersionResponseModel.class);
    }*/

    /*public static void ClearSession(Context cntx){}{
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
        SharedPreferences.Editor editor = prefs.edit();
    }*/
}
