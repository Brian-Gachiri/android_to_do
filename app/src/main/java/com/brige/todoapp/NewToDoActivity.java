package com.brige.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.Date;

import io.objectbox.Box;

public class NewToDoActivity extends AppCompatActivity {


    EditText editTitle;
    private Box<Note> notesBox;
    TextInputEditText editDetails, editSubtasks;
    Note newNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_new_to_do);

        notesBox = ObjectBox.get().boxFor(Note.class);



        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnCreateTasks = findViewById(R.id.btnCreateTask);
        ImageButton btnAddSubtask = findViewById(R.id.btnAddSubtask);

        ImageView firstFile = findViewById(R.id.imgFirstFile);
        ImageView secondFile = findViewById(R.id.imgSecondFile);

        editTitle = findViewById(R.id.editTitle);
        editDetails = findViewById(R.id.editDetails);
        editSubtasks = findViewById(R.id.editSubtasks);


        btnCancel.setOnClickListener(v ->{

            onBackPressed();
            finish();

        });


        btnCreateTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newNote.setTitle(editTitle.getText().toString());
                newNote.setDescription(editDetails.getText().toString());

                long id = notesBox.put(newNote); //creates a new note in the database



                Intent intent = new Intent(NewToDoActivity.this, ToDoDetailsActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
                finish();





            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent().hasExtra("ID")){

            newNote = notesBox.get(getIntent().getLongExtra("ID", 0));

            editTitle.setText(newNote.getTitle());
            editDetails.setText(newNote.getDescription());
            newNote.setUpdated_at(new Date().toString());


        }
        else{
            newNote = new Note();
            newNote.setCreated_at(new Date().toString());
            newNote.setUpdated_at(new Date().toString());

        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (editTitle.getText().toString().trim().isEmpty()){

            Toast.makeText(this, "Nothing to save", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Note saved to draft", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}