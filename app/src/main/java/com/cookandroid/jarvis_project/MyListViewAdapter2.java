package com.cookandroid.jarvis_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cookandroid.jarvis_project.database.MyListViewAdapter;
import com.cookandroid.jarvis_project.database.Post;
import com.cookandroid.jarvis_project.database.PostImage;

import java.util.ArrayList;

public class MyListViewAdapter2 extends BaseAdapter {
    ArrayList<PostImage> items;
    Context context;

    public MyListViewAdapter2(ArrayList<PostImage> items, Context context) {
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
        MyListViewAdapter.ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_post1, viewGroup, false);

            holder = new MyListViewAdapter.ViewHolder();
            holder.title = view.findViewById(R.id.title);
            holder.body = view.findViewById(R.id.body);
            view.setTag(holder);
        } else {
            holder = (MyListViewAdapter.ViewHolder) view.getTag();
        }

        PostImage item = items.get(i);
        holder.title.setText(item.getTitle());
        holder.body.setText(item.getBody());

        return view;
    }

    static class ViewHolder {
        TextView title;
        TextView body;
    }
}
