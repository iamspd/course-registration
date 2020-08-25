package com.example.educourse.features;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.educourse.R;
import com.example.educourse.main.DatabaseHelper;

public class CourseDetailsActivity extends AppCompatActivity {

    //widgets
    private EditText courseName, courseDuration, courseDepartment, courseTuition;

    //vars
    private DatabaseHelper myDb;
    private SharedPreferences spCourse;
    String courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Course Details");
        setContentView(R.layout.activity_course_details);

        spCourse = getSharedPreferences("LoginCredentials", MODE_PRIVATE);

        findAllViews();
        Intent i = getIntent();
        if (i != null){
            courseId = i.getStringExtra("CourseId");
        }
        myDb = new DatabaseHelper(this);
        showCourseDetails();
    }

    private void findAllViews() {
        courseName = findViewById(R.id.etCourseName);
        courseDuration = findViewById(R.id.etDuration);
        courseDepartment = findViewById(R.id.etDepartment);
        courseTuition = findViewById(R.id.etTuition);
    }

    public void showCourseDetails() {
        if (courseId.length() > 0) {
            Cursor data = myDb.viewCourseData(Integer.parseInt(courseId));
                if (data.moveToNext()) {
                    courseName.setText(data.getString(1));
                    courseDuration.setText(data.getString(2));
                    courseDepartment.setText(data.getString(3));
                    courseTuition.setText(data.getString(4));
                }
        }
    }

    public void registerCourse(View v) {
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:"));
        i.putExtra(Intent.EXTRA_SUBJECT, "Registration Details");
        i.putExtra(Intent.EXTRA_TEXT, showMessage());

        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }
    }

    public String showMessage() {
        String userName = spCourse.getString("Name", "");
        String couseName = spCourse.getString("Course", "");
        return userName + " \n"
                + couseName + " \n"
                + "Registration successfully completed! ";
    }
}
