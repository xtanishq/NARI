package com.beast.nari;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.beast.nari.adapter.UserAdapter;
import com.beast.nari.databinding.ActivityAddNariBinding;
import com.beast.nari.model.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddNariActivity extends AppCompatActivity {

    private ActivityAddNariBinding binding;

    UserAdapter userAdapter;
    UserModel userModel;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<UserModel> modelList;
    String keyword;
    String urlUserID = "https://biochemical-damping.000webhostapp.com/nari/fetchUserID.php";
    String urlUserName = "https://biochemical-damping.000webhostapp.com/nari/fetchUsers.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_nari);

        recyclerView = findViewById(R.id.addUserRecycler);

        linearLayoutManager = new LinearLayoutManager(AddNariActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        modelList = new ArrayList<>();
        userAdapter = new UserAdapter(modelList, this, AddNariActivity.this);
        recyclerView.setAdapter(userAdapter);

        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyword = binding.edSearchUser.getText().toString().trim();

                if (keyword.isEmpty()){
                    Toast.makeText(AddNariActivity.this, "please insert something to search", Toast.LENGTH_SHORT).show();
                }else {
                    findByKeyword(keyword);
                }

            }
        });

    }

    private void findByKeyword(String keyword) {

        StringRequest request = new StringRequest(Request.Method.POST, urlUserID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (success.equals("1")){
                                modelList.clear();

                                for (int i = 0; i < jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String userName = object.getString("userName");
                                    String userID = object.getString("userID");
                                    String profilePic ="https://biochemical-damping.000webhostapp.com/nari/profilepic/" + object.getString("profilePic");

                                    userModel = new UserModel(userID, userName, profilePic);
                                    modelList.add(userModel);
                                    userAdapter.notifyDataSetChanged();

                                }

                                addByUserName(keyword);

                            }else {
                                Toast.makeText(AddNariActivity.this, "something went wrong try again later", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {

                            Toast.makeText(AddNariActivity.this, "something went wrong\n" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AddNariActivity.this, "something went wrong\n" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();

                map.put("keyword", keyword);

                return map;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(AddNariActivity.this);
        requestQueue.add(request);

    }

    private void addByUserName(String keyword) {
        
        StringRequest request = new StringRequest(Request.Method.POST, urlUserName,
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
                                    String userID = object.getString("userID");
                                    int check = 0;
                                    for (int j = 0; j < modelList.size(); j++){

                                        if (!(modelList.get(j).getUserID().equals(userID))){

                                            check++;

                                            if (check == modelList.size()){

                                                String userName = object.getString("userName");
                                                userID = object.getString("userID");
                                                String profilePic ="https://biochemical-damping.000webhostapp.com/nari/profilepic/" + object.getString("profilePic");
                                                userModel = new UserModel(userID, userName, profilePic);
                                                modelList.add(userModel);
                                                userAdapter.notifyDataSetChanged();

                                                j = modelList.size() + 2;
                                            }

                                        }

                                    }


                                }


                            }else {
                                Toast.makeText(AddNariActivity.this, "something went wrong try again later", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {

                            Toast.makeText(AddNariActivity.this, "something went wrong\n" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AddNariActivity.this, "something went wrong\n" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();

                map.put("keyword", keyword);

                return map;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(AddNariActivity.this);
        requestQueue.add(request);

    }
}