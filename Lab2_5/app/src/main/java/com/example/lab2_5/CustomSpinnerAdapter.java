package com.example.lab2_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<Thumbnail> thumbnails;

    public CustomSpinnerAdapter(Context context, ArrayList<Thumbnail> thumbnails)
    {
        this.context = context;
        this.thumbnails = thumbnails;
    }


    @Override
    public int getCount()
    {
        return thumbnails.size();
    }

    @Override
    public Object getItem(int position)
    {
        return thumbnails.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.spinner_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.ivSpinner);
        TextView textView = convertView.findViewById(R.id.tvSpinner);

        Thumbnail thumbnail = thumbnails.get(position);

        imageView.setImageResource(thumbnail.getImage());
        textView.setText(thumbnail.getName());

        return convertView;
    }
}
