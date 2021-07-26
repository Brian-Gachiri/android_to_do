package com.brige.todoapp;

import android.content.Intent;
import android.os.Bundle;

import com.brige.todoapp.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

public class ToDoActivity extends AppCompatActivity {

    int numberOfSearches = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CardView singleCard = findViewById(R.id.card_single);

        EditText inputSearch = findViewById(R.id.inputSearch);

        inputSearch.setOnEditorActionListener((v, actionId, event)-> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                Toast.makeText(this, "Number of searches is:" + addNumbers(), Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

        singleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ToDoActivity.this, ToDoDetailsActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ToDoActivity.this, NewToDoActivity.class);
                startActivity(intent);

            }
        });

    }

    public int addNumbers()
    {

        return numberOfSearches++;
    }


}