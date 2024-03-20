package com.example.part4;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.part4.api.ApiService;
import com.example.part4.model.Comment;
import com.example.part4.model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView tvBody, tvPostId, tvUsername;
    Button btnCallApi;
    List<Comment> a = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUI();
//        bai1();
        bai2();
    }

    private void setUpUI() {
        tvBody = findViewById(R.id.tv_body);
        tvPostId = findViewById(R.id.tv_postId);
        tvUsername = findViewById(R.id.tv_username);
        btnCallApi = findViewById(R.id.btn_call_api);
    }

    public void bai1() {
//        Job job = new Job(1, "Developer");
//        List<Favorite> favorites = List.of(
//                new Favorite(1, "Reading"),
//                new Favorite(2, "Traveling")
//        );
//        User user = new User(1, "John", true, job, favorites);
//        Gson gson = new Gson();
//        String json = gson.toJson(user);
//        Log.d("JSON", json);
    }

    public void bai2() {
        btnCallApi.setOnClickListener(v -> {
//            clickCallApi();
            sendPost();
        });
    }

    private void clickCallApi() {
        ApiService.apiService
                .getComment(1)
                .enqueue(new Callback<Comment>() {
                    @Override
                    public void onResponse(
                            Call<Comment> call, Response<Comment> response
                    ) {
                        if (response.isSuccessful()) {
                            Comment comment = response.body();
                            tvBody.setText(comment.getBody());
                            tvPostId.setText(String.valueOf(comment.getPostId()));
                            tvUsername.setText(comment.getUser()
                                                      .getUsername());
                        }
                    }

                    @Override
                    public void onFailure(Call<Comment> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                    }
                });

    }

    private void sendPost() {
        ApiService.apiService
                .createPost(new Post(1, 1, "Title", "Body"))
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(
                            Call<Post> call, Response<Post> response
                    ) {
                        if (response.isSuccessful()) {
                            Post post = response.body();
                            Log.d("Post", post.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                    }
                });
    }

}