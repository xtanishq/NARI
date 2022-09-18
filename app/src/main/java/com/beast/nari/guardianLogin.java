package com.beast.nari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.beast.nari.databinding.ActivityGuardianLoginBinding;

public class guardianLogin extends AppCompatActivity {

    Button log,signup;
    private ActivityGuardianLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_guardian_login);

        log = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(guardianLogin.this,guardianDashboard.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(guardianLogin.this,guardianSignup.class));
            }
        });
    }
}