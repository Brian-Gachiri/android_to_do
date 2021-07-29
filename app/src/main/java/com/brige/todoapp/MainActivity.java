package com.brige.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.brige.todoapp.auth.ui.login.LoginActivity;
import com.brige.todoapp.settings.SharedPrefConfig;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton mainButton = findViewById(R.id.btn_main);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPrefConfig myPreferenceStorage = new SharedPrefConfig(MainActivity.this);

                if(myPreferenceStorage.isLoggedIn()){

                    Intent intent = new Intent(MainActivity.this, ToDoActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        });
    }
}