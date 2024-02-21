package com.example.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CalActivity extends AppCompatActivity {
    private TextView tv1;
    private EditText number1, number2;
    private Button btAdd;
    private Spinner Sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);
        initView();
        solve();
    }

    public void solve() {
        Sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tmp1 = number1.getText().toString(), tmp2 = number2.getText().toString();
                        try {
                            Double nb1 = Double.parseDouble(tmp1), nb2 = Double.parseDouble(tmp2);
                            String target = Sp.getSelectedItem().toString();
                            String ans = poccess(nb1, nb2, target);
                            tv1.setText(ans);
                            Toast.makeText(getApplicationContext(), ans, Toast.LENGTH_LONG).show();
                        } catch (
                                NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "nhap 2 so ", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initView() {
        tv1 = findViewById(R.id.res);
        number1 = findViewById(R.id.e1);
        number2 = findViewById(R.id.e2);
//        cach 2 can btandd
        btAdd = findViewById(R.id.buttton1);
        Sp = findViewById(R.id.spExpress);
    }

    //    android:onClick="ADD"
//    public void ADD(View v) {
//        Double nb1 = Double.parseDouble(number1.getText().toString()),
//                nb2 = Double.parseDouble(number2.getText().toString());
//        try {
//            String ans = poccess(nb1, nb2, "+");
//            tv1.setText(ans);
//            Toast.makeText(this, ans, Toast.LENGTH_LONG).show();
//        } catch (NumberFormatException e) {
//
//        }
//    }

    private String poccess(double x, double y, String s) {
        String ans = "";
        switch (s) {
            case "+":
                ans = "tong : " + (x + y);
                break;
            case "-":
                ans = "hieu : " + (x - y);
                break;
            case "*":
                ans = "tich : " + (x * y);
                break;
            case "/":
                if (y == 0)
                    ans = "Error!!";
                else
                    ans = "thuong : " + (x / y);
                break;
        }
        return ans;
    }
}