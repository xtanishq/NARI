package com.beast.nari.adapter;

import static android.content.SharedPreferences.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.beast.nari.AddNariActivity;
import com.beast.nari.R;
import com.beast.nari.guardianDashboard;
import com.beast.nari.model.UserModel;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewAdapter> {

    private List<UserModel> userList;
    private Context context;
    private Activity activity;
    String url = "https://biochemical-damping.000webhostapp.com/nari/addfriend.php";
    ProgressDialog progressDialog;

    public UserAdapter(List<UserModel> userList, Context context, Activity activity) {
        this.userList = userList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public UserViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_user_show, parent, false);
        return new UserViewAdapter(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewAdapter holder, @SuppressLint("RecyclerView") int position) {

        progressDialog = new ProgressDialog(activity);

        Glide.with(context).load(userList.get(position).getProfilePic()).into(holder.profilePic);
        holder.userName.setText(userList.get(position).getUserName());
        holder.userID.setText(userList.get(position).getUserID());

        holder.sendReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                String userName, userID;

                userName = userList.get(position).getUserName();
                userID = userList.get(position).getUserID();

                sendRequestTo(userID);

            }
        });

    }

    private void sendRequestTo(String userID) {

        SharedPreferences sp = activity.getSharedPreferences("DATA_NAME", Context.MODE_PRIVATE);

        String frUserID = sp.getString("userName", null);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if (response.contains("data updated") || response.equalsIgnoreCase("data updated")){

                            progressDialog.dismiss();

                            Toast.makeText(context, "friend request sent successfully", Toast.LENGTH_SHORT).show();

                            activity.startActivity(new Intent(activity, guardianDashboard.class));
                            activity.finish();

                        }

                        if (response.contains("can't update the data") || response.equalsIgnoreCase("can't update the data")){
                            progressDialog.dismiss();
                            Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(activity, "something went wrong\n" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();

                map.put("friendUserID", frUserID);
                map.put("userid", userID);

                return map;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(request);


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewAdapter extends RecyclerView.ViewHolder {

        ImageView profilePic, sendReq;
        TextView userID, userName;

        public UserViewAdapter(@NonNull View itemView) {
            super(itemView);

            profilePic = itemView.findViewById(R.id.userProfilePic);
            sendReq = itemView.findViewById(R.id.sendReq);
            userID = itemView.findViewById(R.id.userID);
            userName = itemView.findViewById(R.id.userName);

        }
    }


}
