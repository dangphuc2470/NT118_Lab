package com.dnhp.lab4_2_quanlysinhvien;

public class Student
{
    private int studentId;
    private String fullName;
    private int yearOfBirth;
    private String className;

    public Student(int studentId, String fullName, int yearOfBirth, String className)
    {
        this.studentId = studentId;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.className = className;
    }

    public int getStudentId()
    {
        return studentId;
    }

    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public int getYearOfBirth()
    {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth)
    {
        this.yearOfBirth = yearOfBirth;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }
}
