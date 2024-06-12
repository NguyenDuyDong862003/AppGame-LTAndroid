package com.example.appgame3.database;

import com.example.appgame3.model.Level;

import java.util.List;

public interface DAO<T> {
    void insert(T object);
    Level findById(int id);
    List<T> findAll();
    int update(T object);
    int delete(T object);
}

