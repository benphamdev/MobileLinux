package com.example.bai6apilogin.controller;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bai6apilogin.R;
import com.example.bai6apilogin.model.User;

public class MainActivity extends AppCompatActivity {
    TextView tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
    }

    private void setUpUI() {
        tvUser = findViewById(R.id.tv_user_infor);
    }

    private void showUserInfor() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            User user = (User) bundle.getSerializable("user");
            if (user != null) {
                tvUser.setText("Username: " + user.getUserName() + "\nEmail: " + user.getEmail());
            }
        }
    }
}