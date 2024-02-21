package com.example.bai45listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends BaseAdapter {

    private final Context context;
    private final int layout;
    private final List<Fruit> fruitList;

    public FruitAdapter(Context context, int layout, List<Fruit> fruitList) {
        this.context = context;
        this.layout = layout;
        this.fruitList = fruitList;
    }

    @Override
    public int getCount() {
        return fruitList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        if (convertView == null) {
////            LayoutInflater layoutInflater =
////                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////
////        convertView = layoutInflater.inflate(layout, null);
//
//            convertView = LayoutInflater.from(context.getApplicationContext())
//                    .inflate(layout, null);
//        }
//
//        // anh xa
//        TextView txtFullName = convertView.findViewById(R.id.tvName),
//                txtDes = convertView.findViewById(R.id.tvDes);
//        ImageView imgTmp = convertView.findViewById(R.id.imgView);
//
//        // gan gia tri
//        Fruit fruit = fruitList.get(position);
//        txtFullName.setText(fruit.getNameFruit());
//        txtDes.setText(fruit.getDescription());
//        imgTmp.setImageResource(fruit.getImgFruit());
//
//        return convertView;
//    }

    // optimize
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

        // gan gia tri
        Fruit fruit = fruitList.get(position);
        viewHolder.txtFullName.setText(fruit.getNameFruit());
        viewHolder.txtDes.setText(fruit.getDescription());
        viewHolder.imgView.setImageResource(fruit.getImgFruit());

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_scale);
        convertView.startAnimation(animation);
        return convertView;
    }

    private class ViewHolder {
        TextView txtFullName, txtDes;
        ImageView imgView;

        public ViewHolder(View view) {
            txtFullName = view.findViewById(R.id.tvName);
            txtDes = view.findViewById(R.id.tvDes);
            imgView = view.findViewById(R.id.imgView);
        }
    }
}
