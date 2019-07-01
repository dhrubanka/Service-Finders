package com.bankaspace.servicefinder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView doctor, garage, plumber, electrician, tutor;
    boolean doubleBackToExitPressedOnce = false;
    private static final String url = "http://babydchere.96.lt/localityquerry.php";
    List<String> list;// = new ArrayList<String>();
    Spinner spinner;
    Toolbar myToolbar;
    Context ctx;
    ArrayAdapter<String> adapter;
    String location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //bidning views
        setContentView(R.layout.activity_main);
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);



        doctor = (CardView) findViewById(R.id.doctorId);
        garage = (CardView) findViewById(R.id.garageID);
        plumber = (CardView) findViewById(R.id.plumberID);
        electrician = (CardView) findViewById(R.id.electricianID);
        tutor = (CardView) findViewById(R.id.tutorID);

        //setting listeners
        doctor.setOnClickListener(this);
        garage.setOnClickListener(this);
        plumber.setOnClickListener(this);
        electrician.setOnClickListener(this);
        tutor.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.spinner_nav);
        loadProducts();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                location= parent.getItemAtPosition(position).toString(); //this is your selected item
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });




    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.doctorId:
                i = new Intent(this, doctorSearch.class);
                i.putExtra("locality",location);
                startActivity(i);
                break;
            case R.id.garageID:
                i = new Intent(this, garageSearch.class);
                i.putExtra("locality",location);
                startActivity(i);
                break;
            case R.id.plumberID:
                i = new Intent(this, plumberSearch.class);
                i.putExtra("locality",location);
                startActivity(i);
                break;
            case R.id.electricianID:
                i = new Intent(this, electricianSearch.class);
                i.putExtra("locality",location);
                startActivity(i);
                break;
            case R.id.tutorID:
                i = new Intent(this, tutorSearch.class);
                i.putExtra("locality",location);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    private void loadProducts() {
 ;

        ///
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONObject jsonObj = new JSONObject(response);
                            JSONArray array = jsonObj.getJSONArray("records");

                            list = new ArrayList<String>();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject actor = array.getJSONObject(i);
                                String name = actor.getString("locality");
                                list.add(name);
                                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                spinner.setAdapter(adapter);


                                //list.add(array.getJSONObject(i).getString("locality"));
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

}


