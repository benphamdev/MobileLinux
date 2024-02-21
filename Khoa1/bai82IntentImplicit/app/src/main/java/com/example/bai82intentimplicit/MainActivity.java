package com.example.bai82intentimplicit;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CALL_PHONE = 1;
    // Inside your MainActivity.java file
    ImageView imgvNav, imgvCall;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init() {
        imgvNav = findViewById(R.id.imageView);
        imgvCall = findViewById(R.id.imageview_call);
    }

    public void process() {
//        bai82();
        bai83();
        bai84();
    }

    public void bai82() {
        imgvNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/"));
                startActivity(intent);
            }
        });
    }

    public void bai83() {
        imgvNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body", "hello");
                intent.setData(Uri.parse("sms:0833998180"));
                startActivity(intent);
            }
        });
    }

    public void bai84() {
        imgvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCallPhone();
            }
        });
    }

    public void makeCallPhone() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PHONE);
        } else {
            // Permission has already been granted
            performCall();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PHONE) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted
                performCall();
            } else {
                // Permission denied
                // You can handle this case by showing a dialog or a message
            }
        }
    }

    private void performCall() {
        // Now, you can proceed with making the call
        intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:0833998180"));
        startActivity(intent);
    }
}