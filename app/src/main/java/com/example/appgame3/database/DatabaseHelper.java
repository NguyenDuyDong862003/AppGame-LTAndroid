package com.example.appgame3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appgame3.model.Level;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sokoban.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_LEVELS = "levels";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_BOARD = "board";
    public static final String CLEAR_BOARD = "cleared";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_LEVELS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_BOARD + " TEXT, " +
                    CLEAR_BOARD + " BOOLEAN);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        createData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEVELS);
        onCreate(db);
    }

    private void createData(SQLiteDatabase db) {
        LevelDAO DAO = LevelDAO.getInstance(this);

        int TREE = -1;
        int FLOOR = 0;
        int WALL = 1;
        int BOX = 2;
        int BOMB = 3;
        int BOX_BOMB = 4;
        int PLAYER = 5;
        int PLAYER_BOMB = 6;

        int[][] level1 = {
                {TREE, TREE, WALL, WALL, WALL, TREE, TREE, TREE, TREE},
                {TREE, TREE, WALL, BOMB, WALL, TREE, TREE, TREE, TREE},
                {TREE, TREE, WALL, FLOOR, WALL, WALL, WALL, WALL, TREE},
                {WALL, WALL, WALL, BOX, FLOOR, BOX, BOMB, WALL, TREE},
                {WALL, BOMB, FLOOR, BOX, PLAYER, WALL, WALL, WALL, TREE},
                {WALL, WALL, WALL, WALL, BOX, WALL, TREE, TREE, TREE},
                {TREE, TREE, TREE, WALL, BOMB, WALL, TREE, TREE, TREE},
                {TREE, TREE, TREE, WALL, WALL, WALL, TREE, TREE, TREE},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE}
        };
        int[][] level2 = {
                {WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE, TREE},
                {WALL, FLOOR, FLOOR, FLOOR, WALL, TREE, TREE, TREE, TREE},
                {WALL, FLOOR, BOX, PLAYER, WALL, TREE, WALL, WALL, WALL},
                {WALL, FLOOR, BOX, BOX, WALL, TREE, WALL, BOMB, WALL},
                {WALL, WALL, WALL, FLOOR, WALL, WALL, WALL, BOMB, WALL},
                {TREE, WALL, WALL, FLOOR, FLOOR, FLOOR, FLOOR, BOMB, WALL},
                {TREE, WALL, FLOOR, FLOOR, FLOOR, WALL, FLOOR, FLOOR, WALL},
                {TREE, WALL, FLOOR, FLOOR, FLOOR, WALL, WALL, WALL, WALL},
                {TREE, WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE}
        };
        int[][] level3 = {
                {TREE, WALL, WALL, WALL, WALL, TREE, TREE, TREE, TREE},
                {WALL, WALL, FLOOR, FLOOR, WALL, TREE, TREE, TREE, TREE},
                {WALL, FLOOR, PLAYER, BOX, WALL, TREE, TREE, TREE, TREE},
                {WALL, WALL, BOX, FLOOR, WALL, WALL, TREE, TREE, TREE},
                {WALL, WALL, FLOOR, BOX, FLOOR, WALL, TREE, TREE, TREE},
                {WALL, BOMB, BOX, FLOOR, FLOOR, WALL, TREE, TREE, TREE},
                {WALL, BOMB, BOMB, BOX_BOMB, BOMB, WALL, TREE, TREE, TREE},
                {WALL, WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE}
        };
        int[][] level4 = {{TREE, WALL, WALL, WALL, WALL, TREE, TREE, TREE, TREE},
                {TREE, WALL, PLAYER, FLOOR, WALL, WALL, WALL, TREE, TREE},
                {TREE, WALL, FLOOR, BOX, FLOOR, FLOOR, WALL, TREE, TREE},
                {WALL, WALL, WALL, FLOOR, WALL, FLOOR, WALL, WALL, TREE},
                {WALL, BOMB, WALL, FLOOR, WALL, FLOOR, FLOOR, WALL, TREE},
                {WALL, BOMB, BOX, FLOOR, FLOOR, WALL, FLOOR, WALL, TREE},
                {WALL, BOMB, FLOOR, FLOOR, FLOOR, BOX, FLOOR, WALL, TREE},
                {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, TREE},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE}};


        int[][] level5 = {{TREE, TREE, WALL, WALL, WALL, WALL, WALL, WALL, TREE},
                {TREE, TREE, WALL, FLOOR, FLOOR, FLOOR, FLOOR, WALL, TREE},
                {WALL, WALL, WALL, BOX, BOX, BOX, FLOOR, WALL, TREE},
                {WALL, PLAYER, FLOOR, BOX, BOMB, BOMB, FLOOR, WALL, TREE},
                {WALL, FLOOR, BOX, BOMB, BOMB, BOMB, WALL, WALL, TREE},
                {WALL, WALL, WALL, WALL, FLOOR, FLOOR, WALL, TREE, TREE},
                {TREE, TREE, TREE, WALL, WALL, WALL, WALL, TREE, TREE},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};

        int[][] level6 = {{TREE, TREE, WALL, WALL, WALL, WALL, WALL, TREE, TREE},
                {WALL, WALL, WALL, FLOOR, FLOOR, PLAYER, WALL, TREE, TREE},
                {WALL, FLOOR, FLOOR, BOX, BOMB, FLOOR, WALL, WALL, TREE},
                {WALL, FLOOR, FLOOR, BOMB, BOX, BOMB, FLOOR, WALL, TREE},
                {WALL, WALL, WALL, FLOOR, BOX_BOMB, BOX, FLOOR, WALL, TREE},
                {TREE, TREE, WALL, FLOOR, FLOOR, FLOOR, WALL, WALL, TREE},
                {TREE, TREE, WALL, WALL, WALL, WALL, WALL, TREE, TREE},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};
        int[][] level7 = {{TREE, TREE, WALL, WALL, WALL, WALL, TREE, TREE, TREE},
                {TREE, TREE, WALL, BOMB, BOMB, WALL, TREE, TREE, TREE},
                {TREE, WALL, WALL, FLOOR, BOMB, WALL, WALL, TREE, TREE},
                {TREE, WALL, FLOOR, FLOOR, BOX, BOMB, WALL, TREE, TREE},
                {WALL, WALL, FLOOR, BOX, FLOOR, FLOOR, WALL, WALL, TREE},
                {WALL, FLOOR, FLOOR, WALL, BOX, BOX, FLOOR, WALL, TREE},
                {WALL, FLOOR, FLOOR, PLAYER, FLOOR, FLOOR, FLOOR, WALL, TREE},
                {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, TREE},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};

        int[][] level8 = {{WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, TREE},
                {WALL, FLOOR, FLOOR, WALL, FLOOR, FLOOR, FLOOR, WALL, TREE},
                {WALL, PLAYER, BOX, BOMB, BOMB, BOX, FLOOR, WALL, TREE},
                {WALL, FLOOR, BOX, BOMB, BOX_BOMB, FLOOR, WALL, WALL, TREE},
                {WALL, FLOOR, BOX, BOMB, BOMB, BOX, FLOOR, WALL, TREE},
                {WALL, FLOOR, FLOOR, WALL, FLOOR, FLOOR, FLOOR, WALL, TREE},
                {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, TREE},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};

        int[][] level9 = {{WALL, WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE},
                {WALL, FLOOR, FLOOR, FLOOR, FLOOR, WALL, TREE, TREE, TREE},
                {WALL, FLOOR, BOX, BOX, BOX, WALL, WALL, TREE, TREE},
                {WALL, FLOOR, FLOOR, WALL, BOMB, BOMB, WALL, WALL, WALL},
                {WALL, WALL, FLOOR, FLOOR, BOMB, BOMB, BOX, FLOOR, WALL},
                {TREE, WALL, FLOOR, PLAYER, FLOOR, FLOOR, FLOOR, FLOOR, WALL},
                {TREE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};

        int[][] level10 = {{WALL, WALL, WALL, WALL, WALL, WALL, WALL, TREE, TREE},
                {WALL, BOMB, BOMB, BOX, BOMB, BOMB, WALL, TREE, TREE},
                {WALL, BOMB, BOMB, WALL, BOMB, BOMB, WALL, TREE, TREE},
                {WALL, FLOOR, BOX, BOX, BOX, FLOOR, WALL, TREE, TREE},
                {WALL, FLOOR, FLOOR, BOX, FLOOR, FLOOR, WALL, TREE, TREE},
                {WALL, FLOOR, BOX, BOX, BOX, FLOOR, WALL, TREE, TREE},
                {WALL, FLOOR, FLOOR, WALL, PLAYER, FLOOR, WALL, TREE, TREE},
                {WALL, WALL, WALL, WALL, WALL, WALL, WALL, TREE, TREE},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};

        int[][] level11 = {{TREE, WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE},
                {TREE, WALL, FLOOR, PLAYER, FLOOR, WALL, WALL, WALL, TREE},
                {WALL, WALL, FLOOR, WALL, BOX, FLOOR, FLOOR, WALL, TREE},
                {WALL, FLOOR, BOX_BOMB, BOMB, FLOOR, BOMB, FLOOR, WALL, TREE},
                {WALL, FLOOR, FLOOR, BOX, BOX, FLOOR, WALL, WALL, TREE},
                {WALL, WALL, WALL, FLOOR, WALL, BOMB, WALL, TREE, TREE},
                {TREE, TREE, WALL, FLOOR, FLOOR, FLOOR, WALL, TREE, TREE},
                {TREE, TREE, WALL, WALL, WALL, WALL, WALL, TREE, TREE},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};

        int[][] level12 = {{WALL, WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE},
                {WALL, FLOOR, FLOOR, FLOOR, FLOOR, WALL, TREE, TREE, TREE},
                {WALL, FLOOR, BOX, FLOOR, PLAYER, WALL, TREE, TREE, TREE},
                {WALL, WALL, BOX_BOMB, FLOOR, FLOOR, WALL, TREE, TREE, TREE},
                {WALL, FLOOR, BOX_BOMB, FLOOR, WALL, WALL, TREE, TREE, TREE},
                {WALL, FLOOR, BOX_BOMB, FLOOR, WALL, TREE, TREE, TREE, TREE},
                {WALL, FLOOR, BOX_BOMB, FLOOR, WALL, TREE, TREE, TREE, TREE},
                {WALL, FLOOR, BOMB, FLOOR, WALL, TREE, TREE, TREE, TREE},
                {WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE, TREE},};
        int[][] level13 = {{TREE, TREE, WALL, WALL, WALL, WALL, TREE, TREE, TREE},
                {TREE, TREE, WALL, FLOOR, FLOOR, WALL, TREE, TREE, TREE},
                {WALL, WALL, WALL, BOX, FLOOR, WALL, WALL, TREE, TREE},
                {WALL, FLOOR, FLOOR, BOX_BOMB, FLOOR, PLAYER, WALL, TREE, TREE},
                {WALL, FLOOR, FLOOR, BOX_BOMB, FLOOR, FLOOR, WALL, TREE, TREE},
                {WALL, FLOOR, FLOOR, BOX_BOMB, FLOOR, WALL, WALL, TREE, TREE},
                {WALL, WALL, WALL, BOX_BOMB, FLOOR, WALL, TREE, TREE, TREE},
                {TREE, TREE, WALL, BOMB, WALL, WALL, TREE, TREE, TREE},
                {TREE, TREE, WALL, WALL, WALL, TREE, TREE, TREE, TREE},};

        int[][] level14 = {{WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE, TREE},
                {WALL, FLOOR, FLOOR, FLOOR, WALL, WALL, WALL, WALL, WALL},
                {WALL, FLOOR, WALL, FLOOR, WALL, FLOOR, FLOOR, FLOOR, WALL},
                {WALL, FLOOR, BOX, FLOOR, FLOOR, FLOOR, BOX, FLOOR, WALL},
                {WALL, BOMB, BOMB, WALL, BOX, WALL, BOX, WALL, WALL},
                {WALL, BOMB, PLAYER, BOX, FLOOR, FLOOR, FLOOR, WALL, TREE},
                {WALL, BOMB, BOMB, FLOOR, FLOOR, WALL, WALL, WALL, TREE},
                {WALL, WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};

        int[][] level15 = {{TREE, WALL, WALL, WALL, WALL, WALL, WALL, TREE, TREE},
                {TREE, WALL, FLOOR, FLOOR, FLOOR, FLOOR, WALL, WALL, TREE},
                {WALL, WALL, BOMB, WALL, WALL, BOX, FLOOR, WALL, TREE},
                {WALL, FLOOR, BOMB, BOMB, BOX, FLOOR, FLOOR, WALL, TREE},
                {WALL, FLOOR, FLOOR, WALL, BOX, FLOOR, FLOOR, WALL, TREE},
                {WALL, FLOOR, FLOOR, PLAYER, FLOOR, WALL, WALL, WALL, TREE},
                {WALL, WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},
                {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};

        DAO.insert(db, new
                Level(1, level1, false));
        DAO.insert(db, new
                Level(2, level2, false));
        DAO.insert(db, new
                Level(3, level3, false));
        DAO.insert(db, new
                Level(4, level4, false));
        DAO.insert(db, new
                Level(5, level5, false));
        DAO.insert(db, new
                Level(6, level6, false));
        DAO.insert(db, new
                Level(7, level7, false));
        DAO.insert(db, new
                Level(8, level8, false));
        DAO.insert(db, new
                Level(9, level9, false));
        DAO.insert(db, new
                Level(10, level10, false));
        DAO.insert(db, new
                Level(11, level11, false));
        DAO.insert(db, new
                Level(12, level12, false));
        DAO.insert(db, new
                Level(13, level13, false));
        DAO.insert(db, new
                Level(14, level14, false));
        DAO.insert(db, new
                Level(15, level15, false));


    }
}
