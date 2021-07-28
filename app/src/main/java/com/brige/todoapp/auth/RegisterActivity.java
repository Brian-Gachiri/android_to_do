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
import com.brige.todoapp.databinding.ActivityRegisterBinding;
import com.brige.todoapp.settings.SharedPrefConfig;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    TextInputEditText inputName, inputNumber, inputPassword, inputConfirm, inputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnLogin = binding.btnLogin;
        Button btnRegister = binding.btnRegister;

        inputConfirm = binding.inputConfirm;
        inputEmail = binding.inputEmail;
        inputName = binding.inputName;
        inputPassword = binding.inputPassword;
        inputNumber = binding.inputNumber;

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

        SharedPrefConfig sharedPrefConfig = new SharedPrefConfig(RegisterActivity.this);
        sharedPrefConfig.setUserInfo(name, email, number, password);

        Toast.makeText(this, "User"+" "+ name+ " "+ "registered successfully.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(RegisterActivity.this, ToDoActivity.class);
        startActivity(intent);
    }

}