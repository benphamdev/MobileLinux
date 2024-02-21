package com.example.bai95sharepreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static final String PREF_USERNAME = "PREF_USERNAME",
            PREF_PASSWORD = "PREF_PASSWORD",
            PREF_LOGIN = "PREF_LOGIN",
            PREF_STATE_CHECKED = "PREF_STATE_CHECKED";
    EditText edtUserName, edtPassWord;
    Button btnConfirm;
    CheckBox chbRem;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init() {
        edtUserName = findViewById(R.id.edit_text_username);
        edtPassWord = findViewById(R.id.edit_text_password);
        chbRem = findViewById(R.id.checkbox_remem);
        btnConfirm = findViewById(R.id.button_login);

        sharedPreferences = getSharedPreferences(PREF_LOGIN, MODE_PRIVATE);
        edtUserName.setText(sharedPreferences.getString(PREF_USERNAME, ""));
        edtPassWord.setText(sharedPreferences.getString(PREF_PASSWORD, ""));
        chbRem.setChecked(sharedPreferences.getBoolean(PREF_STATE_CHECKED, false));
    }

    public void process() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText()
                                             .toString(),
                        passWord = edtPassWord.getText()
                                              .toString();

                if (userName.equals("chien") && passWord.equals("123")) {
                    Toast.makeText(MainActivity.this, "Successfull log in", Toast.LENGTH_SHORT)
                         .show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    if (chbRem.isChecked()) {
                        editor.putString(PREF_USERNAME, userName);
                        editor.putString(PREF_PASSWORD, passWord);
                        editor.putBoolean(PREF_STATE_CHECKED, true);
                    } else {
                        editor.remove(PREF_USERNAME);
                        editor.remove(PREF_PASSWORD);
                        editor.remove(PREF_STATE_CHECKED);
                    }

                    editor.commit();
                } else {
                    Toast.makeText(MainActivity.this, "Failed log in", Toast.LENGTH_SHORT)
                         .show();
                }
            }
        });
    }

}