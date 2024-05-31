package com.example.uploadimageusingretrofit;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;

import java.io.File;
import java.util.Collections;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 11;
    private static final int GALLERY_PERMISSION_REQUEST_CODE = 12;
    public static Uri imageUri;
    TextView id, userName, userEmail, gender;
    Button btnLogout;
    ImageView imageViewpprofile;
    ImageView imgCam, imgGallery;
    ImageButton imgEditAvatar;
    Dialog dialog;
    int id1 = 0;
    ApiService apiService;
    private Intent cameraIntent, galleryIntent;
    private ActivityResultLauncher<Intent> cameraLauncher, galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get image from camera
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        imageViewpprofile.setImageURI(imageUri);
                        dialog.dismiss();
//                        UploadImage1();

                        createPost();
                    }
                }
        );

        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            Toast.makeText(this, "Don't choose image", Toast.LENGTH_SHORT)
                                 .show();
                            return;
                        }
                        imageUri = data.getData();
                        imageViewpprofile.setImageURI(imageUri);
                        dialog.dismiss();
//                        UploadImage1();
                        createPost();
                    }
                }
        );

        setUpUI();

        imgEditAvatar.setOnClickListener(v -> {
            showCustomDialog();
        });

//        getAllPostBySort();

    }

    public void UploadImage1() {
        Log.d("oke", "UploadImage1: ");
        if (imageUri == null)
            return;

        Log.d("oke", "UploadImage2: ");
        // Create a Retrofit instance
        Retrofit retrofit = RetrofitClient.getRetrofit();

        // Create a RequestBody instance from the user_id
        RequestBody userIdBody = RequestBody.create(MediaType.parse("text/plain"), "2");

        // Create a file instance from the Uri
        String realPath = RealPathUtil.getRealPath(this, imageUri);
        File file = new File(realPath);
        // Print the file size
//        file = compressImage(file);
        long fileSizeInBytes = file.length();
        long fileSizeInKB = fileSizeInBytes / 1024;
        long fileSizeInMB = fileSizeInKB / 1024;
        Log.d("Upload", "File size: " + fileSizeInMB + " MB");

        // Create a RequestBody instance from the file
        RequestBody requestFile = RequestBody.create(
                MediaType.parse(getContentResolver().getType(imageUri)),
                file
        );

        // Create a MultipartBody.Part from the file RequestBody
        MultipartBody.Part body = MultipartBody.Part.createFormData(
                "profilePicture",
                file.getName(),
                requestFile
        );

        // Get the API interface
        apiService = RetrofitClient.getRetrofit()
                                   .create(ApiService.class);

        // Call the API
        apiService.uploadImage(userIdBody, body)
                  .enqueue(new Callback<BaseResponse<UserResponse>>() {
                      @Override
                      public void onResponse(
                              Call<BaseResponse<UserResponse>> call,
                              Response<BaseResponse<UserResponse>> response
                      ) {
                          if (response.isSuccessful()) {
                              // Handle the response
                              Toast.makeText(
                                           MainActivity.this,
                                           "Upload success",
                                           Toast.LENGTH_SHORT
                                   )
                                   .show();
                          } else {
                              // Handle the error
                              Toast.makeText(
                                           MainActivity.this,
                                           "Upload failed",
                                           Toast.LENGTH_SHORT
                                   )
                                   .show();
                              Log.e("Error2", response.message());
                          }
                      }

                      @Override
                      public void onFailure(
                              Call<BaseResponse<UserResponse>> call, Throwable t
                      ) {
                          // Handle the error
                          Toast.makeText(MainActivity.this, "Upload failed", Toast.LENGTH_SHORT)
                               .show();
                          Log.e("Error1", t.getMessage());
                      }
                  });
    }

