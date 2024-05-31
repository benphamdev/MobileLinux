package com.example.roomdbv1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roomdbv1.database.UserDatabase;

public class UpdateActivity extends AppCompatActivity {
    EditText etUsername, etAddress, etDob;
    Button btnUpdateUser;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        setUpUI();

        mUser = (User) getIntent().getSerializableExtra("objectUser");
        if (mUser != null) {
            etUsername.setText(mUser.getUsername());
            etAddress.setText(mUser.getAddress());
            etDob.setText(mUser.getDob());
        }

        btnUpdateUser.setOnClickListener(v -> {
            updateUser();
        });
    }

    private void updateUser() {
        String username = etUsername.getText()
                                    .toString();
        String address = etAddress.getText()
                                  .toString();
        String dob = etDob.getText()
                          .toString();

        if (username.isEmpty() || address.isEmpty() || dob.isEmpty()) {
            return;
        }

        mUser.setUsername(username);
        mUser.setAddress(address);
        mUser.setDob(dob);

        UserDatabase.getInstance(this)
                    .userDAO()
                    .updateUser(mUser);

        Toast.makeText(this, "Update successfully", Toast.LENGTH_SHORT)
             .show();

        Intent intentResult = new Intent();
        setResult(RESULT_OK, intentResult);
        finish();
    }

    private void setUpUI() {
        etUsername = findViewById(R.id.edt_username);
        etAddress = findViewById(R.id.edt_address);
        etDob = findViewById(R.id.edt_dob);
        btnUpdateUser = findViewById(R.id.btn_update);
    }
}