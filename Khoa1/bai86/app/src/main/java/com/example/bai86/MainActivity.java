package com.example.bai86;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    ImageView imgRes;
    Button btnTmp;
    ActivityResultLauncher<Intent> cameraLauncher;
    Intent cameraIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init() {
        imgRes = findViewById(R.id.imageview_img);
        btnTmp = findViewById(R.id.button_camera);

        cameraLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == RESULT_OK) {
                                Intent data = result.getData();
                                Bitmap photo = (Bitmap) data.getExtras()
                                                            .get("data");
                                imgRes.setImageBitmap(photo);
                            }
                        });
    }

    public void process() {
        btnTmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePhoto();
            }
        });
    }

    private void dispatchTakePhoto() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE);
        } else {
            // Permission already granted, launch camera intent
            launchCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, launch camera intent
                launchCamera();
            } else {
                // Permission denied, show a message or handle accordingly
                Toast.makeText(this, "Khong cho phep mo camera", Toast.LENGTH_SHORT)
                     .show();
            }
        }
    }

    private void launchCamera() {
        cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            cameraLauncher.launch(cameraIntent);
        }
    }
}