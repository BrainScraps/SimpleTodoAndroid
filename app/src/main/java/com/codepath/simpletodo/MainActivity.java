package com.codepath.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    ArrayList<TodoItem> todoItems;
    TodoItemsAdapter itemsAdapter;
    ListView lvItems;

    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        todoItems = TodoItem.allSortedItems();
        itemsAdapter = new TodoItemsAdapter(this, todoItems) ;
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            itemsAdapter.notifyDataSetChanged();
        }
    }

    public void editTodoItem(TodoItem todoItem, int listPosition) {
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);

        i.putExtra("description", todoItem.description );
        i.putExtra("listPosition", listPosition );
        startActivityForResult(i, REQUEST_CODE);
    }

    public void onAddItem(View v){
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemNext = etNewItem.getText().toString();
        // make a new TodoItem and save it
        int newTodoItemIndex = todoItems.size();
        TodoItem newTodoItem = new TodoItem(itemNext, newTodoItemIndex, null  );
        newTodoItem.save();
        itemsAdapter.add(newTodoItem);
        etNewItem.setText("");
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                        int listIndex = itemsAdapter.getItem(pos).listIndex;
                        TodoItem todoItem = TodoItem.getByListIndex(listIndex);
                        todoItems.remove(todoItem);
                        todoItem.delete();
                        itemsAdapter.persistListIndex(todoItems);
                        itemsAdapter.notifyDataSetChanged();
                        return true;
                    }
                }
        );

        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View item, int pos, long id){
                        TodoItem itemText = todoItems.get(pos);
                        editTodoItem(itemText, pos);
                    }
                }
        );
    }

}
