package com.example.bai131sqllite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends BaseAdapter {
    private final MainActivity context;
    private final int layout;
    private final List<Task> taskList;

    public TaskAdapter(MainActivity context, int layout, List<Task> taskList) {
        this.context = context;
        this.layout = layout;
        this.taskList = taskList;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                                        .inflate(layout, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Task task = taskList.get(position);
        viewHolder.txtName.setText(task.getName());

        viewHolder.imgNote.setOnClickListener(v -> {
            context.updateDialog(task.getName(), task.getId());
        });

        viewHolder.imgDelete.setOnClickListener(v -> {
            context.deleteTask(task.getId());
        });

        return convertView;
    }

    private class ViewHolder {
        TextView txtName;
        ImageView imgDelete, imgNote;

        public ViewHolder(View view) {
            this.txtName = view.findViewById(R.id.textView_task);
            this.imgDelete = view.findViewById(R.id.image_delete);
            this.imgNote = view.findViewById(R.id.image_note);
        }
    }
}
