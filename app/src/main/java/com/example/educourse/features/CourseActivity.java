package com.example.educourse.features;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.educourse.R;
import com.example.educourse.list.Course;
import com.example.educourse.list.CourseListAdapter;
import com.example.educourse.main.DatabaseHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseActivity extends AppCompatActivity {
    //vars
    private SharedPreferences spCourse;
    private DatabaseHelper myDb;
    private ArrayList<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Course List");
        setContentView(R.layout.activity_course);

        spCourse = getSharedPreferences("LoginCredentials", MODE_PRIVATE);

        myDb = new DatabaseHelper(this);
        courseList = new ArrayList<>();

        courseList = myDb.getAllCourses();
        CourseListAdapter mAdapter = new CourseListAdapter(this, courseList);
        ListView cCourseList = findViewById(R.id.courseList);
        cCourseList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


        cCourseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long cId = id + 1;
                Intent i = new Intent(CourseActivity.this, CourseDetailsActivity.class);

                i.putExtra("CourseId", (String.valueOf(cId)));
                startActivity(i);
            }
        });
    }
}
