package com.example.bai69dialogcustom;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView textViewLogin;

    EditText etUsername, etPassword;
    Button btnAccept, btnCancel;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init() {
        textViewLogin = findViewById(R.id.textview_login);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom);
//        dialog.setTitle("Dang nhap");// tuy may co may khong
        dialog.setCanceledOnTouchOutside(false);
        etUsername = dialog.findViewById(R.id.edittext_username);
        etPassword = dialog.findViewById(R.id.edittext_password);
        btnAccept = dialog.findViewById(R.id.button_yes);
        btnCancel = dialog.findViewById(R.id.button_cancel);
    }

    public void process() {
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLogin();
            }
        });
    }

    public void dialogLogin() {
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText()
                                            .toString()
                                            .trim(),
                        password = etPassword.getText()
                                             .toString()
                                             .trim(),
                        tmp = "";

                if (username.equals("chien") && password.equals("chien")) tmp = "thanh cong";
                else {tmp = "that bai";}

                Toast.makeText(MainActivity.this, tmp, Toast.LENGTH_SHORT)
                     .show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialog.cancel();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}