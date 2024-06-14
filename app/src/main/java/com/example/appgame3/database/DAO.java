package com.example.appgame3.database;

import android.database.sqlite.SQLiteDatabase;

import com.example.appgame3.model.Level;

public interface DAO<T> {
    void insert(SQLiteDatabase db, T t);
}

