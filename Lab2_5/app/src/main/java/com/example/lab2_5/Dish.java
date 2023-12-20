package com.example.lab2_5;

public class Dish
{
    private String name;
    private Thumbnail thumbnail;
    private boolean isFavorite = false;

    public Dish(String name, Thumbnail thumbnail, boolean isFavorite)
    {
        this.name = name;
        this.thumbnail = thumbnail;
        this.isFavorite = isFavorite;
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

    public boolean isFavorite()
    {
        return isFavorite;
    }

    public void setFavorite(boolean favorite)
    {
        isFavorite = favorite;
    }
}