//    public File compressImage(File originalFile) {
//        Bitmap bitmap = BitmapFactory.decodeFile(originalFile.getPath());
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);
//        File compressedFile = new File(getCacheDir(), "compressed.jpg");
//        try (FileOutputStream fos = new FileOutputStream(compressedFile)) {
//            fos.write(out.toByteArray());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return compressedFile;
//    }

    public void setUpUI() {
        id = findViewById(R.id.tv_id);
        userName = findViewById(R.id.tv_username);
        userEmail = findViewById(R.id.tv_email);
        gender = findViewById(R.id.tv_gender);
        btnLogout = findViewById(R.id.button_logout);
        imageViewpprofile = findViewById(R.id.img_avt);
        imgEditAvatar = findViewById(R.id.image_edit_ava);
    }

    private void dispatchTakePhotoGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        galleryLauncher.launch(intent);
    }

    private void dispatchTakePhoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE
            );
        } else {
            // Permission already granted, launch camera intent
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

        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, launch camera intent
                launchCamera();
            } else {
                // Permission denied, show a message or handle accordingly
                Toast.makeText(this, "Permission deny", Toast.LENGTH_SHORT)
                     .show();
            }
        }

        if (requestCode == GALLERY_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, launch gallery intent
                launchGallery();
            } else {
                // Permission denied, show a message or handle accordingly
                Toast.makeText(this, "Permission deny", Toast.LENGTH_SHORT)
                     .show();
            }
        }
    }

    private void launchGallery() {
        galleryIntent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        );
        galleryLauncher.launch(galleryIntent);
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void launchCamera() {
        cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "New Picture");
            values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
            imageUri = getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    values
            );
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            cameraLauncher.launch(cameraIntent);
        }
    }

    private void showCustomDialog() {
        // Create dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);

        // Find components in the dialog layout
        imgCam = dialog.findViewById(R.id.image_camera);
        imgGallery = dialog.findViewById(R.id.image_folder);
        Button buttonCancel = dialog.findViewById(R.id.button_cancel);

        imgCam.setOnClickListener(v -> dispatchTakePhoto());

        imgGallery.setOnClickListener(v -> dispatchTakePhotoGallery());
        // Set click listener for the Cancel button
        buttonCancel.setOnClickListener(v -> {
            // Dismiss the dialog when Cancel button is clicked
            dialog.dismiss();
        });

        // Show dialog
        dialog.show();
    }

    // create a post
    public void createPost() {
        // Create a Retrofit instance
        Retrofit retrofit = RetrofitClient.getRetrofit();

        Gson gson = new Gson();
        PostCreationRequest post = new PostCreationRequest(
                "Test Post",
                "This is a test post.",
                Collections.singletonList(10)
        );

        String postJson = gson.toJson(post);

        // Create a RequestBody instance from the post
        RequestBody postBody = RequestBody.create(MediaType.parse("application/json"), postJson);

        // Create a file instance from the Uri
        String realPath = RealPathUtil.getRealPath(this, imageUri);
        File file = new File(realPath);
        // Print the file size
//        file = compressImage(file);
        long fileSizeInBytes = file.length();
        long fileSizeInKB = fileSizeInBytes / 1024;
        long fileSizeInMB = fileSizeInKB / 1024;
        Log.d("Upload", "File size: " + fileSizeInMB + " MB");

        // Create a RequestBody instance from the file
        RequestBody requestFile = RequestBody.create(
                MediaType.parse(getContentResolver().getType(imageUri)),
                file
        );
        // Create a MultipartBody.Part from the file RequestBody
        MultipartBody.Part body = MultipartBody.Part.createFormData(
                "file",
                file.getName(),
                requestFile
        );

        // Get the API interface
        apiService = RetrofitClient.getRetrofit()
                                   .create(ApiService.class);

        // Call the API
        apiService.createPost(postBody, body)
                  .enqueue(new Callback<BaseResponse<com.banking.thejavabanking.models.entity.Post>>() {
                      @Override
                      public void onResponse(
                              Call<BaseResponse<com.banking.thejavabanking.models.entity.Post>> call,
                              Response<BaseResponse<com.banking.thejavabanking.models.entity.Post>> response
                      ) {
                          if (response.isSuccessful()) {
                              // Handle the response
                              Toast.makeText(
                                           MainActivity.this,
                                           "Upload success",
                                           Toast.LENGTH_SHORT
                                   )
                                   .show();
                          } else {
                              // Handle the error
                              Toast.makeText(
                                           MainActivity.this,
                                           "Upload failed",
                                           Toast.LENGTH_SHORT
                                   )
                                   .show();
                              Log.e("Error2", response.message());
                          }
                      }

                      @Override
                      public void onFailure(
                              Call<BaseResponse<com.banking.thejavabanking.models.entity.Post>> call,
                              Throwable t
                      ) {
                          // Handle the error
                          Toast.makeText(MainActivity.this, "Upload failed", Toast.LENGTH_SHORT)
                               .show();
                          Log.e("Error1", t.getMessage());
                      }
                  });
    }

    private void getAllPostBySort() {
        ApiService apiService = RetrofitClient.getRetrofit()
                                             .create(ApiService.class);

        apiService.getAllPostBySort(1,10)
                  .enqueue(new Callback<BaseResponse<PageResponse<List<PostResponse>>>>() {
                      @Override public void onResponse(
                              Call<BaseResponse<PageResponse<List<PostResponse>>>> call,
                              Response<BaseResponse<PageResponse<List<PostResponse>>>> response
                      ) {
                            if (response.isSuccessful()) {
                                PageResponse<List<PostResponse>> pageResponse = response.body().getData();
                                List<PostResponse> postResponses = pageResponse.getItems();
                                for (PostResponse postResponse : postResponses) {
                                    Log.d("Post", postResponse.getId().toString());
                                }
                            }
                            else{
                                Log.d("Post", "Error" + response.message());
                            }
                      }

                      @Override public void onFailure(
                              Call<BaseResponse<PageResponse<List<PostResponse>>>> call, Throwable t
                      ) {
                            Log.d("Post", "Error" + t.getMessage());
                      }
                  });
    }

}