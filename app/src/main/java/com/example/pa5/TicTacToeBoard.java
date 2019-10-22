package com.example.pa5;

public class TicTacToeBoard {
    protected static int N; //dimension of the board
    protected Cell[][] grid;
    protected int moveCount = 0;

    //no argument constructor defaults board size to 3 and initializes grid(board) to all dashes(empty board)
    public TicTacToeBoard() {
        N = 3;
        grid = new Cell[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                grid[i][j] = new Cell(0, i, j);
            }
        }
    }

    //constructor takes n as an argument to set the board size to n and initializes grid(board) to all dashes(empty board)
    public TicTacToeBoard(int n) {
        N = n;
        grid = new Cell[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                grid[i][j] = new Cell(0,i,j);
            }
        }
    }

    //getter function for N(board size), returns N
    public static int getN() {
        return N;
    }

    @Override
    //overridden toString function prints out grid(board)
    public String toString() {
        String gridString = "";
        for(int k = 0; k < N; k++){
            gridString += "  " + k;
        }
            gridString += '\n';
        for(int i = 0; i < N; i++) {
            gridString += i + " ";
            for (int j = 0; j < N; j++) {
                gridString += " " + grid[i][j] + " ";
            }
                gridString += '\n';
        }

        return gridString;
    }

    //returns true if the coordinates specified contain a dash, otherwise function returns false meaning
    //the coordinates have already been specified previously
    public boolean isValidMove(Coordinates coordinates){
        if(grid[coordinates.getColumn()][coordinates.getRow()].symbol == 0){
            return true;
        } else {
            return false;
        }
    }

    //updates grid member symbol at the specified coordinates to the player's symbol(X or O)
    public void makeMove(Coordinates coordinates, int playerSymbol){
        grid[coordinates.getColumn()][coordinates.getRow()].symbol= playerSymbol;
        moveCount++;
    }

    //returns true if one of the players have won by getting N in a row horizontally, vertically, or diagonally.
    //returns false otherwise
    public boolean isWinner(int playerSymbol){
        boolean won = false;

        //checks if there is a winner only if there have been enough moves made to allow a winner
        if(moveCount >= 2*N - 1) {
            if(isRowWin()){
                return true;
            }
            if(isColumnWin()){
                return true;
            }
            if(isDiagonalWin()){
                return true;
            }
            if(isAntiDiagonalWin()){
                return true;
            }
            if(isDraw()){
                return false;
            }
        }
        return won;
    }

    //checks each row of grid(board) to check for player win
    //returns true if N in a row horizontally
    //returns false otherwise
    public boolean isRowWin(){
        //initializing counter variables
        int numSheep = 0;
        int numPigs = 0;
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if (grid[i][j].symbol == R.drawable.sheep) {
                    numSheep++;
                } else if (grid[i][j].symbol == R.drawable.pig) {
                    numPigs++;
                }
            }
            if(numPigs == N){
                return true;
            } else if (numSheep == N){
                return true;
            }
            //resetting counter variables to check next row
            numSheep = 0;
            numPigs = 0;
        }

        return false;
    }

    //checks each column of grid(board) to check for player win
    //returns true if N in a row vertically
    //returns false otherwise
    public boolean isColumnWin(){
        //initializing counter variables
        int numSheep = 0;
        int numPigs = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if (grid[j][i].symbol == R.drawable.pig) {
                    numPigs++;
                } else if (grid[j][i].symbol == R.drawable.sheep) {
                    numSheep++;
                }
            }
            if(numPigs == N){
                return true;
            } else if (numSheep == N){
                return true;
            }
            //resetting counter variables to check next column
            numSheep = 0;
            numPigs = 0;
        }
        if(numPigs == N){
            return true;
        } else if (numSheep == N){
            return true;
        }
        return false;
    }

    //checks diagonal of grid(board) to check for player win
    //returns true if there are N in a row on the diagonal
    //returns false otherwise
    public boolean isDiagonalWin(){
        //initializing counter variables
        int numSheep = 0;
        int numPigs = 0;

        //checks diagonal for player win
        for(int i = 0, j = 0; i < N; i++, j++){
            if(grid[i][j].symbol == R.drawable.sheep){
                numSheep++;
            } else if(grid[i][j].symbol == R.drawable.pig){
                numPigs++;
            }
        }

        if(numSheep == N){
            return true;
        } else if (numPigs== N){
            return true;
        }

        return false;
    }

    //checks anti diagonal of grid(board) to check for player win
    //returns true if has N in a row on the anti diagonal
    //returns false otherwise
    public boolean isAntiDiagonalWin(){
        //initializing counter variables
        int numSheep = 0;
        int numPigs = 0;

        //checks anti diagonal for player win
        for(int i = 0, j = N - 1; i < N && j >= 0; i++, j--){
            if(grid[i][j].symbol == R.drawable.sheep){
                numSheep++;
            } else if(grid[i][j].symbol == R.drawable.pig){
                numPigs++;
            }
        }
        if(numPigs == N){
            return true;
        } else if (numSheep== N){
            return true;
        }

        return false;
    }

    //returns true if board is full and neither player has won, returns false otherwise
    public boolean isDraw(){
        if(moveCount == Math.pow(N, 2)){
            return true;
        }
        return false;
    }
}

