package com.example.bai4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CheckBox ck1, ck2, ck3;
    private RadioButton g1, g2;
    private RatingBar rt;
    private Spinner Sp1, Sp2;
    private Button button1;
    private TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initSpinner();
        solve();
    }

    private void initSpinner() {
//        android:entries="@array/country" khoi can cai nay neu khai bao trong day
//        String [] list = getResources().getStringArray(R.array.country);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item1, list);
//        Sp1.setAdapter(adapter);

        String [] a = {"NEU", "PTIT", "HUST"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item1, a);
        Sp2.setAdapter(adapter);
    }

    private void solve() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = "";
                if(ck1.isChecked())
                    tmp += ck1.getText() +  ",";
                if(ck2.isChecked())
                    tmp += ck2.getText() + ",";
                if(ck3.isChecked())
                    tmp += ck3.getText() + ",";
                if(tmp.endsWith(","))
                    tmp = tmp.substring(0,tmp.length() - 1);
                tmp += "\nGioi tinh : ";
                if(g1.isChecked())
                    tmp += g1.getText() + " ";
                else
                    tmp += g2.getText() + " ";
                tmp += "\n Rating " + rt.getRating()
                        +"\n"+ Sp1.getSelectedItem().toString()
                        + "\n" + Sp2.getSelectedItem().toString();
                res.setText(tmp);
            }
        });
    }

    private void initView() {
        ck1 = findViewById(R.id.cb1);
        ck2 = findViewById(R.id.cb2);
        ck3 = findViewById(R.id.cb3);
        g1 = findViewById(R.id.gnam);
        g2 = findViewById(R.id.gnu);
        rt = findViewById(R.id.rating);
        Sp1 = findViewById(R.id.sp1);
        Sp2 = findViewById(R.id.sp2);
        button1 = findViewById(R.id.btn);
        res = findViewById(R.id.tv1);
    }
}