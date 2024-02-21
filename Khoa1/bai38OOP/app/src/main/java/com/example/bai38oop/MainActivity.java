package com.example.bai38oop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<String> a;

    EditText editText;
    Button btnAdd, btnUpdate;

    int pos = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Student student = new Student("pham duy chien", "thai binh", 20);
//        Log.d("test", student.getAddress());

        init();
        process();
    }
    public void init(){
        lv = findViewById(R.id.listCourse);
        a = new ArrayList<>(Arrays.asList("nodejs", "nextJs", "python", "c#"));
        btnAdd = findViewById(R.id.buttonAdd);
        btnUpdate = findViewById(R.id.buttonUpdate);
        editText = findViewById(R.id.editTextCourse);
    }
    public void process(){
        final ArrayAdapter<String> adapter = new
                ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                a);

        lv.setAdapter(adapter);

        bai44(adapter);
    }
    public void bai43(){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, a.get(position), Toast.LENGTH_LONG).show();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "long click " + a.get(position), Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
    public void bai44(ArrayAdapter<String> adapter){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                a.add(s);
//                lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editText.setText(a.get(position));
                pos = position;
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.set(pos, editText.getText().toString());
//                lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                a.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

}