package com.bankaspace.servicefinder.networkcalls;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;


public class JSONProvider{
    Context context;
    public JSONProvider(Context context){this.context = context;}


    public void getJson(String url, final HTTP_Get calback){
        Log.d("ASSAMESE", url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        calback.onSuccess(response);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            calback.onError(error);
                            Log.d("ASSAMESE", error.getMessage());
                            Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show();
                        } catch (Exception ex){
                            Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );

        request.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setTag(NetRequest.TAG);
        NetRequest.getInstance(context).addToRequestQueue(request);
    }












}
