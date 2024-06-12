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

        this.level = 1;
        dbHelper = new DatabaseHelper(context);
        levelDAO = LevelDAO.getInstance(dbHelper);
        this.setLevel(this.level);
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
//    public void setLevel(int level) {
//        this.level = level;
//
//        if (level == 1) {
//            this.board = new int[][]{{TREE, TREE, WALL, WALL, WALL, TREE, TREE, TREE, TREE},
//                    {TREE, TREE, WALL, BOMB, WALL, TREE, TREE, TREE, TREE},
//                    {TREE, TREE, WALL, FLOOR, WALL, WALL, WALL, WALL, TREE},
//                    {WALL, WALL, WALL, BOX, FLOOR, BOX, BOMB, WALL, TREE},
//                    {WALL, BOMB, FLOOR, BOX, PLAYER, WALL, WALL, WALL, TREE},
//                    {WALL, WALL, WALL, WALL, BOX, WALL, TREE, TREE, TREE},
//                    {TREE, TREE, TREE, WALL, BOMB, WALL, TREE, TREE, TREE},
//                    {TREE, TREE, TREE, WALL, WALL, WALL, TREE, TREE, TREE},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE}};
//        }
//
//        if (level == 2) {
//            this.board = new int[][]{{WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE, TREE},
//                    {WALL, FLOOR, FLOOR, FLOOR, WALL, TREE, TREE, TREE, TREE},
//                    {WALL, FLOOR, BOX, PLAYER, WALL, TREE, WALL, WALL, WALL},
//                    {WALL, FLOOR, BOX, BOX, WALL, TREE, WALL, BOMB, WALL},
//                    {WALL, WALL, WALL, FLOOR, WALL, WALL, WALL, BOMB, WALL},
//                    {TREE, WALL, WALL, FLOOR, FLOOR, FLOOR, FLOOR, BOMB, WALL},
//                    {TREE, WALL, FLOOR, FLOOR, FLOOR, WALL, FLOOR, FLOOR, WALL},
//                    {TREE, WALL, FLOOR, FLOOR, FLOOR, WALL, WALL, WALL, WALL},
//                    {TREE, WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE},};
//        }
//        if (level == 3) {
//            this.board = new int[][]{{TREE, WALL, WALL, WALL, WALL, TREE, TREE, TREE, TREE},
//                    {WALL, WALL, FLOOR, FLOOR, WALL, TREE, TREE, TREE, TREE},
//                    {WALL, FLOOR, PLAYER, BOX, WALL, TREE, TREE, TREE, TREE},
//                    {WALL, WALL, BOX, FLOOR, WALL, WALL, TREE, TREE, TREE},
//                    {WALL, WALL, FLOOR, BOX, FLOOR, WALL, TREE, TREE, TREE},
//                    {WALL, BOMB, BOX, FLOOR, FLOOR, WALL, TREE, TREE, TREE},
//                    {WALL, BOMB, BOMB, BOX_BOMB, BOMB, WALL, TREE, TREE, TREE},
//                    {WALL, WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};
//        }
//        if (level == 4) {
//            this.board = new int[][]{{TREE, WALL, WALL, WALL, WALL, TREE, TREE, TREE, TREE},
//                    {TREE, WALL, PLAYER, FLOOR, WALL, WALL, WALL, TREE, TREE},
//                    {TREE, WALL, FLOOR, BOX, FLOOR, FLOOR, WALL, TREE, TREE},
//                    {WALL, WALL, WALL, FLOOR, WALL, FLOOR, WALL, WALL, TREE},
//                    {WALL, BOMB, WALL, FLOOR, WALL, FLOOR, FLOOR, WALL, TREE},
//                    {WALL, BOMB, BOX, FLOOR, FLOOR, WALL, FLOOR, WALL, TREE},
//                    {WALL, BOMB, FLOOR, FLOOR, FLOOR, BOX, FLOOR, WALL, TREE},
//                    {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, TREE},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE}};
//
//        }
//        if (level == 5) {
//            this.board = new int[][]{{TREE, TREE, WALL, WALL, WALL, WALL, WALL, WALL, TREE},
//                    {TREE, TREE, WALL, FLOOR, FLOOR, FLOOR, FLOOR, WALL, TREE},
//                    {WALL, WALL, WALL, BOX, BOX, BOX, FLOOR, WALL, TREE},
//                    {WALL, PLAYER, FLOOR, BOX, BOMB, BOMB, FLOOR, WALL, TREE},
//                    {WALL, FLOOR, BOX, BOMB, BOMB, BOMB, WALL, WALL, TREE},
//                    {WALL, WALL, WALL, WALL, FLOOR, FLOOR, WALL, TREE, TREE},
//                    {TREE, TREE, TREE, WALL, WALL, WALL, WALL, TREE, TREE},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};
//        }
//        if (level == 6) {
//            this.board = new int[][]{{TREE, TREE, WALL, WALL, WALL, WALL, WALL, TREE, TREE},
//                    {WALL, WALL, WALL, FLOOR, FLOOR, PLAYER, WALL, TREE, TREE},
//                    {WALL, FLOOR, FLOOR, BOX, BOMB, FLOOR, WALL, WALL, TREE},
//                    {WALL, FLOOR, FLOOR, BOMB, BOX, BOMB, FLOOR, WALL, TREE},
//                    {WALL, WALL, WALL, FLOOR, BOX_BOMB, BOX, FLOOR, WALL, TREE},
//                    {TREE, TREE, WALL, FLOOR, FLOOR, FLOOR, WALL, WALL, TREE},
//                    {TREE, TREE, WALL, WALL, WALL, WALL, WALL, TREE, TREE},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};
//        }
//        if (level == 7) {
//            this.board = new int[][]{{TREE, TREE, WALL, WALL, WALL, WALL, TREE, TREE, TREE},
//                    {TREE, TREE, WALL, BOMB, BOMB, WALL, TREE, TREE, TREE},
//                    {TREE, WALL, WALL, FLOOR, BOMB, WALL, WALL, TREE, TREE},
//                    {TREE, WALL, FLOOR, FLOOR, BOX, BOMB, WALL, TREE, TREE},
//                    {WALL, WALL, FLOOR, BOX, FLOOR, FLOOR, WALL, WALL, TREE},
//                    {WALL, FLOOR, FLOOR, WALL, BOX, BOX, FLOOR, WALL, TREE},
//                    {WALL, FLOOR, FLOOR, PLAYER, FLOOR, FLOOR, FLOOR, WALL, TREE},
//                    {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, TREE},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};
//        }
//        if (level == 8) {
//            this.board = new int[][]{{WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, TREE},
//                    {WALL, FLOOR, FLOOR, WALL, FLOOR, FLOOR, FLOOR, WALL, TREE},
//                    {WALL, PLAYER, BOX, BOMB, BOMB, BOX, FLOOR, WALL, TREE},
//                    {WALL, FLOOR, BOX, BOMB, BOX_BOMB, FLOOR, WALL, WALL, TREE},
//                    {WALL, FLOOR, BOX, BOMB, BOMB, BOX, FLOOR, WALL, TREE},
//                    {WALL, FLOOR, FLOOR, WALL, FLOOR, FLOOR, FLOOR, WALL, TREE},
//                    {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, TREE},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};
//        }
//        if (level == 9) {
//            this.board = new int[][]{{WALL, WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE},
//                    {WALL, FLOOR, FLOOR, FLOOR, FLOOR, WALL, TREE, TREE, TREE},
//                    {WALL, FLOOR, BOX, BOX, BOX, WALL, WALL, TREE, TREE},
//                    {WALL, FLOOR, FLOOR, WALL, BOMB, BOMB, WALL, WALL, WALL},
//                    {WALL, WALL, FLOOR, FLOOR, BOMB, BOMB, BOX, FLOOR, WALL},
//                    {TREE, WALL, FLOOR, PLAYER, FLOOR, FLOOR, FLOOR, FLOOR, WALL},
//                    {TREE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};
//        }
//        if (level == 10) {
//            this.board = new int[][]{{WALL, WALL, WALL, WALL, WALL, WALL, WALL, TREE, TREE},
//                    {WALL, BOMB, BOMB, BOX, BOMB, BOMB, WALL, TREE, TREE},
//                    {WALL, BOMB, BOMB, WALL, BOMB, BOMB, WALL, TREE, TREE},
//                    {WALL, FLOOR, BOX, BOX, BOX, FLOOR, WALL, TREE, TREE},
//                    {WALL, FLOOR, FLOOR, BOX, FLOOR, FLOOR, WALL, TREE, TREE},
//                    {WALL, FLOOR, BOX, BOX, BOX, FLOOR, WALL, TREE, TREE},
//                    {WALL, FLOOR, FLOOR, WALL, PLAYER, FLOOR, WALL, TREE, TREE},
//                    {WALL, WALL, WALL, WALL, WALL, WALL, WALL, TREE, TREE},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};
//        }
//        if (level == 11) {
//            this.board = new int[][]{{TREE, WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE},
//                    {TREE, WALL, FLOOR, PLAYER, FLOOR, WALL, WALL, WALL, TREE},
//                    {WALL, WALL, FLOOR, WALL, BOX, FLOOR, FLOOR, WALL, TREE},
//                    {WALL, FLOOR, BOX_BOMB, BOMB, FLOOR, BOMB, FLOOR, WALL, TREE},
//                    {WALL, FLOOR, FLOOR, BOX, BOX, FLOOR, WALL, WALL, TREE},
//                    {WALL, WALL, WALL, FLOOR, WALL, BOMB, WALL, TREE, TREE},
//                    {TREE, TREE, WALL, FLOOR, FLOOR, FLOOR, WALL, TREE, TREE},
//                    {TREE, TREE, WALL, WALL, WALL, WALL, WALL, TREE, TREE},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};
//        }
//        if (level == 12) {
//            this.board = new int[][]{{WALL, WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE},
//                    {WALL, FLOOR, FLOOR, FLOOR, FLOOR, WALL, TREE, TREE, TREE},
//                    {WALL, FLOOR, BOX, FLOOR, PLAYER, WALL, TREE, TREE, TREE},
//                    {WALL, WALL, BOX_BOMB, FLOOR, FLOOR, WALL, TREE, TREE, TREE},
//                    {WALL, FLOOR, BOX_BOMB, FLOOR, WALL, WALL, TREE, TREE, TREE},
//                    {WALL, FLOOR, BOX_BOMB, FLOOR, WALL, TREE, TREE, TREE, TREE},
//                    {WALL, FLOOR, BOX_BOMB, FLOOR, WALL, TREE, TREE, TREE, TREE},
//                    {WALL, FLOOR, BOMB, FLOOR, WALL, TREE, TREE, TREE, TREE},
//                    {WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE, TREE},};
//        }
//        if (level == 13) {
//            this.board = new int[][]{{TREE, TREE, WALL, WALL, WALL, WALL, TREE, TREE, TREE},
//                    {TREE, TREE, WALL, FLOOR, FLOOR, WALL, TREE, TREE, TREE},
//                    {WALL, WALL, WALL, BOX, FLOOR, WALL, WALL, TREE, TREE},
//                    {WALL, FLOOR, FLOOR, BOX_BOMB, FLOOR, PLAYER, WALL, TREE, TREE},
//                    {WALL, FLOOR, FLOOR, BOX_BOMB, FLOOR, FLOOR, WALL, TREE, TREE},
//                    {WALL, FLOOR, FLOOR, BOX_BOMB, FLOOR, WALL, WALL, TREE, TREE},
//                    {WALL, WALL, WALL, BOX_BOMB, FLOOR, WALL, TREE, TREE, TREE},
//                    {TREE, TREE, WALL, BOMB, WALL, WALL, TREE, TREE, TREE},
//                    {TREE, TREE, WALL, WALL, WALL, TREE, TREE, TREE, TREE},};
//        }
//        if (level == 14) {
//            this.board = new int[][]{{WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE, TREE},
//                    {WALL, FLOOR, FLOOR, FLOOR, WALL, WALL, WALL, WALL, WALL},
//                    {WALL, FLOOR, WALL, FLOOR, WALL, FLOOR, FLOOR, FLOOR, WALL},
//                    {WALL, FLOOR, BOX, FLOOR, FLOOR, FLOOR, BOX, FLOOR, WALL},
//                    {WALL, BOMB, BOMB, WALL, BOX, WALL, BOX, WALL, WALL},
//                    {WALL, BOMB, PLAYER, BOX, FLOOR, FLOOR, FLOOR, WALL, TREE},
//                    {WALL, BOMB, BOMB, FLOOR, FLOOR, WALL, WALL, WALL, TREE},
//                    {WALL, WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};
//        }
//        if (level == 15) {
//            this.board = new int[][]{{TREE, WALL, WALL, WALL, WALL, WALL, WALL, TREE, TREE},
//                    {TREE, WALL, FLOOR, FLOOR, FLOOR, FLOOR, WALL, WALL, TREE},
//                    {WALL, WALL, BOMB, WALL, WALL, BOX, FLOOR, WALL, TREE},
//                    {WALL, FLOOR, BOMB, BOMB, BOX, FLOOR, FLOOR, WALL, TREE},
//                    {WALL, FLOOR, FLOOR, WALL, BOX, FLOOR, FLOOR, WALL, TREE},
//                    {WALL, FLOOR, FLOOR, PLAYER, FLOOR, WALL, WALL, WALL, TREE},
//                    {WALL, WALL, WALL, WALL, WALL, WALL, TREE, TREE, TREE},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},
//                    {TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE, TREE},};
//        }
//    }

    public boolean checkCompleteLevel() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == BOMB || board[i][j] == PLAYER_BOMB)
                    return false;
            }
        }
        return true;
    }

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
