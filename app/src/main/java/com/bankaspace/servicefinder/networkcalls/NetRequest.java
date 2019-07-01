package com.bankaspace.servicefinder.networkcalls;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class NetRequest {
    private static NetRequest mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;
    public static String TAG = "NETWORK";

    private NetRequest(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

    }

    public static synchronized NetRequest getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NetRequest(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }




}
