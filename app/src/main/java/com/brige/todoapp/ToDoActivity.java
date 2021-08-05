package com.brige.todoapp;

import android.content.Intent;
import android.os.Bundle;

import com.brige.todoapp.adapters.NotesAdapter;
import com.brige.todoapp.models.Note;
import com.brige.todoapp.settings.SharedPrefConfig;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class ToDoActivity extends AppCompatActivity {

    int numberOfSearches = 0;
    TextView welcomeText;

    private Box<Note> notesBox;
    private List<Note> todos = new ArrayList<>();
    RecyclerView notesRecyclerView;
    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        notesBox = ObjectBox.get().boxFor(Note.class);

        Toast.makeText(this, "You have " + notesBox.count() + " To dos.", Toast.LENGTH_SHORT).show();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        notesRecyclerView = findViewById(R.id.notes_recycler);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        EditText inputSearch = findViewById(R.id.inputSearch);
        welcomeText = findViewById(R.id.txtWelcome);


        inputSearch.setOnEditorActionListener((v, actionId, event)-> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                Toast.makeText(this, "Number of searches is:" + addNumbers(), Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_to_do_details, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {

        int menu_id = item.getItemId();

        if (menu_id == R.id.action_logoout) {

            SharedPrefConfig sharedPrefConfig = new SharedPrefConfig(this);
            sharedPrefConfig.setLoggingInStatus(false);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

            return true;
        }
        else if (menu_id == R.id.action_settings){
            Toast.makeText(this, "Coming Soon...", Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);

        }


    }

    public int addNumbers()
    {

        return numberOfSearches++;
    }

    @Override
    protected void onResume() {
        super.onResume();

        String welcome = "Hello " + new SharedPrefConfig(this).getUserName();
        welcomeText.setText(welcome);

        todos = notesBox.getAll();
        notesAdapter = new NotesAdapter(todos, ToDoActivity.this);
        notesRecyclerView.setAdapter(notesAdapter);
        notesAdapter.notifyDataSetChanged();
    }
}