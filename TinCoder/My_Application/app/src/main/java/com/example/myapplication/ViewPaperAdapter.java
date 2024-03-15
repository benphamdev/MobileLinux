package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPaperAdapter extends FragmentStateAdapter {
    public ViewPaperAdapter(
            @NonNull FragmentActivity fragmentActivity
    ) {
        super(fragmentActivity);
    }

    public ViewPaperAdapter(
            @NonNull Fragment fragment
    ) {
        super(fragment);
    }

    public ViewPaperAdapter(
            @NonNull FragmentManager fragmentManager,
            @NonNull Lifecycle lifecycle
    ) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new HomeFragment();
        } else if (position == 1) {
            return new MyPageFragment();
        } else if (position == 2) {
            return new FavoriteFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
