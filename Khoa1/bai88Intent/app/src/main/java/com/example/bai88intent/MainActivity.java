package com.example.bai88intent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    static final String PREF_POINT = "PREF_POINT", PREF_CUR_POINT = "PREF_CUR_POINT";
    static ArrayList<String> arrayList;
    ImageView imgViewRoot, imageViewChild;
    Button btnExit;
    Intent intent;
    ActivityResultLauncher<Intent> mGetImage;
    int idRoot, point = 100;
    TextView tvPoint;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init() {
        imgViewRoot = findViewById(R.id.imageview_root);
        imageViewChild = findViewById(R.id.imageview_child);
        btnExit = findViewById(R.id.button_exit);
        tvPoint = findViewById(R.id.textview_point);

        sharedPreferences = getSharedPreferences(PREF_POINT, MODE_PRIVATE);

        tvPoint.setText(String.valueOf(sharedPreferences.getInt(PREF_CUR_POINT, 100)));

        arrayList =
                new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.list_name)));
    }

    public void process() {
        getImage();

        imageViewChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ImageActivity.class);
                mGetImage.launch(intent);
            }
        });

        exit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reload, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_reload) {
            reloadImageRoot();
        }
        return super.onOptionsItemSelected(item);
    }

    public void reloadImageRoot() {
        Collections.shuffle(arrayList);

        idRoot = getResources().getIdentifier(arrayList.get(0), "drawable", getPackageName());

        imgViewRoot.setImageResource(idRoot);
    }

    public void setSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_CUR_POINT, point);
        editor.commit();
    }

    public void exit() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }

    public void getImage() {
        reloadImageRoot();

        mGetImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            int idChild = getResources()
                                    .getIdentifier(data.getStringExtra("ChildImage"), "drawable", getPackageName());

                            imageViewChild.setImageResource(idChild);

                            if (idRoot == idChild) {
                                Toast.makeText(this, "Exactly", Toast.LENGTH_SHORT)
                                     .show();

                                point += 10;

                                new CountDownTimer(2000, 100) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {

                                    }

                                    @Override
                                    public void onFinish() {
                                        reloadImageRoot();
                                    }
                                }.start();
                            } else {
                                Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT)
                                     .show();
                                point -= 10;
                            }
                        }
                    } else if (result.getResultCode() == RESULT_CANCELED) {
                        Toast.makeText(this, "Ban bi tru 10 diem ", Toast.LENGTH_SHORT)
                             .show();
                        point -= 10;
                    }

                    setSharedPreferences();

                    tvPoint.setText(String.valueOf(point));
                });
    }

}