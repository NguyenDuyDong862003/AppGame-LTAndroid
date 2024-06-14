package com.example.appgame3.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appgame3.model.Level;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LevelDAO implements DAO<Level> {

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
    // Lấy ra id của tất cả lv dag có (tạo button level)
    public List<Integer> getAllLevels(SQLiteDatabase db) {
        List<Integer> levels = new ArrayList<>();

        String[] columns = {DatabaseHelper.COLUMN_ID};

        Cursor cursor = db.query(DatabaseHelper.TABLE_LEVELS, columns, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int levelId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
                levels.add(levelId);
            }
            cursor.close();
        }

        return levels;
    }

    public void updateClear(SQLiteDatabase db, int levelId) {
        ContentValues values = new ContentValues();
        //Set trạng thái clear của level thành true
        Level lv = getLevel(db, levelId);
        values.put(DatabaseHelper.CLEAR_BOARD, lv.setCleared());
        String selection = DatabaseHelper.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(levelId)};
        db.update(DatabaseHelper.TABLE_LEVELS, values, selection, selectionArgs);
    }

//    public int getUnclearedLevel(SQLiteDatabase readableDatabase) {
//        String query = "SELECT * FROM " + DatabaseHelper.TABLE_LEVELS + " WHERE " + DatabaseHelper.CLEAR_BOARD + " = false";
//        int levelId = -1;
//        Cursor cursor = readableDatabase.rawQuery(query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            levelId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
//            cursor.close();
//            return levelId;
//        }
//        return levelId;
//    }
//@SuppressLint("Range") // bỏ qua cảnh báo về range của getColumnIndex có thể là -1
//public boolean isLevelCleared(SQLiteDatabase db, int levelNumber) {
//    String query = "SELECT cleared FROM levels WHERE level_id = ?";
//    Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(levelNumber) });
//    boolean isCleared = false;
//    if (cursor.moveToFirst()) {
//        isCleared = cursor.getInt(cursor.getColumnIndex("cleared")) == 1;
//    }
//    cursor.close();
//    return isCleared;
//}

}
