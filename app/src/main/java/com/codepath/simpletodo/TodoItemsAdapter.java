package com.codepath.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by isaac on 1/8/15.
 */
public class TodoItemsAdapter extends ArrayAdapter<TodoItem> {
    public TodoItemsAdapter(Context context, ArrayList<TodoItem> todoItems) {
        super(context, 0, todoItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        TodoItem todoItem = TodoItem.getByListIndex(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_item, parent, false);
        }
        // Lookup view for data population
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
        TextView tvDueDate = (TextView) convertView.findViewById(R.id.tvDueDate);
        // Populate the data into the template view using the data object
        tvDescription.setText(todoItem.description);
        tvDueDate.setText(todoItem.dueDate);
        // Return the completed view to render on screen
        return convertView;
    }

    public static void persistListIndex(ArrayList<TodoItem> todoItems){
        ActiveAndroid.beginTransaction();
        try {
            int index = 0;
            for (TodoItem t : todoItems){
                t.listIndex = index;
                t.save();
                index++;

            }
            ActiveAndroid.setTransactionSuccessful();

        } finally {
            ActiveAndroid.endTransaction();
        }
    }
}
