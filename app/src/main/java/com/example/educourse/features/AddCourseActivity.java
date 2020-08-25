package com.example.educourse.features;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.educourse.R;
import com.example.educourse.authenticate.LoginActivity;
import com.example.educourse.main.DatabaseHelper;
import com.example.educourse.main.MainActivity;

public class AddCourseActivity extends AppCompatActivity {

    //widgets
    private EditText courseName, courseDuration, courseDepartment, courseTuition;

    //vars
    private DatabaseHelper myDb;
    private SharedPreferences spCourse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add New Course");
        setContentView(R.layout.activity_add_course);

        findAllViews();
        spCourse = getSharedPreferences("LoginCredentials", MODE_PRIVATE);
        myDb = new DatabaseHelper(this);

    }

    private void findAllViews() {
        courseName = findViewById(R.id.etCourseName);
        courseDuration = findViewById(R.id.etDuration);
        courseDepartment = findViewById(R.id.etDepartment);
        courseTuition = findViewById(R.id.etTuition);
    }

    public void addCourse(View v) {
        String cName = courseName.getText().toString();
        String cDuration = courseDuration.getText().toString();
        String cDepartment = courseDepartment.getText().toString();
        String cTuition = courseTuition.getText().toString();

        if (cName.isEmpty() || cDuration.isEmpty() || cDepartment.isEmpty()
                || cTuition.isEmpty()) {
            Toast.makeText(this, "Please check your inputs!", Toast.LENGTH_SHORT).show();
        } else {

            boolean isInserted = myDb.insertCourseData(cName, cDuration, cDepartment, cTuition);
            if (isInserted) {
                Toast.makeText(this, "Course " +
                        "successfully added", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor prefEditor = spCourse.edit();
                prefEditor.putString("Course", cName);
                prefEditor.apply();

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

            } else {
                Toast.makeText(this, "Something went wrong. " +
                        "Try Again!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
