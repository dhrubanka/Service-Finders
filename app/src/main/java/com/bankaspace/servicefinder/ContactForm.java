package com.bankaspace.servicefinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bankaspace.servicefinder.networkcalls.HTTP_Post_Callback;
import com.bankaspace.servicefinder.networkcalls.Post_to_Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContactForm extends AppCompatActivity {
    int UID;
    EditText nameClient,emailClient,phoneClient,messageClient;

    private static final String URL_CF = "http://babydchere.96.lt/contactform.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);
        try {
            UID = getIntent().getExtras().getInt("userid");

        } catch (NullPointerException e ) {

            Toast.makeText(ContactForm.this, "STFU", Toast.LENGTH_SHORT).show();
        }
        nameClient=findViewById(R.id.nameClient);
        emailClient=findViewById(R.id.emailClient);
        phoneClient=findViewById(R.id.phoneClient);
        messageClient=findViewById(R.id.messageClient);
    }


    public void submitform(View view){
        work();


    }


    String mName,mEmail,mPhone,mMessage;
    private void setvalues() {
        //first getting the values
        mName = nameClient.getText().toString().trim();
        mEmail = emailClient.getText().toString().trim();
        mPhone=phoneClient.getText().toString().trim();
        mMessage=messageClient.getText().toString().trim();
    }

    private boolean checkEmpty(){
        boolean ok = true;
        //validating inputs
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            emailClient.setError("Enter your Email");
            emailClient.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(mName)) {
            nameClient.setError("Please enter your Name ");
            nameClient.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(mPhone)) {
            phoneClient.setError("Please enter your Phone");
            phoneClient.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(mMessage)) {
            messageClient.setError("Please enter your Message");
            messageClient.requestFocus();
            return false;
        }

        return ok;

    }

    public void work(){
        setvalues();
        if(checkEmpty()==false)
        {return;}
        //diaglog progress
        final ProgressDialog dialog= new ProgressDialog(this);
        dialog.setMessage("Logging ");
        dialog.show();

        Map<String, String> params = new HashMap<>();
        params.put("name",mName);
        params.put("email", mEmail);
        params.put("phone", mPhone);
        params.put("message",mMessage);
        params.put("userid",String.valueOf(UID));




        new Post_to_Server(this, params).getJson(URL_CF, new HTTP_Post_Callback() {
            @Override
            public void onSuccess(String string) {
                try {
                    JSONObject job = new JSONObject(string);
                    int SUCCESS = job.getInt("SUCCESS");
                    if (SUCCESS == 1) {
                        dialog.dismiss();
                        Toast.makeText(ContactForm.this, "Submitted", Toast.LENGTH_SHORT).show();
                        Intent io=new Intent(ContactForm.this,MainActivity.class);

                        io.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(io);

                    } else {
                            dialog.dismiss();
                            Toast.makeText(ContactForm.this, "Not Submitted", Toast.LENGTH_SHORT).show();
                        }

                }

                catch (JSONException ex){
                    dialog.cancel();
                    Toast.makeText(ContactForm.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        });

    }
}
