package com.example.pa5;

public class Coordinates {
    protected int row;
    protected int column;

    //no argument Coordinates constructor defaults the row and column members to 0
    public Coordinates(){
        row = 0;
        column = 0;
    }

    //constructor accepts row and column arguments to create a Coordinates object
    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    //overridden toString() function returns coordinates as a string in the form (0, 0)
    public String toString(){
        String coordString = "(" + row + ", " + column + ")";
        return coordString;
    }

    //getter method returning the row member of Coordinates
    public int getRow() {
        return row;
    }

    //getter method returning the column member of Coordinates
    public int getColumn() {
        return column;
    }
}
