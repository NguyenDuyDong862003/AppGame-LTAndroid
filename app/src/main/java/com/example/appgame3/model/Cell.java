package com.example.appgame3.model;

import java.util.Objects;

public class Cell {
    int row;
    int col;

    public Cell(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cell other = (Cell) obj;
        return col == other.col && row == other.row;
    }

    @Override
    public String toString() {
        return "Cell [row=" + row + ", col=" + col + "]";
    }

}