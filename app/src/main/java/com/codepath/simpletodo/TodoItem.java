package com.codepath.simpletodo;

import android.app.LauncherActivity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by isaac on 1/1/15.
 */
@Table(name = "TodoItems")
public class TodoItem extends Model{
    // This is a regular field
    @Column(name = "Description")
    public String description;

    @Column(name = "ListIndex")
    public int listIndex;

    @Column(name = "DueDate")
    public String dueDate;

    // Make sure to have a default constructor for every ActiveAndroid model
    public TodoItem(){
        super();
    }

    public TodoItem(String description, int listIndex, String dueDate ){
        super();
        this.description = description;
        this.listIndex = listIndex;
        this.dueDate = dueDate;
    }

    public static TodoItem getByListIndex(int priority){
        return new Select()
                    .from(TodoItem.class)
                    .where("ListIndex = ?", priority)
                    .executeSingle();
    }

    public static ArrayList<TodoItem> allSortedItems(){
        return new Select()
                    .from(TodoItem.class)
                    .orderBy("ListIndex ASC")
                    .execute();
    }

    /// Used to reset strange states in the DB
    public static void deleteAll(){
        new Delete().from(TodoItem.class).where("Id > ?", 0);
    }

}
