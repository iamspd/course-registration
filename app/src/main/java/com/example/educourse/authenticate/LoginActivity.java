package com.example.educourse.authenticate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.educourse.main.DatabaseHelper;
import com.example.educourse.main.MainActivity;
import com.example.educourse.R;

public class LoginActivity extends AppCompatActivity {

    //widgets
    private EditText lUsername, lPassword;

    //vars
    SharedPreferences spLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.FullScreen);
        setContentView(R.layout.activity_login);

        spLogin = getSharedPreferences("LoginCredentials", MODE_PRIVATE);
        if (spLogin.getString("Username", "").length() > 0){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        findAllViews();
    }

    private void findAllViews() {
        lUsername = findViewById(R.id.etUsername);
        lPassword = findViewById(R.id.etPassword);
    }

    public void authenticateUser(View v){
        String spName = spLogin.getString("Username", "");
        String spPwd = spLogin.getString("Password", "");
        String fName = spLogin.getString("facName", "aa");
        String fPwd = spLogin.getString("facPwd", "aa");

        String userName = lUsername.getText().toString();
        String userPwd = lPassword.getText().toString();

        if (userName.isEmpty() || userPwd.isEmpty()){
            Toast.makeText(this, "Please check your inputs!", Toast.LENGTH_SHORT).show();
        }
        else if(userName.equals(spName) && userPwd.equals(spPwd)){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        else if(userName.equals(fName) && userPwd.equals(fPwd)){
            SharedPreferences.Editor prefEditor = spLogin.edit();
            prefEditor.putString("facUserName", "Faculty");
            prefEditor.apply();

            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        else {
            Toast.makeText(this, "Incorrect Username or Password!", Toast.LENGTH_SHORT).show();
        }
    }

    public void registerNewUser(View v){
        startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
    }
}
