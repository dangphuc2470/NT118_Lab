package com.example.lab2_testrecyclerview;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>
{

    static ArrayList<Employee> arr;
    public CustomRecyclerViewAdapter(ArrayList<Employee> dataSet)
    {
        arr = dataSet;
    }

    public static void updateData(Employee e)
    {
        arr.add(e);
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        private final TextView tvName;
        private final TextView tvRole;
        private final ImageView imageView;
        private final LinearLayout linearLayout;

        public ViewHolder(View view)
        {
            super(view);
            // Define click listener for the ViewHolder's View

            //textView = (TextView) view.findViewById(R.id.textViewRS);
            //button = view.findViewById(R.id.buttonRS);
            tvName = view.findViewById(R.id.item_employee_tv_fullname);
            tvRole = view.findViewById(R.id.item_employee_tv_position);
            imageView = view.findViewById(R.id.item_employee_iv_manager);
            linearLayout = view.findViewById(R.id.item_employee_ll_parent);

        }



        public TextView getTextViewRole()
        {
            return tvRole;
        }

        public TextView getTextViewName()
        {
            return tvName;
        }

        public ImageView getImageView()
        {
            return imageView;
        }

        public View getLinearLayoutParent()
        {
            return linearLayout;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView.
     */

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position)
    {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Employee employee = arr.get(position);


        viewHolder.getTextViewRole().setText(employee.getRole());
        viewHolder.getImageView().setImageResource(R.drawable.ic_manager);

        if (employee.getName()!=null) {
            viewHolder.getTextViewName().setText(employee.getName());
        }
        else viewHolder.getTextViewName().setText("");
        // If this is a manager -> show icon manager. Otherwise, show Staff in tvPosition
        if (employee.isManager())
        {
            viewHolder.getImageView().setVisibility(View.VISIBLE);
            viewHolder.getTextViewRole().setVisibility(View.GONE);
        }
        else
        {
            viewHolder.getImageView().setVisibility(View.GONE);
            viewHolder.getTextViewRole().setVisibility(View.VISIBLE);
            viewHolder.getTextViewRole().setText("staff");
        }

        if (position%2==0)
        {
            viewHolder.getLinearLayoutParent().setBackgroundResource(R.color.white);
        }
        else
        {
            viewHolder.linearLayout.setBackgroundResource(R.color.light_blue);
        }


        Log.i("ABC", "onBindViewHolder: " + position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return arr.size();
    }
}
