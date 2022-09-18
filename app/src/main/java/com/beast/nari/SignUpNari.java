package com.beast.nari;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.beast.nari.databinding.ActivitySignUpNariBinding;

import java.util.HashMap;
import java.util.Map;

public class SignUpNari extends AppCompatActivity {

    private ActivitySignUpNariBinding binding;

    String fullName, userName, phoneNumber, eMail, password, job;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    String url = "https://biochemical-damping.000webhostapp.com/nari/signupdemo.php";

    String signStatus;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_nari);

        progressDialog = new ProgressDialog(this);

        sp = getSharedPreferences("DATA_NAME", MODE_PRIVATE);
        editor = sp.edit();

        signStatus = sp.getString("SIGN_UP_STATUS", null);

        if (signStatus != null){
            startActivity(new Intent(getApplicationContext(), NariDashboard.class));
            finish();
        }

        binding.narisignup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(SignUpNari.this,NariDashboard.class));

                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                getAllData();

            }
        });

        binding.narilogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpNari.this,nari_login.class));
            }
        });
    }

    private void getAllData() {

        fullName = binding.fullName.getText().toString();
        userName = binding.userName.getText().toString();
        phoneNumber = binding.phoneNumber.getText().toString();
        eMail = binding.eMail.getText().toString();
        password = binding.password.getText().toString();

        if (fullName.length() < 5 ){
            progressDialog.dismiss();
            Toast.makeText(this, "this fullname cannot be accepted", Toast.LENGTH_SHORT).show();
        }else if (userName.length() < 5){
            progressDialog.dismiss();
            Toast.makeText(this, "please enter atleast 5 characters in username", Toast.LENGTH_SHORT).show();
        }else if (userName.contains(" ")){
            Toast.makeText(this, "spaces are not alowed in username", Toast.LENGTH_SHORT).show();
        } else if (phoneNumber.length() < 9){
            progressDialog.dismiss();
            Toast.makeText(this, "please enter correct mobile number", Toast.LENGTH_SHORT).show();
        }else if (!eMail.contains("@") || !eMail.contains(".com") || eMail.length()<13){
            progressDialog.dismiss();
            Toast.makeText(this, "please reenter e-mail", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6){
            progressDialog.dismiss();
            Toast.makeText(this, "password must include 6 characters", Toast.LENGTH_SHORT).show();
        }else {
            saveAllData();
        }

    }

    private void saveAllData() {

        editor.putString("fullName", fullName);
        editor.putString("userName", userName);
        editor.putString("phoneNumber", phoneNumber);
        editor.putString("eMail", eMail);
        editor.putString("password", password);

        editor.apply();

        uploadData();

    }

    private void uploadData() {

        job = sp.getString("JOB", null);

        Toast.makeText(this, job, Toast.LENGTH_SHORT).show();

        if (job != null){

            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.contains("Data Inserted") || response == ("Data Inserted")){


                                editor.putString("SIGN_UP_STATUS", "SIGNIND");
                                editor.apply();

                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), NariDashboard.class));
                                finish();

                            }

                            if (response.contains("Failed")){

                                progressDialog.dismiss();
                                Toast.makeText(SignUpNari.this, "Failed, Maybe username already exist!", Toast.LENGTH_SHORT).show();

                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    progressDialog.dismiss();
                    Toast.makeText(SignUpNari.this, "something went wrong\n" + error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }){

                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> map = new HashMap<>();
                    map.put("name", fullName);
                    map.put("userid", userName);
                    map.put("email", eMail);
                    map.put("number", phoneNumber);
                    map.put("password", password);
                    map.put("profilepic", "0");
                    map.put("relation", job);
                    map.put("friend", "0");
                    map.put("longtitude", "0");
                    map.put("latitude", "0");
                    return map;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(SignUpNari.this);
            requestQueue.add(request);

        }else{

            progressDialog.dismiss();
            Toast.makeText(this, "something went wrong please try to reform", Toast.LENGTH_SHORT).show();
        }

    }

}