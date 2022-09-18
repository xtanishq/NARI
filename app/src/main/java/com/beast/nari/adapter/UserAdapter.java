package com.beast.nari.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beast.nari.R;
import com.beast.nari.model.UserModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewAdapter> {

    private List<UserModel> userList;
    private Context context;
    private Activity activity;

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

        Glide.with(context).load(userList.get(position).getProfilePic()).into(holder.profilePic);
        holder.userName.setText(userList.get(position).getUserName());
        holder.userID.setText(userList.get(position).getUserID());

        holder.sendReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName, userID;

                userName = userList.get(position).getUserName();
                userID = userList.get(position).getUserID();

                Toast.makeText(context, "sending request to the : " + userID, Toast.LENGTH_SHORT).show();

            }
        });

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
