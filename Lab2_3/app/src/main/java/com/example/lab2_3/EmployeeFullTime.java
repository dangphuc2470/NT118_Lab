package com.example.lab2_3;

import android.util.Log;

public class EmployeeFullTime extends Employee
{
    @Override
    public double TinhLuong()
    {
        return 500;
    }

    @Override
    public String toString()
    {
        Log.i("Show", ID + " - " + name);
        return super.toString() + " -->FullTime=" + TinhLuong();

    }
}

