package com.example.lab2_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomGridViewAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<Dish> dishes;

    public CustomGridViewAdapter(Context context, ArrayList<Dish> dishes)
    {
        this.context = context;
        this.dishes = dishes;
    }


    @Override
    public int getCount()
    {
        return dishes.size();
    }

    @Override
    public Object getItem(int position)
    {
        return dishes.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if (view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.grid_item, parent, false);

        }
        Dish dish = dishes.get(position);
        Thumbnail thumbnail = dish.getThumbnail();
        TextView nameTextView = view.findViewById(R.id.tvNameGrid);
        ImageView imageImageView = view.findViewById(R.id.ivThumbnailsGrid);
        nameTextView.setText(dish.getName());
        imageImageView.setImageResource(thumbnail.getImage());
        ImageView starImgGrid = view.findViewById(R.id.starImgGrid);
        if (dish.isFavorite())
            starImgGrid.setImageResource(R.drawable.star_filled);
        return view;
    }
}
