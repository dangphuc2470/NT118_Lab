package com.example.lab2_5;

public class Dish
{
    private String name;
    private Thumbnail thumbnail;

    public Dish(String name, Thumbnail thumbnail)
    {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Thumbnail getThumbnail()
    {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail)
    {
        this.thumbnail = thumbnail;
    }
}
