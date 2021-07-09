package com.brige.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class NewToDoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_do);

        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnCreateTasks = findViewById(R.id.btnCreateTask);
        ImageButton btnAddSubtask = findViewById(R.id.btnAddSubtask);

        ImageView firstFile = findViewById(R.id.imgFirstFile);
        ImageView secondFile = findViewById(R.id.imgSecondFile);

        EditText editTitle = findViewById(R.id.editTitle);
        TextInputEditText editDetails = findViewById(R.id.editDetails);
        TextInputEditText editSubtasks = findViewById(R.id.editSubtasks);




        btnCreateTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTitle.getText().toString();
                String details = editDetails.getText().toString();

                Toast.makeText(NewToDoActivity.this, title , Toast.LENGTH_SHORT).show();

                Snackbar.make(v, details, Snackbar.LENGTH_LONG).show();
            }
        });


    }
}