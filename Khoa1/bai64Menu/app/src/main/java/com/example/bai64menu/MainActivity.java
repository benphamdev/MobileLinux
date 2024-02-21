package com.example.bai64menu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int temp = item.getItemId();
        if (temp == R.id.menu_share) {
            Toast.makeText(this, "ban chon share", Toast.LENGTH_SHORT)
                 .show();
        } else if (temp == R.id.menu_search) {
            Toast.makeText(this, "ban chon search", Toast.LENGTH_SHORT)
                 .show();
        } else if (temp == R.id.menu_account) {
            Toast.makeText(this, "ban chon account", Toast.LENGTH_SHORT)
                 .show();
        } else if (temp == R.id.menu_logout) {
            Toast.makeText(this, "ban chon logout", Toast.LENGTH_SHORT)
                 .show();
        } else if (temp == R.id.menu_switch) {
            Toast.makeText(this, "ban chon switch account", Toast.LENGTH_SHORT)
                 .show();
        } else if (temp == R.id.menu_exit) {
            Toast.makeText(this, "ban chon exit", Toast.LENGTH_SHORT)
                 .show();
        }
//        Toast.makeText(this, "Select" + item.getTitle(), Toast.LENGTH_SHORT)
//             .show();
        return super.onOptionsItemSelected(item);
    }
}