package com.cookandroid.jarvis_project.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cookandroid.jarvis_project.R;

import java.util.ArrayList;

public class MyListViewAdapter extends BaseAdapter {
    ArrayList<Post> items;
    Context context;

    public MyListViewAdapter(ArrayList<Post> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_post1, viewGroup, false);

            holder = new ViewHolder();
            holder.title = view.findViewById(R.id.title);
            holder.body = view.findViewById(R.id.body);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Post item = items.get(i);
        holder.title.setText(item.title);
        holder.body.setText(item.body);

        return view;
    }

    public static class ViewHolder {
        public TextView title;
        public TextView body;
    }
}
