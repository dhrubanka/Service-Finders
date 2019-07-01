package com.bankaspace.servicefinder.networkcalls;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface HTTP_Get{
    void onSuccess(JSONObject object);
    void onError(VolleyError error);
}
