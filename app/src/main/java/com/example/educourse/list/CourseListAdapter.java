package com.example.educourse.list;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.educourse.R;
import com.example.educourse.main.DatabaseHelper;

import java.util.ArrayList;

public class CourseListAdapter extends BaseAdapter {
    private Context cContext;
    private ArrayList<Course> courseList;

    public CourseListAdapter(Context cContext, ArrayList<Course> courseList) {
        this.cContext = cContext;
        this.courseList = courseList;
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(cContext, R.layout.activity_list_item, null);
        TextView cName = v.findViewById(R.id.tvCourseName);
        cName.setText(courseList.get(position).getcName());

        TextView cDepartment = v.findViewById(R.id.tvCourseDepartment);
        cDepartment.setText(courseList.get(position).getcDepartment());

        return v;
    }
}
