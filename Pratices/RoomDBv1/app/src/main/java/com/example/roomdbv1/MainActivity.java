package com.example.roomdbv1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdbv1.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int MY_REQUEST_CODE = 1;
    EditText etUsername, etAddress, edtSearch, edtDob;
    Button btnAddUser;
    RecyclerView rvUserList;
    UserAdapter userAdapter;
    List<User> userList;
    ActivityResultLauncher<Intent> activityResultLauncher;
    TextView tvDeleteAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();

        userAdapter = new UserAdapter(new UserAdapter.IClick() {
            @Override
            public void updateUser(User user) {
                clickUpdateUser(user);
            }

            @Override public void deleteUser(User user) {
                clickDeleteUser(user);
            }

        });

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            loadData();
                        }
                    }
                }
        );

        userList = new ArrayList<>();
        userAdapter.setData(userList);

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        rvUserList.setLayoutManager(linearLayout);
        rvUserList.setHasFixedSize(true);
        rvUserList.setAdapter(userAdapter);

        btnAddUser.setOnClickListener(v -> addUser());

        tvDeleteAll.setOnClickListener(v -> clickDeleteAll());

        edtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchUser();
            }
            return false;
        });

        loadData();

    }

    private void searchUser() {
        String search = edtSearch.getText()
                                 .toString()
                                 .trim();

        if (TextUtils.isEmpty(search)) {
            loadData();
            return;
        }

        userList = UserDatabase.getInstance(this)
                               .userDAO()
                               .searchUser(search);

        if (userList != null && !userList.isEmpty()) {
            userAdapter.setData(userList);
        } else {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT)
                 .show();
        }

        closeKeyboard();
    }

    private void clickDeleteAll() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete all users")
                .setMessage("Do you want to delete all users?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    UserDatabase.getInstance(this)
                                .userDAO()
                                .deleteAllUsers();
                    Toast.makeText(this, "Delete all users successful", Toast.LENGTH_SHORT)
                         .show();
                    loadData();
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void clickDeleteUser(User user) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete user")
                .setMessage("Do you want to delete this user?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    UserDatabase.getInstance(this)
                                .userDAO()
                                .deleteUser(user);
                    Toast.makeText(this, "Delete successful", Toast.LENGTH_SHORT)
                         .show();
                    loadData();
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void clickUpdateUser(User user) {
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("objectUser", user);
        intent.putExtras(bundle);

        activityResultLauncher.launch(intent);
    }

    private void addUser() {
        String username = etUsername.getText()
                                    .toString()
                                    .trim();
        String address = etAddress.getText()
                                  .toString()
                                  .trim();
        String dob = edtDob.getText()
                           .toString()
                           .trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(address)) {
            etUsername.setError("Please enter username or address");
            return;
        }

        User user = new User(username, address, dob);
        if (isUserExist(user)) {
            Toast.makeText(this, "User is exist", Toast.LENGTH_SHORT)
                 .show();
            return;
        }

        UserDatabase.getInstance(this)
                    .userDAO()
                    .insert(user);

        Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
             .show();

        etAddress.setText("");
        etUsername.setText("");
        edtDob.setText("");

        closeKeyboard();
        loadData();
    }

    private void setUpUI() {
        etUsername = findViewById(R.id.edt_username);
        etAddress = findViewById(R.id.edt_address);
        edtSearch = findViewById(R.id.edt_search);
        edtDob = findViewById(R.id.edt_dob);
        btnAddUser = findViewById(R.id.button);
        tvDeleteAll = findViewById(R.id.tv_delete_all);
        rvUserList = findViewById(R.id.rcv_user);
    }

    private void closeKeyboard() {
        // this will give us the view
        // which is currently focus
        // in this layout
        View view = this.getCurrentFocus();

        // if nothing is currently
        // focus then this will protect
        // the app from crash
        if (view != null) {
            // now assign the system
            // service to InputMethodManager
            InputMethodManager manager =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void loadData() {
        userList = UserDatabase.getInstance(this)
                               .userDAO()
                               .getAllUsers();

        userAdapter.setData(userList);
    }

    private boolean isUserExist(User user) {
        List<User> list = UserDatabase.getInstance(this)
                                      .userDAO()
                                      .checkUser(user.getUsername());

        return list != null && !list.isEmpty();
    }

}