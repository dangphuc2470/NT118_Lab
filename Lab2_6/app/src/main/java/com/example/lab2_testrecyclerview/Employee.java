package com.example.lab2_testrecyclerview;

import android.util.Log;

public class Employee {
    protected String ID;
    protected String name;
    protected boolean isManager;

    public Employee(boolean b)
    {
        isManager = b;
    }

    public Employee(String ID, String name, boolean isManager)
    {
        this.ID = ID;
        this.name = name;
        this.isManager = isManager;
    }

    public void setId(String id) {
        this.ID = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isManager()
    {
        return isManager;
    }

    public String getRole()
    {
        if (!isManager)
            return "Staff";
        else
            return "Manager";
    }
}
