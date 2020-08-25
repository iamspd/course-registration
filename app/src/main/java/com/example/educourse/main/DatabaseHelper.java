package com.example.educourse.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.educourse.list.Course;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Educourse.db";
    private static final String STUDENT_TABLE = "student_table";
    private static final String SCOL_1 = "ID";
    private static final String SCOL_2 = "FIRSTNAME";
    private static final String SCOL_3 = "LASTNAME";
    private static final String SCOL_4 = "EMAIL";
    private static final String SCOL_5 = "PASSWORD";
    private static final String SCOL_6 = "PHONE";

    private static final String COURSE_TABLE = "course_table";
    private static final String CCOL_1 = "ID";
    private static final String CCOL_2 = "NAME";
    private static final String CCOL_3 = "DURATION";
    private static final String CCOL_4 = "DEPARTMENT";
    private static final String CCOL_5 = "TUITION";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + STUDENT_TABLE + " (" + SCOL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SCOL_2 + " TEXT, " + SCOL_3 + " TEXT, " + SCOL_4 + " TEXT, " + SCOL_5 + " TEXT, " +
                SCOL_6 + " TEXT)");

        db.execSQL("CREATE TABLE " + COURSE_TABLE + " (" + CCOL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CCOL_2 + " TEXT, " + CCOL_3 + " TEXT, " + CCOL_4 + " TEXT, " + CCOL_5 + " TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COURSE_TABLE);
        onCreate(db);
    }

    public boolean insertCourseData(String cName, String department,
                                    String duration, String tuition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CCOL_2, cName);
        cv.put(CCOL_3, department);
        cv.put(CCOL_4, duration);
        cv.put(CCOL_5, tuition);

        long result = db.insert(COURSE_TABLE, null, cv);

        return result != -1;
    }

    public boolean insertStudentData(String fName, String lName,
                                     String email, String pwd, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SCOL_2, fName);
        cv.put(SCOL_3, lName);
        cv.put(SCOL_4, email);
        cv.put(SCOL_5, pwd);
        cv.put(SCOL_6, phone);

        long result = db.insert(STUDENT_TABLE, null, cv);

        return result != -1;
    }

    public Cursor viewStudentData(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + STUDENT_TABLE
                + " WHERE " + SCOL_4 + " = ?", new String[]{userName});
    }

    public Cursor viewCourseData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String cId = "0" + id;

        return db.rawQuery("SELECT * FROM " + COURSE_TABLE
                + " WHERE " + CCOL_1 + " = ?", new String[]{cId});
    }

    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + COURSE_TABLE, null);

        while (data.moveToNext()) {
            String name = data.getString(1);
            String department = data.getString(3);

            Course course = new Course(name, department);
            courseList.add(course);
        }
        return courseList;
    }
}
