package com.bankaspace.servicefinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class electricianSearch extends AppCompatActivity {

    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
    private static final String URL_PRODUCTS = "http://babydchere.96.lt/electricianSearch.php";

    //a list to store all the products
    List<category> categoryList;

    //the recyclerview
    RecyclerView recyclerView;
    categoryAdapter adapter;
    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_search);        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //initializing the productlist
        categoryList = new ArrayList<>();


        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();
    }

    private void loadProducts() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        ///
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONObject jsonObj = new JSONObject(response);
                            JSONArray array = jsonObj.getJSONArray("records");

                            //traversing through all the object
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject categor = array.getJSONObject(i);

                                category catt = new category(
                                        categor.getInt("userid"),
                                        categor.getString("Full_name"),
                                        categor.getString("Category_type"),
                                        categor.getString("Pricing"),
                                        categor.getString("picpath"),
                                        categor.getString("locality")
                                );

                                //adding the product to product list
                                categoryList.add(catt);
                                adapter = new categoryAdapter(electricianSearch.this, categoryList, new categoryAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(category item) {
                                        userid=item.getUserId();
                                        Intent intent=new Intent(electricianSearch.this, DetailProfile.class);
                                        intent.putExtra("userid",userid);
                                        startActivity(intent);
                                    }
                                });
                                recyclerView.setAdapter(adapter);
                                progressDialog.dismiss();


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    ///

}
