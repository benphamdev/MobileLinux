package com.example.bai137v2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ItemObject> listItems;

    public ItemAdapter() {}

    public ItemAdapter(Context context, int layout, List<ItemObject> listItems) {
        this.context = context;
        this.layout = layout;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
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
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ItemObject itemObject = listItems.get(position);
        holder.txtNameItem.setText(itemObject.getNameItem());
        holder.txtDesc.setText(itemObject.getDescription());

        // Chuyển byte[] -> bitmap -> imageView
        byte[] image = itemObject.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.imgItem.setImageBitmap(bitmap);
        return convertView;
    }

    private class ViewHolder {
        // Khai báo các view cần hiển thị
        ImageView imgItem;
        TextView txtNameItem, txtDesc;

        public ViewHolder(View view) {
            this.imgItem = view.findViewById(R.id.row_iv_icon);
            this.txtNameItem = view.findViewById(R.id.row_tv_name);
            this.txtDesc = view.findViewById(R.id.row_tv_description);
        }
    }
}
