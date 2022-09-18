package com.beast.nari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.beast.nari.adapter.UserAdapter;
import com.beast.nari.databinding.ActivityAddNariBinding;
import com.beast.nari.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class AddNariActivity extends AppCompatActivity {

    private ActivityAddNariBinding binding;

    UserAdapter userAdapter;
    UserModel userModel;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<UserModel> modelList;
    String keyword;

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
                keyword = binding.edSearchUser.getText().toString();

                if (keyword.isEmpty()){
                    Toast.makeText(AddNariActivity.this, "please insert something to search", Toast.LENGTH_SHORT).show();
                }else {
                    findByKeyword(keyword);
                }

            }
        });

    }

    private void findByKeyword(String keyword) {

        Toast.makeText(this, "finding result by : " + keyword, Toast.LENGTH_SHORT).show();

    }
}