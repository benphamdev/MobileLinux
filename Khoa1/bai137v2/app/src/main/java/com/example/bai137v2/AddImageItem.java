package com.example.bai137v2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;

public class AddImageItem extends AppCompatActivity {

    Button btnAdd, btnCancel;
    ImageButton imgBtnCam, imgBtnFolder;
    ImageView imgViewResult;
    EditText edtNameItem, edtDesc;
    int REQUEST_CODE_CAMERA = 123, RESQUEST_CODE_FOLDER = 456;
    ActivityResultLauncher<Intent> cameraLauncher, folderLauncher;
    Intent cameraIntent, folderIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image_item);

        setUpUI();
        addEvent();

    }

    public void addEvent() {

        imgBtnCam.setOnClickListener(v -> {
            // Mở camera
            dispatchTakePictureIntent();
        });

        imgBtnFolder.setOnClickListener(v -> {
            // Mở thư mục ảnh
            dispatchTakePictureFolder();
        });

        btnAdd.setOnClickListener(v -> {
            // Thêm dữ liệu vào database
            String name = edtNameItem.getText()
                                     .toString()
                                     .trim();
            String desc = edtDesc.getText()
                                 .toString()
                                 .trim();
            byte[] image = imageViewToByte(imgViewResult);
            MainActivity.database.insertData(name, desc, image);
            Toast.makeText(this, "Thêm dữ liệu thành công", Toast.LENGTH_SHORT)
                 .show();
            startActivity(new Intent(AddImageItem.this, MainActivity.class));
        });
    }

    public void setUpUI() {
        btnAdd = findViewById(R.id.button_add);
        btnCancel = findViewById(R.id.button_cancel);
        imgBtnCam = findViewById(R.id.imageBtn_cam);
        imgBtnFolder = findViewById(R.id.imageBtn_folder);
        imgViewResult = findViewById(R.id.imageBtn_res);
        edtNameItem = findViewById(R.id.editText_name_item);
        edtDesc = findViewById(R.id.editText_description);

        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        Bitmap bitmap = (Bitmap) data.getExtras()
                                                     .get("data");
                        imgViewResult.setImageBitmap(bitmap);
                    }
                }
        );

        folderLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        imgViewResult.setImageURI(data.getData());
                    }
                }
        );
    }

    private void dispatchTakePictureFolder() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    RESQUEST_CODE_FOLDER
            );
        } else {
            launchFolder();
        }
    }

    private void dispatchTakePictureIntent() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{android.Manifest.permission.CAMERA},
                    REQUEST_CODE_CAMERA
            );
        } else {
            launchCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchCamera();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT)
                     .show();
            }
        }

        if (requestCode == RESQUEST_CODE_FOLDER) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchFolder();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT)
                     .show();
            }
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void launchCamera() {
        cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            cameraLauncher.launch(cameraIntent);
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void launchFolder() {
        folderIntent = new Intent(Intent.ACTION_PICK);
        if (folderIntent.resolveActivity(getPackageManager()) != null) {
            folderIntent.setType("image/*");
            folderIntent.setAction(Intent.ACTION_GET_CONTENT);
            folderLauncher.launch(folderIntent);
        }
    }

    public byte[] imageViewToByte(ImageView imgView) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bitmap =
                ((BitmapDrawable) imgView.getDrawable()).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}