package com.example.bai6apilogin.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bai6apilogin.R;
import com.example.bai6apilogin.api.ApiService;
import com.example.bai6apilogin.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin;

    private List<User> mUsers;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpUI();
        getUser();
        clickLogin();
    }

    private void setUpUI() {
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        mUsers = new ArrayList<>();
    }

    private void getUser() {
        ApiService.apiService
                .getUsers()
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(
                            Call<List<User>> call, Response<List<User>> response
                    ) {
                        if (response.isSuccessful()) {
                            mUsers = response.body();
                            Log.d("TAG", "onResponse: " + mUsers.size());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Log.d("TAG", "onFailure: " + t.getMessage());
                    }
                });
    }

    private void clickLogin() {
        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText()
                                         .toString()
                                         .trim();
            String password = edtPassword.getText()
                                         .toString()
                                         .trim();

            boolean isLogin = false;
            if (mUsers != null && !mUsers.isEmpty()) {
                for (User user : mUsers) {
                    if (username.equals(user.getUserName()) && password.equals(user.getEmail())) {
                        mUser = user;
                        navigateToMain();
//                        isLogin = true;
                        return;
                    }
                }
            }

//            navigateToMain();
            // Show error message
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT)
                 .show();
        });
    }

    private void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", mUser);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}