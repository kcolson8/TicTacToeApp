package com.example.pa5;

public class Cell {
    protected String symbol;
    protected Coordinates cellCoord;

    //no argument constructor defaults cell symbol to a dash (empty)
    //sets default coordinates to be (0,0)
    public Cell(){
        symbol = "-";
        cellCoord = new Coordinates(0,0);
    }

    //constructor accepts a specified symbol, and specified coordinates separated into row and column values
    public Cell(String symbol, int row, int column){
        this.symbol = symbol;
        this.cellCoord = new Coordinates(row, column);
    }

    @Override
    //overridden toString method returns the symbol of the cell
    public String toString(){
        return symbol;
    }

    //returns the cell's coordinates
    public Coordinates getCellCoord(){
        return cellCoord;
    }

    //calls the Coordinates' class' toString() method to return the cell coordinates as a string
    public String getCellCoordStr(){
        return cellCoord.toString();
    }

}
