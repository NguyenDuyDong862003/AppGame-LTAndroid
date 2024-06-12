package com.example.appgame3.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appgame3.model.Level;
import com.google.gson.Gson;

public class LevelDAO {

    private static LevelDAO instance;
    private Gson gson;

    private LevelDAO() {
        gson = new Gson();
    }

    public static synchronized LevelDAO getInstance(DatabaseHelper databaseHelper) {
        if (instance == null) {
            instance = new LevelDAO();
        }
        return instance;
    }

    public void insert(SQLiteDatabase db, Level level) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ID, level.getId());
        values.put(DatabaseHelper.COLUMN_BOARD, gson.toJson(level.getBoard()));
        values.put(DatabaseHelper.CLEAR_BOARD, level.isCleared());
        db.insert(DatabaseHelper.TABLE_LEVELS, null, values);
    }

    public Level getLevel(SQLiteDatabase db, int levelId) {
        String selection = DatabaseHelper.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(levelId)};
        Cursor cursor = db.query(DatabaseHelper.TABLE_LEVELS, null, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String boardJson = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_BOARD));
            boolean cleared = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.CLEAR_BOARD)) > 0;
            int[][] board = gson.fromJson(boardJson, int[][].class);
            cursor.close();
            return new Level(levelId, board, cleared);
        } else {
            return null;
        }
    }
}
