package com.example.lab2_5;

public enum Thumbnail
{
    THUMBNAIL_1("Thumbnail 1", R.drawable.first_thumbnail),
    THUMBNAIL_2("Thumbnail 2", R.drawable.second_thumbnail),
    THUMBNAIL_3("Thumbnail 3", R.drawable.third_thumbnail),
    THUMBNAIL_4("Thumbnail 4", R.drawable.fourth_thumbnail);

    private String name;
    private int img;

    Thumbnail(String name, int img)
    {
        this.name = name;
        this.img = img;
    }

    public String getName()
    {
        return name;
    }

    public int getImage()
    {
        return img;
    }
}
