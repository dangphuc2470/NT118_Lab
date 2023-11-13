package com.example.lab2_5;

public class Employee {
    protected String ID;
    protected String name;
    protected boolean isManager;

    public Employee(boolean b)
    {
        isManager = b;
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
}
