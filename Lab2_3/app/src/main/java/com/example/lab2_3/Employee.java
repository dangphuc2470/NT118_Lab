package com.example.lab2_3;

import android.util.Log;
import android.widget.Toast;

public abstract class Employee
{
    protected String ID;
    protected String name;

    public abstract double TinhLuong();
    public String toString()
    {
        return ID + " - " + name;
    }

    public void setId(String id) {
        this.ID = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

}

