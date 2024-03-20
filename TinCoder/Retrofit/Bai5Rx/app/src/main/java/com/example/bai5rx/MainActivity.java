package com.example.bai5rx;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bai5rx.adapter.UserAdapter;
import com.example.bai5rx.api.ApiService;
import com.example.bai5rx.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcvUser;
    List<User> mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
        callApiGetUser();
    }

    private void setUpUI() {
        rcvUser = findViewById(R.id.rv_users);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                this,
                layoutManager.getOrientation()
        );
        rcvUser.addItemDecoration(dividerItemDecoration);

        mUsers = new ArrayList<>();
    }

    public void callApiGetUser() {
        ApiService.apiService
                .getUsers(1)
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (response.isSuccessful()) {
                            mUsers = response.body();
                            UserAdapter adapter = new UserAdapter(mUsers);
                            rcvUser.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(
                                     MainActivity.this,
                                     "Error: " + t.getMessage(),
                                     Toast.LENGTH_SHORT
                             )
                             .show();
                    }
                });
    }
}