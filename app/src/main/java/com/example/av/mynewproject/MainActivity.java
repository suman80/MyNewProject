package com.example.av.mynewproject;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView login_button;

    private EditText userEmail;
    private EditText userName;
    private EditText userPassword;

    private AppCompatButton btn_login;

    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));
            getSupportActionBar().setTitle("Login");
            getSupportActionBar().hide();
        }
        login_button=findViewById(R.id.login_button);
        userEmail=findViewById(R.id.user_email);
        userName=findViewById(R.id.user_name);
        userPassword=findViewById(R.id.user_password);
        btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(btn_loginListener);
        login_button.setOnClickListener(loginbuttonListener);
        initObjects();

    }
    private View.OnClickListener loginbuttonListener=new View.OnClickListener() {
        @Override
        public void onClick ( View v ) {
            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    };
    private View.OnClickListener btn_loginListener=new View.OnClickListener() {
        @Override
        public void onClick ( View v ) {
            postDataToSQLite();

        }
    };
    private void initObjects() {

        databaseHelper = new DatabaseHelper(getApplicationContext());
        user = new User();
    }
    private void postDataToSQLite() {

        if (!databaseHelper.checkUser(userEmail.getText().toString().trim())) {

            user.setName(userName.getText().toString().trim());
            user.setEmail(userEmail.getText().toString().trim());
            user.setPassword(userPassword.getText().toString().trim());
            databaseHelper.addUser(user);
            Toast.makeText(this, "Your account is created successfully!", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this, "You are not entering correct data", Toast.LENGTH_SHORT).show();

        }


    }}



