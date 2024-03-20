package com.example.bai5rx.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bai5rx.R;
import com.example.bai5rx.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    List<User> mUsers;

    public UserAdapter(List<User> users) {
        this.mUsers = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType
    ) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_user, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull UserViewHolder holder, int position
    ) {
        User user = mUsers.get(position);
        if (user == null) {
            return;
        }
        holder.tvId.setText(String.valueOf(user.getId()));
        holder.tvTitle.setText(user.getTitle());
    }

    @Override
    public int getItemCount() {
        if (mUsers != null)
            return mUsers.size();
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvId;
        private final TextView tvTitle;

        public UserViewHolder(
                @NonNull View itemView
        ) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
