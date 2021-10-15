package com.apu.basejava;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.apu.basejava.Api.BaseResponseModel;
import com.apu.basejava.Constants.CoreConstants;
import com.apu.basejava.Enum.FieldCaseType;
import com.apu.basejava.Interface.BaseCallBack;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

public abstract class CoreAPI {
    public static String BaseUrl;

    private static final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
    private static final String errorResponse = CoreUtility.ToJson(new BaseResponseModel(), FieldCaseType.PascalCase).toString();

    protected static <U, T> void ProcessJsonRequest(final Context context, final int Method, final String Url, T rqstModel,
                                                    final BaseCallBack callBack, final Class<U> type) {

        Response.Listener<JSONObject> responseListner = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d(CoreConstants.TAG, "onResponse: " + response);
                    U data = CoreUtility.ToObject(response.toString(), type);
                    Log.d(CoreConstants.TAG, String.valueOf(data));
                    callBack.onComplete(true, data);
                } catch (Exception e) {
                    RepoertErrorInLog("onResponseError: ", e);
                    callBack.onComplete(true, CoreUtility.ToObject(errorResponse, type));
                    Toast.makeText(context, "Json format error", Toast.LENGTH_LONG).show();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(CoreConstants.TAG, "onResponse: " + errorResponse);
                callBack.onComplete(true, gson.fromJson(errorResponse, type));
                //Toast.makeText(context,"Internal server error",Toast.LENGTH_LONG).show();
                VolleyErrorHandler(context, error);
            }
        };

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Method, Url, (JSONObject) rqstModel, responseListner, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                //headers.put("content-type", "application/json");
                //headers.put("accept", "application/json");
                //headers.put("accept-encoding", "gzip, deflate");
                //headers.put("accept-language", "en-US,en;q=0.8");
                //headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");

                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsObjRequest);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<String>() {
            @Override
            public void onRequestFinished(Request<String> request) {
            }
        });
    }

    /*protected static <U, T> void MultiPartRequest(final Context context, final int Method, final String Url, T rqstModel, final BaseCallBack callBack, final Class<U> type) {

        ProgressDialog progress = new ProgressDialog(context);
        progress.setIndeterminate(false);
        progress.setMax(100);
        progress.setMessage("Uploading...");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        String ApiUrl = BaseUrl + Url;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(CoreConstants.TAG, "Url: " + ApiUrl);
                JSONObject object = BaseUtility.ToJson(rqstModel, FieldCaseType.PascalCase);
                Log.d(CoreConstants.TAG, "dataSend: " + object);
            }
        }).start();

        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Method, ApiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d(CoreConstants.TAG, "onResponse: " + response);
                    U data = (U) gson.fromJson(response.toString(), type);
                    Log.d(CoreConstants.TAG, String.valueOf(data));
                    callBack.onComplete(true, data);
                } catch (Exception e) {
                    RepoertErrorInLog("onResponseError: ", e);
                    callBack.onComplete(true, gson.fromJson(errorResponse, type));
                    Toast.makeText(context, "Json format error", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.dismiss();
                Log.d(CoreConstants.TAG, "onResponse: " + errorResponse);
                callBack.onComplete(true, gson.fromJson(errorResponse, type));
                //Toast.makeText(context,"Internal server error",Toast.LENGTH_LONG).show();
                VolleyErrorHandler(context, error);
            }
        });

        smr.setOnProgressListener(new Response.ProgressListener() {
            @Override
            public void onProgress(long transferredBytes, long totalSize) {
                int percentage = (int) ((transferredBytes / ((float) totalSize)) * 100);
                if (progress != null) {
                    progress.setProgress(percentage);
                }
                if (percentage == 100) {
                    progress.dismiss();
                }
            }
        });

        SimpleMultiPartRequest sm = ToFormData(rqstModel, smr);
        //sm.addStringParam("ApiToken", new Session(context).getUser().getApiToken());
        Log.d(CoreConstants.TAG, "dataSend: " + sm.getMultipartParams());

        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
        mRequestQueue.add(sm);
        mRequestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<String>() {
            @Override
            public void onRequestFinished(Request<String> request) {
                //CloseProgressDialog();
            }
        });
    }*/



    /*Below Helper Code which is only use in this section*/

    protected static void RepoertErrorInLog(String stepName, Exception error) {
        if (error == null) {
            return;
        }

        Log.d(CoreConstants.TAG, stepName + error.getMessage());
    }

    protected static void VolleyErrorHandler(Context ctx, VolleyError error) {
        if (error instanceof NoConnectionError) {
            ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = null;
            if (cm != null) {
                activeNetwork = cm.getActiveNetworkInfo();
            }
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                Toast.makeText(ctx, "Server is not connected to internet.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ctx, "Your device is not connected to internet.", Toast.LENGTH_SHORT).show();
            }
        } else if (error instanceof NetworkError || error.getCause() instanceof ConnectException) {
            Toast.makeText(ctx, "Your device is not connected to internet.", Toast.LENGTH_SHORT).show();
        } else if (error.getCause() instanceof MalformedURLException) {
            Toast.makeText(ctx, "Bad Request.", Toast.LENGTH_SHORT).show();
        } else if (error instanceof ParseError || error.getCause() instanceof IllegalStateException
                || error.getCause() instanceof JSONException
                || error.getCause() instanceof XmlPullParserException) {
            Toast.makeText(ctx, "Parse Error (because of invalid json or xml).", Toast.LENGTH_SHORT).show();
        } else if (error.getCause() instanceof OutOfMemoryError) {
            Toast.makeText(ctx, "Out Of Memory Error.", Toast.LENGTH_SHORT).show();
        } else if (error instanceof AuthFailureError) {
            Toast.makeText(ctx, "server couldn't find the authenticated request.", Toast.LENGTH_SHORT).show();
        } else if (error instanceof ServerError || error.getCause() instanceof ServerError) {
            Toast.makeText(ctx, "Server is not responding.", Toast.LENGTH_SHORT).show();
        } else if (error instanceof TimeoutError || error.getCause() instanceof SocketTimeoutException
                || error.getCause() instanceof ConnectTimeoutException
                || error.getCause() instanceof SocketException) {
            Toast.makeText(ctx, "Connection timeout error", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ctx, "An unknown error occurred.", Toast.LENGTH_SHORT).show();
        }
    }

    /*private static <T> SimpleMultiPartRequest ToFormData(T model, SimpleMultiPartRequest requestBody) {
        Field[] fields = model.getClass().getFields();
        for (Field data : fields) {
            try {
                if (data.get(model) != null) {
                    String dataType = data.get(model).getClass().getSimpleName();
                    if (dataType.equals("File") || dataType.equals("HierarchicalUri")) {
                        if (dataType.equals("File")) {
                            File file = (File) data.get(model);
                            requestBody.addFile(BaseUtility.PascalCase(data.getName()), file.getPath());
                        } else {
                            Uri uri = (Uri) data.get(model);
                            requestBody.addFile(BaseUtility.PascalCase(data.getName()), uri.getPath());
                        }

                    } else {
                        requestBody.addStringParam(BaseUtility.PascalCase(data.getName()), data.get(model).toString());
                    }

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return requestBody;
    }*/
}