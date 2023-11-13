package com.example.lab2_4;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lab2_4.Employee;
import com.example.lab2_4.R;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<Employee>
{
    private Activity context;
    public EmployeeAdapter(Activity context, int layoutID, List<Employee>
            objects) {
        super(context, layoutID, objects);
        this.context = context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            convertView =
                    LayoutInflater.from(context).inflate(R.layout.item_employee, null,
                            false);
        }
        // Get item
        Employee employee = getItem(position);
        // Get view
        TextView tvFullName =
                convertView.findViewById(R.id.item_employee_tv_fullname);
        TextView tvPosition =
                convertView.findViewById(R.id.item_employee_tv_position);
        ImageView ivManager =
                convertView.findViewById(R.id.item_employee_iv_manager);
        LinearLayout llParent =
                convertView.findViewById(R.id.item_employee_ll_parent);
        // Set fullname
        if (employee.getName()!=null) {
            tvFullName.setText(employee.getName());
        }
        else tvFullName.setText("");
        // If this is a manager -> show icon manager. Otherwise, show Staff in tvPosition
        if (employee.isManager())
        {
            ivManager.setVisibility(View.VISIBLE);
            tvPosition.setVisibility(View.GONE);
        }
        else
        {
            ivManager.setVisibility(View.GONE);
            tvPosition.setVisibility(View.VISIBLE);
            tvPosition.setText(context.getString(R.string.staff));
        }
        // Show different color backgrounds for 2 continuous employees
        if (position%2==0)
        {
            llParent.setBackgroundResource(R.color.white);
        }
        else
        {
            llParent.setBackgroundResource(R.color.light_blue);
        }
        return convertView;
    }
}
