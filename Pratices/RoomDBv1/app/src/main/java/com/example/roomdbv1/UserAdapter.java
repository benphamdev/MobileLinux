package com.example.roomdbv1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;

    private IClick iClick;

    public UserAdapter() {}

    public UserAdapter(IClick iClick) {
        this.iClick = iClick;
    }

    public void setData(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType
    ) {
        View view = View.inflate(parent.getContext(), R.layout.item_user, null);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull UserViewHolder holder, int position
    ) {
        User user = userList.get(position);

        if (user == null) return;

        holder.tvUsername.setText(user.getUsername());
        holder.tvAddress.setText(user.getAddress());
        holder.tvDob.setText(user.getDob());
        
        holder.buttonUpdate.setOnClickListener(v -> {
            iClick.updateUser(user);
        });

        holder.buttonDelete.setOnClickListener(v -> {
            iClick.deleteUser(user);
        });
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }

    public interface IClick {
        void updateUser(User user);

        void deleteUser(User user);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUsername;
        private TextView tvAddress;
        private TextView tvDob;
        private Button buttonUpdate;
        private Button buttonDelete;

        public UserViewHolder(
                @NonNull View itemView
        ) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tv_username);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvDob = itemView.findViewById(R.id.tv_dob);

            buttonUpdate = itemView.findViewById(R.id.button_update);
            buttonDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
