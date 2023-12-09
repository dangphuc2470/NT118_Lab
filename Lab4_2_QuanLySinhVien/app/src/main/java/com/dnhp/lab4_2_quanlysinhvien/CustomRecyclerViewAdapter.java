package com.dnhp.lab4_2_quanlysinhvien;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>
{


    static ArrayList<Student> arr;
    private CustomRecyclerViewAdapter.OnItemClickListener listener;

    public CustomRecyclerViewAdapter(ArrayList<Student> dataSet, CustomRecyclerViewAdapter.OnItemClickListener listener)
    {
        arr = dataSet;
        this.listener = listener;
    }

    public static void updateData(Student e)
    {
        arr.add(e);
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        private final TextView tvID;
        private final TextView tvName;
        private final TextView tvClass;
        private final ImageView imageView;
        private final LinearLayout constraintLayout;

        public ViewHolder(View view)
        {
            super(view);
            // Define click listener for the ViewHolder's View

            //textView = (TextView) view.findViewById(R.id.textViewRS);
            //button = view.findViewById(R.id.buttonRS);
            tvID = view.findViewById(R.id.tvID_item);
            tvName = view.findViewById(R.id.tvName_item);
            tvClass = view.findViewById(R.id.tvClass_item);
            imageView = view.findViewById(R.id.iv_item);
            constraintLayout = view.findViewById(R.id.constraint_item);
        }

        public TextView getTvID()
        {
            return tvID;
        }

        public TextView getTvName()
        {
            return tvName;
        }

        public TextView getTvClass()
        {
            return tvClass;
        }


        public ImageView getImageView()
        {
            return imageView;
        }

        public LinearLayout getConstraintLayout()
        {
            return constraintLayout;
        }


    }


    /**
     * Initialize the dataset of the Adapter.
     * <p>
     * param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position)
    {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Student Student = arr.get(position);


        viewHolder.getTvID().setText(Student.getStudentId());
        viewHolder.getTvName().setText(Student.getFullName());
        viewHolder.getTvClass().setText(Student.getClassName());


        Log.i("ABC", "onBindViewHolder: " + position);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return arr.size();
    }

    // Interface for item click listener
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
