package com.example.bai66popupmenu;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    Button btnPopupMenu, btnSetBackground;

    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        process();
    }

    public void init() {
        btnPopupMenu = findViewById(R.id.button_popup_menu);
        btnSetBackground = findViewById(R.id.button_context_menu);
        constraintLayout = findViewById(R.id.constraint_layout);
    }

    public void process() {
        bai66();
        bai67();
    }

    public void bai67() {
        registerForContextMenu(btnSetBackground);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);

        menu.setHeaderIcon(R.drawable.ic_launcher_foreground);
        menu.setHeaderTitle("Select background colour");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int temp = item.getItemId(), colour = 0;

        if (temp == R.id.menu_green) {
            colour = R.color.green;
        } else if (temp == R.id.menu_red) {
            colour = R.color.red;
        } else if (temp == R.id.menu_yellow) {
            colour = R.color.yellow;
        }

        constraintLayout.setBackgroundResource(colour);
        return super.onContextItemSelected(item);
    }

    public void bai66() {
        btnPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu();
            }
        });
    }

    public void showMenu() {
        PopupMenu popupMenu = new PopupMenu(this, btnPopupMenu);

        popupMenu.getMenuInflater()
                 .inflate(R.menu.menu_popup, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int temp = item.getItemId();
                String s = "";
                if (temp == R.id.menu_create) {
                    s = "CREATE";
                } else if (temp == R.id.menu_read) {
                    s = "READ";
                } else if (temp == R.id.menu_update) {
                    s = "UPDATE";
                } else if (temp == R.id.menu_delete) {
                    s = "DELETE";
                }
                btnPopupMenu.setText(s);

//                Toast.makeText(MainActivity.this, "ban chon" + item.getTitle(), Toast.LENGTH_SHORT)
//                     .show();
                return false;
            }
        });

        popupMenu.show();
    }
}