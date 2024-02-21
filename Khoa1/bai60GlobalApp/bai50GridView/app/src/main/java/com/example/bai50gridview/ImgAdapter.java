package com.example.bai50gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImgAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<Image> listImg;
    private final int layout;

    public ImgAdapter(Context context, int layout, ArrayList<Image> listImg) {
        this.context = context;
        this.listImg = listImg;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return listImg.size();
    }

    @Override
    public Object getItem(int position) {
        return listImg.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Image image = listImg.get(position);
        viewHolder.img.setImageResource(image.getImg());
        return convertView;
    }

    private class ViewHolder {
        private final ImageView img;

        public ViewHolder(View view) {
            img = view.findViewById(R.id.imgView);
        }
    }
}
