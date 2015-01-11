package com.codepath.simpletodo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class EditItemActivity extends ActionBarActivity {
    EditText etItemDescription;
    EditText etItemDueDate;
    int listPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        String itemDescription = getIntent().getStringExtra("description");
        String itemDueDate = getIntent().getStringExtra("dueDate");
        listPosition = getIntent().getIntExtra("listPosition", 0);
        etItemDescription = (EditText) findViewById(R.id.etItemDescription);
        etItemDueDate = (EditText) findViewById(R.id.etItemDueDate);
        etItemDescription.setText(itemDescription);
        etItemDueDate.setText(itemDueDate);
        etItemDescription.requestFocus();
        setupSaveButtonListener();
    }

    public void onSubmit(View v){
        Intent data = new Intent();
        String description =  etItemDescription.getText().toString();
        String dueDate = etItemDueDate.getText().toString();
        TodoItem todoItem = TodoItem.getByListIndex(listPosition);
        todoItem.description = description;
        todoItem.dueDate = dueDate;
        todoItem.save();
        data.putExtra("itemDescription", description);
        data.putExtra("itemIndex", listPosition);
        setResult(RESULT_OK, data);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
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
    private void setupSaveButtonListener(){

    }
}
