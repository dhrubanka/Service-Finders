package com.bankaspace.servicefinder.networkcalls;

import com.android.volley.VolleyError;

import org.json.JSONObject;


public interface HTTP_Post_Callback {
    void onSuccess(String string);
    void onError(VolleyError error);
}
