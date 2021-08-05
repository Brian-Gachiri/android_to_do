package com.brige.todoapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brige.todoapp.R;
import com.brige.todoapp.ToDoDetailsActivity;
import com.brige.todoapp.models.Note;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    private List<Note> todos;
    private Context context;

    public NotesAdapter(List<Note> todos, Context context) {
        this.todos = todos;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_to_do, parent,false);

        return new NotesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        Note todo = todos.get(position);

        holder.title.setText(todo.getTitle());
        holder.details.setText(todo.getDescription());
        holder.time.setText("! hour ago");
        holder.id = todo.getId();


    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, details, time;
        long id;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txt_todo_title);
            details = itemView.findViewById(R.id.txt_todo_details);
            time = itemView.findViewById(R.id.txt_todo_time);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(context, "Nitakufinya", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ToDoDetailsActivity.class);
                    intent.putExtra("ID", id);
                    context.startActivity(intent);

                }
            });
        }
    }
}
