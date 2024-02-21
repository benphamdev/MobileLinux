package com.example.bai77intentexplicitstring;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        init();
        process();
    }

    public void init() {
        tvContent = findViewById(R.id.textview_content);
    }

    public void process() {
        Intent intent = getIntent();

//        String ans = intent.getStringExtra("Data");

//        int ans = intent.getIntExtra("Number", 0);// sai 1 trong 2 thi lay defaultvalue
//        tvContent.setText(String.valueOf(ans));

//        String[] ans = intent.getStringArrayExtra("ArrayString");
//        tvContent.setText(String.valueOf(ans.length));

//        Student student = (Student) intent.getSerializableExtra("Object");
//        tvContent.setText(student.getFullName());

        Bundle bundle = intent.getBundleExtra("container");

        try {
            String fullName = bundle.getString("String");
            int num = bundle.getInt("Number");
            String[] list = bundle.getStringArray("ArrayString");
            Student student = (Student) bundle.getSerializable("Object1");

            tvContent.setText(fullName + "\n" + num + "\n" + list.length + "\n" + student.getFullName());
        } catch (NullPointerException e) {
            Toast.makeText(this, "loi String", Toast.LENGTH_SHORT)
                 .show();
        }
        // co 3 th loi la full name , list , object deu la string chua suy nghi duoc cach fix
    }
}