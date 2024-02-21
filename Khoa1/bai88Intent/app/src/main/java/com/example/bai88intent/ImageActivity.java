package com.example.bai88intent;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

public class ImageActivity extends AppCompatActivity {
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_Dialog);
        setContentView(R.layout.activity_image);

        init();
        process();
    }

    public void init() {
        tableLayout = findViewById(R.id.table_layout);
    }

    public void process() {
        createTableLayout();
    }

    public void createTableLayout() {
//        tableLayout.setLayoutParams(
//                new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        tableLayout.setShrinkAllColumns(true);
        tableLayout.setStretchAllColumns(true);

        int sz = MainActivity.arrayList.size(), columns = 3,
                rows = (int) Math.ceil((float) sz / columns);

        Log.d("aaa", String.valueOf(sz));
        Log.d("aaa", String.valueOf(rows));
        int idx = 0, mx = sz;

        for (int i = 0; i < rows; i++) {
            TableRow tableRow = new TableRow(this);

            for (int j = 0; j < columns; j++) {
                ImageView imageView = new ImageView(this);

                float dip = 100f;
                Resources r = getResources();
                float px =
                        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());

                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams((int) px, (int) px);
                imageView.setLayoutParams(layoutParams);

                idx = columns * i + j;
                if (idx < mx) {
                    int id =
                            getResources().getIdentifier(MainActivity.arrayList.get(idx), "drawable", getPackageName());

                    imageView.setImageResource(id);
                    tableRow.addView(imageView);

                    selectImage(imageView, idx);

                } else break;
            }
            tableLayout.addView(tableRow);
        }
    }

    public void selectImage(ImageView imageView, int pos) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("ChildImage", MainActivity.arrayList.get(pos));
                setResult(RESULT_OK, intent);
                finish();

//                Toast.makeText(ImageActivity.this, MainActivity.arrayList.get(pos), Toast.LENGTH_SHORT)
//                     .show();
            }
        });
    }
}