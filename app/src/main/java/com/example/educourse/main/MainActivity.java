package com.example.educourse.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.educourse.R;
import com.example.educourse.authenticate.LoginActivity;
import com.example.educourse.authenticate.RegistrationActivity;
import com.example.educourse.features.AddCourseActivity;
import com.example.educourse.features.CourseActivity;
import com.example.educourse.features.LocationActivity;

public class MainActivity extends AppCompatActivity {

    //widgets
    private TextView mName;
    private Button mCourse, mAddCourse;

    //vars
    private SharedPreferences spMain;
    private DatabaseHelper myDb;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.FullScreen);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        mName = findViewById(R.id.tvName);
        mCourse = findViewById(R.id.btnCourses);
        mAddCourse = findViewById(R.id.btnAddCourses);

        spMain = getSharedPreferences("LoginCredentials", MODE_PRIVATE);
        mName.setText(spMain.getString("Name", ""));
        String facName = spMain.getString("facUserName", "");
        if (facName.length() > 0){
            mName.setText(facName);
        };
        userName = spMain.getString("Username", "");


        if (spMain.getString("fName", "").length() > 0) {
            mCourse.setVisibility(View.GONE);
        }

        if (userName.length() > 0) {
            mAddCourse.setVisibility(View.GONE);
        }
    }

    public void viewLocation(View v) {
        startActivity(new Intent(getApplicationContext(), LocationActivity.class));
    }

    public void viewCourses(View v) {
        startActivity(new Intent(getApplicationContext(), CourseActivity.class));
    }

    public void viewLink(View v) {
        String url = "https://www.centennialcollege.ca/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }
    }

    public void addCourses(View v) {
        startActivity(new Intent(getApplicationContext(), AddCourseActivity.class));
    }

    public void logtheUserout(View v) {
        SharedPreferences.Editor prefEditor = spMain.edit();
        prefEditor.remove("facUserName");
        prefEditor.apply();

        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    public void userProfile(View v) {
        startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
    }
}
