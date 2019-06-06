package com.example.av.mynewproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity{
    private TextView signup_button;
    private DatabaseHelper databaseHelper;
    private User user;
    private EditText username,password;
    private AppCompatButton btn_login_button;



    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));
            getSupportActionBar().setTitle("Login");
            getSupportActionBar().hide();
        }
        databaseHelper = new DatabaseHelper(getApplicationContext());
        signup_button=findViewById(R.id.signup_button);
        signup_button.setOnClickListener(signup_buttonListener);
        username=findViewById(R.id.input_email);
        password=findViewById(R.id.input_password);
        btn_login_button=findViewById(R.id.btn_login_button);
        btn_login_button.setOnClickListener(btn_login_buttonListener);
    }
    private View.OnClickListener signup_buttonListener=new View.OnClickListener() {
        @Override
        public void onClick ( View v ) {

            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    };
    private View.OnClickListener btn_login_buttonListener=new View.OnClickListener() {
        @Override
        public void onClick ( View v ) {

            if(databaseHelper.checkUser(username.getText().toString().trim()
                    , password.getText().toString().trim())) {

                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "Your Username/Password is incorrect", Toast.LENGTH_SHORT).show();
            }

        }
    };
}
