package com.example.bai77intentexplicitstring;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init() {
        btnSend = findViewById(R.id.button_send);
    }

    public void process() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

//                truyen nhan du lieu

//                String
//                intent.putExtra("Data", "anh chien");

//                number
//                intent.putExtra("Number", 2003);

//                array
                String[] a = {"pham", "duy", "chien"};
//                intent.putExtra("ArrayString", a);

//                object
                Student student = new Student("pham duy chien", 2003);
//                intent.putExtra("Object", (Serializable) student);

//                hon hop

                Bundle bundle = new Bundle();
                bundle.putString("String", "pham duy chien");
                bundle.putInt("Number", 2003);
                bundle.putStringArray("ArrayString", a);
                bundle.putSerializable("Object", student);

                intent.putExtra("container", bundle);
                startActivity(intent);
            }
        });
    }
}