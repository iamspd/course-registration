package com.example.educourse.authenticate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.educourse.R;
import com.example.educourse.main.DatabaseHelper;

public class RegistrationActivity extends AppCompatActivity {

    //widgets
    private EditText rFname, rLname, rUsername, rPassword, rPhone;
    private DatabaseHelper myDb;
    private Button registerUser;

    //vars
    SharedPreferences spSignUp;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.FullScreen);
        setContentView(R.layout.activity_registration);

        myDb = new DatabaseHelper(this);
        findAllViews();
        spSignUp = getSharedPreferences("LoginCredentials", MODE_PRIVATE);
        userName = spSignUp.getString("Username", "");

        if (userName.length() > 0){
            rFname.setFocusable(false);
            rLname.setFocusable(false);
            rUsername.setFocusable(false);
            rPassword.setFocusable(false);
            rPhone.setFocusable(false);
            registerUser.setVisibility(View.GONE);
            myProfile();
        }
    }

    private void findAllViews() {
        rFname = findViewById(R.id.etFname);
        rLname = findViewById(R.id.etLname);
        rUsername = findViewById(R.id.etEmail);
        rPassword = findViewById(R.id.etPassword);
        rPhone = findViewById(R.id.etPhone);
        registerUser = findViewById(R.id.btnSignup);
    }

    public void registerUser(View v){
        String fName = rFname.getText().toString();
        String lName = rLname.getText().toString();
        String userName = rUsername.getText().toString();
        String password = rPassword.getText().toString();
        String phone = rPhone.getText().toString();

        if (fName.isEmpty() && lName.isEmpty() && userName.isEmpty()
                && password.isEmpty() && phone.isEmpty()){
            Toast.makeText(this, "Please check your inputs!", Toast.LENGTH_SHORT).show();
        }
        else {

            boolean isInserted = myDb.insertStudentData(fName,lName, userName, password, phone);
            if (isInserted){
                Toast.makeText(this, "You're " +
                        "successfully registered", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor prefEditor = spSignUp.edit();
                prefEditor.putString("Username", userName);
                prefEditor.putString("Password", password);
                prefEditor.putString("Name", fName);
                prefEditor.putString("facName", "aa");
                prefEditor.putString("facPwd", "aa");
                prefEditor.apply();

                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

            }else {
                Toast.makeText(this, "Something went wrong. " +
                        "Try Again!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void myProfile(){
        if (userName.length() > 0) {
            Cursor data = myDb.viewStudentData(userName);
            if (data.moveToNext()) {
                rFname.setText(data.getString(1));
                rLname.setText(data.getString(2));
                rUsername.setText(data.getString(3));
                rPassword.setText(data.getString(4));
                rPhone.setText(data.getString(5));
            }
        }
    }
}
