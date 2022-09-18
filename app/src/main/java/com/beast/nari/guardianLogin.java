package com.beast.nari;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.beast.nari.databinding.ActivityGuardianLoginBinding;
import com.beast.nari.databinding.ActivityNariLoginBinding;

import java.util.HashMap;
import java.util.Map;

public class guardianLogin extends AppCompatActivity {

    private ActivityGuardianLoginBinding binding;
    String userName, password;
    String url = "https://biochemical-damping.000webhostapp.com/nari/login.php";
    ProgressDialog progressDialog;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_guardian_login);

        progressDialog = new ProgressDialog(this);
        sp = getSharedPreferences("DATA_NAME", MODE_PRIVATE);
        editor = sp.edit();

        binding.narilogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(nari_login.this,NariDashboard.class));

                progressDialog.setMessage("Please wait...");
                progressDialog.show();
                logIn();
            }
        });

        binding.narisignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(guardianLogin.this,guardianSignup.class));
            }
        });
    }

    private void logIn() {

        userName = binding.userName.getText().toString();
        password = binding.password.getText().toString();

        if (userName.length() < 5){
            progressDialog.dismiss();
            Toast.makeText(this, "please enter valid userID", Toast.LENGTH_SHORT).show();
        }else if (password.length() < 6){
            progressDialog.dismiss();
            Toast.makeText(this, "Enter valid password", Toast.LENGTH_SHORT).show();
        }else {
            checkUserPass();
        }

    }

    private void checkUserPass() {

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.contains("Loged in successfully") || response == ("Loged in successfully")){

                            editor.putString("SIGN_UP_STATUS", "SIGNIND");
                            editor.apply();

                            progressDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), guardianDashboard.class));
                            finish();

                        }

                        if (response.contains("some error occured") || response == ("some error occured")){

                            progressDialog.dismiss();
                            Toast.makeText(guardianLogin.this, "user not found", Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(guardianLogin.this, "something went wrong\n" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();

                map.put("userid", userName);
                map.put("password", password);

                return map;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(guardianLogin.this);
        requestQueue.add(request);

    }
}