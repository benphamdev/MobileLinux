package com.example.bai7uploadimage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bai7uploadimage.api.ApiService;
import com.example.bai7uploadimage.api.Const;
import com.example.bai7uploadimage.model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    EditText edtUsername, edtPassword;
    ImageView imgFromGallery, imgFromApi;
    Button btnSelectImage, btnUploadImage;
    TextView tvUsername, tvPassword;
    int PERMISSION_CODE = 1;
    Uri mUir;
    private ActivityResultLauncher<Intent> mActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();

        mActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Log.e(TAG, "onActivityResult: " + result.getData());
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // Get image from gallery
                        Uri imageUri = result.getData()
                                             .getData();
                        mUir = imageUri;
                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    imageUri
                            );
                            imgFromGallery.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

        selectImage();
        uploadImage();
    }

    private void setUpUI() {
        edtUsername = findViewById(R.id.et_name);
        edtPassword = findViewById(R.id.et_password);
        imgFromGallery = findViewById(R.id.img_from_gallery);
        imgFromApi = findViewById(R.id.img_from_api);
        btnSelectImage = findViewById(R.id.btn_select_image);
        btnUploadImage = findViewById(R.id.btn_upload_image);
        tvUsername = findViewById(R.id.tv_username);
        tvPassword = findViewById(R.id.tv_password);
    }

    public void selectImage() {
        // Code here
        btnSelectImage.setOnClickListener(v -> {
            // Code here
            onClickRequestPermission();
        });
    }

    private void onClickRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions, PERMISSION_CODE);
            } else {
                pickImageFromGallery();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImageFromGallery();
            } else {
                Log.e(TAG, "Permission denied");
            }
        }
    }

    public void pickImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }

    public void uploadImage() {
        // Code here
        btnUploadImage.setOnClickListener(v -> {
//            if (mUir != null) {
//                uploadImageToApi();
//            }

            getImageFromApi();
        });
    }

    private void uploadImageToApi() {

        String username = edtUsername.getText()
                                     .toString()
                                     .trim(),
                password = edtPassword.getText()
                                      .toString()
                                      .trim();
        RequestBody requestBodyUsername = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                username
        );

        RequestBody requestBodyPassword = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                password
        );

        String strRealPath = RealPathUtil.getRealPath(this, mUir);
        Log.e(TAG, "uploadImageToApi: " + strRealPath);
//        File file = new File(mUir.getPath());

        File file = new File(strRealPath);

        RequestBody requestBodyAvt = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                file
        );

        MultipartBody.Part body = MultipartBody.Part.createFormData(
                Const.KEY_IMAGES,
                file.getName(),
                requestBodyAvt
        );

        ApiService.apiService
                .registerAccount(requestBodyUsername, body, requestBodyPassword)
                .enqueue(new retrofit2.Callback<User>() {
                    @Override
                    public void onResponse(
                            retrofit2.Call<User> call, retrofit2.Response<User> response
                    ) {
                        if (response.isSuccessful()) {
                            User user = response.body();

                            tvUsername.setText(user.getName());
                            tvPassword.setText(user.getDescription());

                            Glide.with(MainActivity.this)
                                 .load(user.getImages())
                                 .into(imgFromApi);

                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<User> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

    public void getImageFromApi() {
        ApiService.apiService
                .getCategories()
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (response.isSuccessful()) {
                            List<User> users = response.body();
                            tvUsername.setText(users.get(0)
                                                    .getName());
                            tvPassword.setText(users.get(0)
                                                    .getDescription());

                            Glide.with(MainActivity.this)
                                 .load(users.get(0)
                                            .getImages())
                                 .into(imgFromApi);

                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<List<User>> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }
}