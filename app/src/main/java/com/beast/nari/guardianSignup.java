package com.beast.nari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.beast.nari.databinding.ActivityGuardianSignupBinding;

public class guardianSignup extends AppCompatActivity {

    private ActivityGuardianSignupBinding binding;

    Button signup,already;

    Button button;
    String Doc;
    String Document="doc";
    byte[] bytes;
    String uriString, position;
    ProgressDialog progressDialog;
    TextView skip;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_guardian_signup);

        signup = findViewById(R.id.signup2);
        already = findViewById(R.id.login2);
        button = findViewById(R.id.guabutton);

        radioGroup = findViewById(R.id.radio);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(guardianSignup.this,guardianDashboard.class));
            }
        });

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(guardianSignup.this,guardianLogin.class));
            }
        });


        Intent intent = getIntent();
        String status = intent.getStringExtra("from main");

        SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);

        String updateStatus = sp.getString("updateStatus", null);

        Doc = sp.getString("doc", String.valueOf(-1));
    }
}