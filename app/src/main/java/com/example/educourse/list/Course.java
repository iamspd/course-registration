package com.example.educourse.list;

public class Course {
    private String cName;
    private String cDepartment;

    public Course(String cName, String cDepartment) {
        this.cName = cName;
        this.cDepartment = cDepartment;
    }

    public String getcName() {
        return cName;
    }

    public String getcDepartment() {
        return cDepartment;
    }
}
