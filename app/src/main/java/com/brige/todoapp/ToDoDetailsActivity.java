package com.brige.todoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.brige.todoapp.models.Note;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.objectbox.Box;

public class ToDoDetailsActivity extends AppCompatActivity {

    CollapsingToolbarLayout toolBarLayout;
    TextView txtDetails;
    private Box<Note> notesBox;
    long idToUse = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_details);

        notesBox = ObjectBox.get().boxFor(Note.class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        Button btnComplete = findViewById(R.id.btn_complete);
        Button btnDelete = findViewById(R.id.btn_delete);
        txtDetails = findViewById(R.id.txtDetails);


        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Task Added Successfully", Snackbar.LENGTH_LONG)
                        .setAction("Undo", null).show();
                Intent intent = new Intent(ToDoDetailsActivity.this, ToDoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnDelete.setOnClickListener(v -> {
            //TODO: Add alert to ask user if he/she really wants to delete this

           alertUser();

        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (idToUse == 0){
                    Toast.makeText(ToDoDetailsActivity.this, "Nothing to edit", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(ToDoDetailsActivity.this, NewToDoActivity.class);
                    intent.putExtra("ID", idToUse);
                    startActivity(intent);
                }

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent().hasExtra("ID")){

            idToUse = getIntent().getLongExtra("ID", 0);
            Note savedNote = notesBox.get(idToUse);


            toolBarLayout.setTitle(savedNote.getTitle());
            txtDetails.setText(savedNote.getDescription());
        }
    }

    public void deleteTodo(){
        if (idToUse == 0){
            Toast.makeText(this, "No Todo Selected", Toast.LENGTH_SHORT).show();
        }
        else{
            notesBox.remove(idToUse);
            Intent intent = new Intent(this, ToDoActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void alertUser(){

        AlertDialog.Builder builder = new AlertDialog.Builder(ToDoDetailsActivity.this);
        builder.setTitle("Delete Todo")
                .setMessage("Are you sure you want to delete this message?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteTodo();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ToDoDetailsActivity.this, "Deleting stopped", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


}