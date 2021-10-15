package com.apu.basejava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;

import com.apu.basejava.Constants.CoreConstants;
import com.apu.basejava.Enum.FieldCaseType;
import com.apu.basejava.Helper.DateDeserializer;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class CoreUtility {

    public static String checkAndGetValidPhoneNumber(String mobileNumber) {
        if (CoreUtility.isEmptyOrNull(mobileNumber) || CoreUtility.getCleanNumber(mobileNumber).length() != CoreConstants.PhoneNumberLength) {
            return null;
        }
        return mobileNumber;
    }

    public static boolean isValidPhoneNumber(String mobileNumber) {
        if (CoreUtility.isEmptyOrNull(mobileNumber) || CoreUtility.getCleanNumber(mobileNumber).length() != CoreConstants.PhoneNumberLength) {
            return false;
        }
        return true;
    }

    public static String getCleanNumber(String number) {
        String cleanNo = number.replaceAll("[\\D]", "");
        return getLast10Character(cleanNo);
    }

    public static String getLast10Character(String str) {
        if (str.length() == 10) {
            return str;
        } else if (str.length() > 10) {
            return str.substring(str.length() - 10);
        }
        return str;
    }

    public static boolean isEmptyOrNull(String data) {
        if (data == null) {
            return true;
        }
        if (data.isEmpty() || data.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public static String SerilizeObject(Object myObject) {
        // serialize the object
        try {
            Gson gson = new Gson();
            String json = gson.toJson(myObject);
            return json;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static <U> U DeserilizeObject(String serializedObject, Class<U> type) {
        // serialize the object
        try {
            if (serializedObject == null || serializedObject.isEmpty()) {
                return null;
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            U data = gson.fromJson(serializedObject, type);
            return data;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /*Start API Protocol Encode*/
    public static String ApiProtocolEncode(String str) {
        byte[] data = str.getBytes(StandardCharsets.UTF_8);
        return Base64.encodeToString(data, Base64.DEFAULT);
    }

    public static String ApiProtocolDecode(Context ctx, int code) {
        byte[] data = Base64.decode(ctx.getResources().getString(code), Base64.DEFAULT);
        return new String(data, StandardCharsets.UTF_8);
    }
    /*End API Protocol Encode*/

    public static <T> JSONObject ToJson(T model, FieldCaseType caseType) {
        Field[] fields = model.getClass().getFields();
        JSONObject obj = new JSONObject();
        for (Field data : fields) {
            data.setAccessible(true);
            try {
                switch (caseType) {
                    case PascalCase:
                        obj.put(PascalCase(data.getName()), data.get(model));
                        break;
                    case CamelCase:
                        obj.put(data.getName(), data.get(model));
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public static String PascalCase(String value) {
        String data = value.substring(0, 1);
        return value.replaceFirst(data, data.toUpperCase());
    }

    public static <U> U ToObject(String json, Class<U> type) {
        U data = null;
        try {
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).registerTypeAdapter(Date.class, new DateDeserializer()).create();
            data = gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static Bundle setExtraParameter(Bundle mBundle, Object obj, String Key) {
        String value = CoreUtility.SerilizeObject(obj);
        if (mBundle == null) {
            mBundle = new Bundle();
        }
        mBundle.putString(Key, value);
        return mBundle;
    }

    public static Intent setExtraParameter(Intent mIntent, Object obj, String Key) {
        Bundle mBundle = mIntent.getExtras();
        mBundle = setExtraParameter(mBundle, obj, Key);
        mIntent.putExtras(mBundle);
        return mIntent;
    }

    public static <U> U getExtraParameter(Bundle mBundle, String Key, Class<U> type) {

        if (mBundle == null) {
            return null;
        }

        String value = mBundle.getString(Key, null);
        if (value == null) {
            return null;
        }

        return CoreUtility.DeserilizeObject(value, type);
    }

    public static <U> U getExtraParameter(Intent mIntent, String Key, Class<U> type) {
        if (mIntent == null) {
            return null;
        }
        return getExtraParameter(mIntent.getExtras(), Key, type);
    }

}