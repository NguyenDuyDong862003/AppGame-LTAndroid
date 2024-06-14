package com.example.appgame3.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.appgame3.database.DatabaseHelper;
import com.example.appgame3.database.LevelDAO;

public class Model {
    public int[][] board;
    public int level;
    static int[][] position4Direction = {{-1, 0, 1, 0}, {0, 1, 0, -1},};
    private DatabaseHelper dbHelper;
    private LevelDAO levelDAO;
    public static final int TREE = -1;
    public static final int FLOOR = 0;
    public static final int WALL = 1;
    public static final int BOX = 2;
    public static final int BOMB = 3;
    public static final int BOX_BOMB = 4;
    public static final int PLAYER = 5;
    public static final int PLAYER_BOMB = 6;

    public Model(Context context) {
        super();
//        this.level = 1;
        dbHelper = new DatabaseHelper(context);
        levelDAO = LevelDAO.getInstance(dbHelper);
        this.setLevel(this.level);
    }
    public DatabaseHelper getDbHelper(){
        return dbHelper;
    }
    public void setLevel(int level) {
        this.level = level;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Level currentLevel = levelDAO.getLevel(db, level);
        if (currentLevel != null) {
            this.board = currentLevel.getBoard();
        }
        db.close();
    }

    public boolean checkCompleteLevel() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == BOMB || board[i][j] == PLAYER_BOMB)
                    return false;
            }
        }
        return true;
    }
    public void setLvCompleted(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        LevelDAO.getInstance(dbHelper).updateClear(db, level);
        db.close();
    }
//     boolean isLevelCleared(int levelNumber) {
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        boolean isCleared = levelDAO.isLevelCleared(db, levelNumber);
//        db.close();
//        return isCleared;
//    }

    public Cell getPositionCharacter() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == PLAYER || board[i][j] == PLAYER_BOMB)
                    return new Cell(i, j);
            }
        }
        return new Cell(-1, -1);
    }

    public void diChuyenNhanVat(int direction) {
        Cell currentPos = this.getPositionCharacter();
        int rowCur = currentPos.row;
        int colCur = currentPos.col;

        int nextRowChar = rowCur + position4Direction[0][direction];
        int nextColChar = colCur + position4Direction[1][direction];

//		ko bao giờ xảy ra, vì map bọc tường rồi
//		if (newRow < 0 || newCol < 0 || newRow >= this.board.length || newCol >= this.board[0].length)
//			return;

        if (this.board[nextRowChar][nextColChar] == WALL)
            return;

        if (this.board[nextRowChar][nextColChar] == BOX || this.board[nextRowChar][nextColChar] == BOX_BOMB) {
//			đẩy thùng gỗ, sau khi đẩy mà vị trí này vẫn là thùng gỗ (thùng gỗ ko đẩy dc) thì ko dc đi
            int nextRowBox = nextRowChar + position4Direction[0][direction];
            int nextColBox = nextColChar + position4Direction[1][direction];
            if (this.board[nextRowBox][nextColBox] == FLOOR) {
                if (this.board[nextRowChar][nextColChar] == BOX) {
                    this.board[nextRowBox][nextColBox] = BOX;
                    this.board[nextRowChar][nextColChar] = FLOOR;
                }
                if (this.board[nextRowChar][nextColChar] == BOX_BOMB) {
                    this.board[nextRowBox][nextColBox] = BOX;
                    this.board[nextRowChar][nextColChar] = BOMB;
                }
            }

            if (this.board[nextRowBox][nextColBox] == BOMB) {
                if (this.board[nextRowChar][nextColChar] == BOX) {
                    this.board[nextRowBox][nextColBox] = BOX_BOMB;
                    this.board[nextRowChar][nextColChar] = FLOOR;
                }
                if (this.board[nextRowChar][nextColChar] == BOX_BOMB) {
                    this.board[nextRowBox][nextColBox] = BOX_BOMB;
                    this.board[nextRowChar][nextColChar] = BOMB;
                }
            }
        }

        if (this.board[nextRowChar][nextColChar] == FLOOR) {
            if (this.board[rowCur][colCur] == PLAYER) {
                this.board[nextRowChar][nextColChar] = PLAYER;
                this.board[rowCur][colCur] = FLOOR;
            }
            if (this.board[rowCur][colCur] == PLAYER_BOMB) {
                this.board[nextRowChar][nextColChar] = PLAYER;
                this.board[rowCur][colCur] = BOMB;
            }
        }

        if (this.board[nextRowChar][nextColChar] == BOMB) {
            if (this.board[rowCur][colCur] == PLAYER) {
                this.board[nextRowChar][nextColChar] = PLAYER_BOMB;
                this.board[rowCur][colCur] = FLOOR;
            }
            if (this.board[rowCur][colCur] == PLAYER_BOMB) {
                this.board[nextRowChar][nextColChar] = PLAYER_BOMB;
                this.board[rowCur][colCur] = BOMB;
            }
        }
    }

}
