package com.example.appgame3.model;

public class Level {
    private int id;
    private int[][] board;
    private boolean cleared;
    public Level(int id, int[][] board,boolean cleared) {
        this.id = id;
        this.board = board;
        this.cleared= cleared;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
    public boolean isCleared(){
        return cleared;
    }
    public boolean setCleared(){
        return cleared = true;
    }
}
