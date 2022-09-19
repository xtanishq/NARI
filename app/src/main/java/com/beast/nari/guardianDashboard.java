package com.beast.nari;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

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
import com.beast.nari.databinding.ActivityGuardianDashboardBinding;
import com.beast.nari.model.UserModel;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class guardianDashboard extends AppCompatActivity {

    private ActivityGuardianDashboardBinding binding;
    String friend;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String url = "https://biochemical-damping.000webhostapp.com/nari/fetchUserToGaurd.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_guardian_dashboard);

        sp = getSharedPreferences("DATA_NAME", MODE_PRIVATE);
        editor = sp.edit();

        checkForFriend();

        binding.addNari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), AddNariActivity.class));

                if (binding.addNariText.getText().toString() != "Add Nari"){
                    Toast.makeText(guardianDashboard.this, "you have already added the friend , you can't add more", Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(getApplicationContext(), AddNariActivity.class));
                }

            }

        });

    }

    private void checkForFriend() {

        String userID = sp.getString("userName", null);

        if (userID != null) {

            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {

                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");
                                JSONArray jsonArray = jsonObject.getJSONArray("data");

                                if (success.equals("1")) {

                                    for (int i = 0; i < jsonArray.length(); i++) {

                                        JSONObject object = jsonArray.getJSONObject(i);

                                        String userid = object.getString("userid");
                                        if (userid != null){
                                            findBy(userid);
                                        }

                                    }


                                } else {
                                    Toast.makeText(guardianDashboard.this, "something went wrong try again later", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {

                                Toast.makeText(guardianDashboard.this, "something went wrong\n" + e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(guardianDashboard.this, "something went wrong\n" + error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }) {

                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> map = new HashMap<>();

                    map.put("keyword", userID);

                    return map;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(guardianDashboard.this);
            requestQueue.add(request);

        }
    }

    private void findBy(String userID) {

        StringRequest request = new StringRequest(Request.Method.POST, "https://biochemical-damping.000webhostapp.com/nari/fetchUserID.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (success.equals("1")){

                                for (int i = 0; i < jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String userName = object.getString("userName");
                                    String userID = object.getString("userID");
                                    String profilePic ="https://biochemical-damping.000webhostapp.com/nari/profilepic/" + object.getString("profilePic");

                                    setData(userID, userName, profilePic);

                                }


                            }else {
                                Toast.makeText(guardianDashboard.this, "something went wrong try again later", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {

                            Toast.makeText(guardianDashboard.this, "something went wrong\n" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(guardianDashboard.this, "something went wrong\n" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();

                map.put("keyword", userID);

                return map;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(guardianDashboard.this);
        requestQueue.add(request);

    }

    private void setData(String userID, String userName, String profilePic) {

        if (userID != null || userName != null || profilePic != null){

            editor.putString("FRIEND", userID);
            editor.apply();

            Glide.with(this).load(profilePic).into(binding.addNari);
            binding.addNariText.setText(userName);

        }

    }
}