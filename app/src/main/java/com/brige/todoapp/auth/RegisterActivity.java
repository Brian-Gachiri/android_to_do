package com.brige.todoapp.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.brige.todoapp.R;
import com.brige.todoapp.ToDoActivity;
import com.brige.todoapp.auth.ui.login.LoginActivity;
import com.brige.todoapp.databinding.ActivityLoginBinding;
import com.brige.todoapp.databinding.ActivityRegisterBinding;
import com.brige.todoapp.settings.SharedPrefConfig;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText inputName, inputNumber, inputPassword, inputConfirm, inputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        Button btnLogin = findViewById(R.id.btn_go_to_login);
        Button btnRegister = findViewById(R.id.btn_register_user);

        inputConfirm = findViewById(R.id.input_confirm);
        inputEmail = findViewById(R.id.input_email);
        inputName = findViewById(R.id.input_name);
        inputPassword = findViewById(R.id.input_password);
        inputNumber = findViewById(R.id.input_number);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Adding function to register a user
                 */
                registerUser();
            }
        });


    }

    private void registerUser() {

        String name, password, confirm, number, email;

        name = inputName.getText().toString().trim();
        password = inputPassword.getText().toString().trim();
        confirm = inputConfirm.getText().toString().trim();
        number = inputNumber.getText().toString().trim();
        email = inputEmail.getText().toString().trim();

        /**
         * Code to add user detail to shared preferences
         */
        SharedPrefConfig sharedPrefConfig = new SharedPrefConfig(RegisterActivity.this);
        sharedPrefConfig.setUserInfo(name, email, number, password);

        //Mark the user as logged in
        sharedPrefConfig.setLoggingInStatus(true);

        Toast.makeText(this, "User"+" "+ name+ " "+ "registered successfully.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(RegisterActivity.this, ToDoActivity.class);
        startActivity(intent);
    }

}