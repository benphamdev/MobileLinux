package com.example.bai4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageCapture;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;



public class MainActivity extends AppCompatActivity {
    Button btnChooseImage;
    ImageView imgPhoto;
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
        // Registers a photo picker activity launcher in single-select mode.
        pickMedia=
                registerForActivityResult(new PickVisualMedia(), uri -> {
                    // Callback is invoked after the user selects a media item or closes the
                    // photo picker.
                    if (uri != null) {
                        Glide.with(this).load(uri).into(imgPhoto);
                        Log.d("PhotoPicker", "Selected URI: " + uri);
                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                });
        chooseImage();

    }

    private void setUpUI() {
        btnChooseImage = findViewById(R.id.btn_choose_image);
        imgPhoto = findViewById(R.id.img_photo);
    }

    public void chooseImage() {
        btnChooseImage.setOnClickListener(v -> {
//            requetPermission();
            openImagePicker();
        });
    }


    public void openImagePicker() {
        // Include only one of the following calls to launch(), depending on the types
        // of media that you want to let the user choose from.

        // Launch the photo picker and let the user choose images and videos.
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                                 .setMediaType(PickVisualMedia.ImageAndVideo.INSTANCE)
                                 .build());

        // Launch the photo picker and let the user choose only images.
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                                 .setMediaType(PickVisualMedia.ImageOnly.INSTANCE)
                                 .build());

        // Launch the photo picker and let the user choose only videos.
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                                 .setMediaType(PickVisualMedia.VideoOnly.INSTANCE)
                                 .build());

        // Launch the photo picker and let the user choose only images/videos of a
        // specific MIME type, such as GIFs.
        String mimeType = "image/gif";
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                                 .setMediaType(new PickVisualMedia.SingleMimeType(mimeType))
                                 .build());

//        ImageCapture imageCapture =
//                new ImageCapture.Builder()
//                        .setTargetRotation(view.getDisplay().getRotation())
//                        .build();
//
//        cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, imageCapture, imageAnalysis, preview);

    }

}