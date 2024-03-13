package com.example.bai131sqllite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database database;
    ListView lsTask;
    ArrayList<Task> tasks;
    TaskAdapter taskAdapter;
    Dialog dialog, dialogUpdate, dialogDelete;
    EditText edAdd, edtNameTask;
    Button btnAdd, btnCancle, btnConfirm, btnCancleUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupUI();
        setupProcess();
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars =
                            insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );
                    return insets;
                }
        );

    }

    private void setupProcess() {
        setupDatabase();
        showData();
    }

    private void setupDatabase() {
        database = new Database(this, "TodoList.sqlite", null, 1);
        database.queryData(
                "CREATE TABLE IF NOT EXISTS Task(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "NameTask VARCHAR(200))");
//        database.queryData("INSERT INTO Task VALUES(null, 'lam bai tap android')");
//        database.queryData("INSERT INTO Task VALUES(null, 'viet ung dung khi chu')");
    }

    public void showData() {
        //        setlect Data
        Cursor dataTask = database.getData("SELECT * FROM Task");
        tasks.clear();
        while (dataTask.moveToNext()) {
            String name = dataTask.getString(1);
            int id = dataTask.getInt(0);
            tasks.add(new Task(id, name));
        }

        taskAdapter.notifyDataSetChanged();
    }

    private void setupUI() {
        lsTask = findViewById(R.id.lv_task);
        tasks = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, R.layout.row_task, tasks);
        lsTask.setAdapter(taskAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_task, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(
            @NonNull MenuItem item
    ) {
        if (item.getItemId() == R.id.menuAdd) {
            addDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_task);

        edAdd = dialog.findViewById(R.id.editText_add_task);
        btnAdd = dialog.findViewById(R.id.button_add);
        btnCancle = dialog.findViewById(R.id.button_cancle);

        solveButton();

        dialog.show();
    }

    public void solveButton() {
        btnCancle.setOnClickListener(v -> {
            dialog.dismiss();
        });

        btnAdd.setOnClickListener(v -> {
            String nameTask = edAdd.getText()
                                   .toString();
            if (nameTask.equals("")) {
                Toast.makeText(this, "Vui long nhap ten cong viec", Toast.LENGTH_SHORT)
                     .show();
            } else {
                database.queryData("INSERT INTO Task VALUES(null, '" + nameTask + "')");
                Toast.makeText(this, "da them", Toast.LENGTH_SHORT)
                     .show();
                dialog.dismiss();
                showData();
            }
        });
    }

    public void updateDialog(String nameTask, int idTask) {
        dialogUpdate = new Dialog(this);
        dialogUpdate.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogUpdate.setContentView(R.layout.dialog_update);

        edtNameTask = dialogUpdate.findViewById(R.id.editText_update);
        btnConfirm = dialogUpdate.findViewById(R.id.btn_confirm);
        btnCancleUpdate = dialogUpdate.findViewById(R.id.button_cancle_update);

        edtNameTask.setText(nameTask);

        btnConfirm.setOnClickListener(v -> {
            String name = edtNameTask.getText()
                                     .toString();
            if (name.equals("")) {
                Toast.makeText(this, "Vui long nhap ten cong viec", Toast.LENGTH_SHORT)
                     .show();
            } else {
                database.queryData("UPDATE Task SET NameTask = '" + name + "'WHERE Id = '" + idTask + "'");
                Toast.makeText(this, "Da cap nhat", Toast.LENGTH_SHORT)
                     .show();
                dialogUpdate.dismiss();
                showData();
            }
        });
        btnCancleUpdate.setOnClickListener(v -> {
            dialogUpdate.dismiss();
        });
        dialogUpdate.show();
    }

    public void deleteTask(int idTask) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(this);
        dialogDelete.setMessage("Ban co muon xoa cong viec nay khong");

        dialogDelete.setPositiveButton("Co", (dialog, which) -> {
            database.queryData("DELETE FROM Task WHERE Id = '" + idTask + "'");
            showData();
        });

        dialogDelete.setNegativeButton("Khong", (dialog, which) -> {

        });

        dialogDelete.show();
    }
}