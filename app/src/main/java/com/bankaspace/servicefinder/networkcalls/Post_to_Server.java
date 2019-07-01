package com.bankaspace.servicefinder.networkcalls;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class Post_to_Server {
    Context context;
    Map<String,String> params;
    public Post_to_Server(Context context, Map<String,String> params)
    {this.context = context; this.params = params;}

    public void getJson(String url, final HTTP_Post_Callback callback) {
        Log.d("ASSAMESE_", url);
        StringRequest request = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String object) {
                        callback.onSuccess(object);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            callback.onError(error);
                            Log.d("ASSAMESE", error.getMessage());
                            Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show();
                        } catch (Exception ex){
                            Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                ){
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setTag(NetRequest.TAG);
        NetRequest.getInstance(context).addToRequestQueue(request);
    }

}
