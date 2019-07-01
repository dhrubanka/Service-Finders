package com.bankaspace.servicefinder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bankaspace.servicefinder.networkcalls.HTTP_Post_Callback;
import com.bankaspace.servicefinder.networkcalls.Post_to_Server;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailProfile extends AppCompatActivity {
    TextView userid,usernme,emailid,gendertype,categorytype,fullname,personalno,pricerange,businessdes,whatsapp,localitty,addresss;
    ImageView picpath;
    int UID;
    Context mContext;
    String ROOT_URL= "http://babydchere.96.lt/querry.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profile);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        try {
            UID = getIntent().getExtras().getInt("userid");

        } catch (NullPointerException e ) {

            Toast.makeText(DetailProfile.this, "STF", Toast.LENGTH_SHORT).show();
        }

        userid=findViewById(R.id.userid);
        usernme=findViewById(R.id.username);
        emailid=findViewById(R.id.emailid);
        gendertype=findViewById(R.id.gender);
        categorytype=findViewById(R.id.categorytype);
        fullname=findViewById(R.id.fullname);
        personalno=findViewById(R.id.mobileNumber);
        pricerange=findViewById(R.id.pricing);
        businessdes=findViewById(R.id.businessdescription);
        whatsapp=findViewById(R.id.whatsapp);
        localitty=findViewById(R.id.localitty);
        addresss=findViewById(R.id.addresss);
        picpath=findViewById(R.id.profilepicture);
        query();
    }

    public void query(){
        Map<String, String> params = new HashMap<>();
        final ProgressDialog dialog= new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();


        params.put("userid",String.valueOf(UID));


        new Post_to_Server(this, params).getJson(ROOT_URL, new HTTP_Post_Callback() {
            @Override
            public void onSuccess(String string) {
                try {
                    JSONObject job = new JSONObject(string);
                    int SUCCESS = job.getInt("SUCCESS");

                    if (SUCCESS == 1) {
                            dialog.dismiss();
                            JSONObject userJson = job.getJSONObject("user");
                            User user = new User(
                                    userJson.getString("userid"),
                                    userJson.getString("username"),
                                    userJson.getString("email"),
                                    userJson.getString("gender"),
                                    userJson.getString("category"),
                                    userJson.getString("fullname"),
                                    userJson.getString("personalno"),
                                    userJson.getString("pricing"),
                                    userJson.getString("description"),
                                    userJson.getString("whatsapp"),
                                    userJson.getString("picpath"),
                                    userJson.getString("locality"),
                                    userJson.getString("address")
                            );


                            userid.setText(user.getId());
                            usernme.setText(user.getUsername());
                            emailid.setText(user.getEmail());
                            gendertype.setText(user.getGender());
                            categorytype.setText(user.getCategory());
                            fullname.setText(user.getName());
                            personalno.setText(user.getPersonalno());
                            pricerange.setText(user.getPricerange());
                            businessdes.setText(user.getBusinessdes());
                            whatsapp.setText(user.getWhatsapp());
                            localitty.setText(user.getLocality());
                            addresss.setText(user.getAddress());

                            int loader = R.drawable.man;

                            String image_url =(String) user.getPicpath();
                            Glide.with(getApplicationContext()).load(image_url).into(picpath);



                       } else {
                            dialog.dismiss();
                            Toast.makeText(DetailProfile.this, "User not register", Toast.LENGTH_SHORT).show();
                        }

                }

                catch (JSONException ex){
                    dialog.cancel();
                    Toast.makeText(DetailProfile.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        });



    }
    public void reachme(View v)
    {
        Intent i=new Intent(DetailProfile.this, ContactForm.class);
        i.putExtra("userid",UID);
        startActivity(i);
    }
}
