package com.brige.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.brige.todoapp.models.Note;
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

        Note newNote = new Note();








        btnCreateTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newNote.setTitle(editTitle.getText().toString());
                newNote.setDescription(editDetails.getText().toString());


                Toast.makeText(NewToDoActivity.this, newNote.getTitle() , Toast.LENGTH_SHORT).show();


                Snackbar.make(v, newNote.getDescription(), Snackbar.LENGTH_LONG).show();
            }
        });


    }
}