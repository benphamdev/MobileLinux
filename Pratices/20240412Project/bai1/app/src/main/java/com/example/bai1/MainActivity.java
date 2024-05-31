package com.example.bai1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.bai1.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding activityMainBinding;
    private ViewPager2Adapter viewPager2Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        setSupportActionBar(activityMainBinding.toolBar);
        FloatingActionButton fab = activityMainBinding.fabAction;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        activityMainBinding.tabLayout.addTab(activityMainBinding.tabLayout.newTab().setText("Xác nhận"));
        activityMainBinding.tabLayout.addTab(activityMainBinding.tabLayout.newTab().setText("Lấy hàng"));
        activityMainBinding.tabLayout.addTab(activityMainBinding.tabLayout.newTab().setText("Đang giao"));
        activityMainBinding.tabLayout.addTab(activityMainBinding.tabLayout.newTab().setText("Đánh giá"));
        activityMainBinding.tabLayout.addTab(activityMainBinding.tabLayout.newTab().setText("Hủy"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager2Adapter = new ViewPager2Adapter(fragmentManager, getLifecycle());
        activityMainBinding.viewPager2.setAdapter(viewPager2Adapter);
        activityMainBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                activityMainBinding.viewPager2.setCurrentItem(tab.getPosition());
                changeFabIcon(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        activityMainBinding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                activityMainBinding.tabLayout.selectTab(activityMainBinding.tabLayout.getTabAt(position));
            }
        });
    }

    private void changeFabIcon(int position) {
        activityMainBinding.fabAction.hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(position == 0){
                    activityMainBinding.fabAction.setImageDrawable(getDrawable(R.drawable.baseline_chat_24));
                } else if (position == 1) {
                    activityMainBinding.fabAction.setImageDrawable(getDrawable(R.drawable.baseline_camera_alt_24));
                } else if (position == 2) {
                     activityMainBinding.fabAction.setImageDrawable(getDrawable(R.drawable.baseline_call_24));
                }
                activityMainBinding.fabAction.show();
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.menuSearch){
            Toast.makeText(this, "Bạn đang chọn search", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuNewGroup) {
            Toast.makeText(this, "Bạn đang chọn newGroup", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuBroadcast) {
            Toast.makeText(this, "Bạn đang chọn Broandcast", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuWeb) {
            Toast.makeText(this, "Bạn đang chọn Web", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.menuMessage){
            Toast.makeText(this, "Bạn đang chọn Message", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuSetting) {
            Toast.makeText(this, "Bạn đang chọn Setting", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}