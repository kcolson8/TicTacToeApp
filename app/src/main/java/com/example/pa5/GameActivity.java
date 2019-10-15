package com.example.pa5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView messageToUser = (TextView) findViewById(R.id.outputMessageTextView);
        ImageView currentPlayerImage = (ImageView) findViewById(R.id.currentPlayerImageView);
        Intent intent = getIntent();
        if(intent != null){
            String player1 = intent.getStringExtra("player1");
            String player2 = intent.getStringExtra("player2");

            String currentPlayer = chooseStartPlayer(player1, player2);
            messageToUser.setText(currentPlayer + " is going first");

            if(currentPlayer == player1) {
                //player 1 is represented by a sheep
                currentPlayerImage.setImageResource(R.drawable.sheep);
            } else {
                //player 2 is represented by a pig
                currentPlayerImage.setImageResource(R.drawable.pig);
            }

        }

        Button quitButton = (Button) findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameActivity.this.finish();
            }
        });
    }

    //uses Random function to randomly choose between player X and player O for who goes first
    //returns the selected random start player
    public static String chooseStartPlayer(String player1, String player2){
        String[] players = {player1, player2};
        Random rand = new Random();
        int playerIndex = rand.nextInt(2);
        String currentPlayer = players[playerIndex];
        return currentPlayer;
    }
}



/*
import java.util.Random;
import java.util.Scanner;


public class Driver {
    public static void main(String[] args) {
        //variable declarations
        String currentPlayer;

        //game setup
        displayInstructions();
        final int BOARD_SIZE = getBoardSize();
        TicTacToeBoard board = new TicTacToeBoard(BOARD_SIZE);
        currentPlayer = chooseStartPlayer();

        //loops while there is not yet a winner and the board is not full
        while(!board.isWinner(currentPlayer)){
            System.out.println(board.toString());
            getPlayerCoord(currentPlayer, board);
            System.out.println(board.toString());
            currentPlayer = changePlayer(currentPlayer);
        }
    }

    //series of print statements for the user to learn how to play the game
    public static void displayInstructions(){
        System.out.println("Let's ply Tic Tac Toe!");
        System.out.println("Instructions:");
        System.out.println("This is a two player game, where one player will be player 'X', the other, player 'O'.");
        System.out.println("The board's dimensions will be N x N and the goal is to get N number of X's or O's in a row either diagonally, vertically, or horizontally.");
    }

    //uses Random function to randomly choose between player X and player O for who goes first
    //returns the selected random start player
    public static String chooseStartPlayer(){
        String[] players = {"X", "O"};
        Random rand = new Random();
        int playerIndex = rand.nextInt(2);
        String currentPlayer = players[playerIndex];
        System.out.println("Player " + currentPlayer + " is going first.");
        return currentPlayer;
    }

    //asks for user to enter the size of the board
    //returns the specified board size
    public static int getBoardSize(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter N for the dimensions of the board to be N x N cells: ");
        int boardSize = input.nextInt();
        return boardSize;
    }

    //asks for player to enter the coordinates for where they would like to place their X or O
    //checks that the specified coordinates are available and do not already have an X or O in its cell
    public static void getPlayerCoord(String currentPlayer, TicTacToeBoard board){
        Scanner input = new Scanner(System.in);
        System.out.println("Player " + currentPlayer + ", please enter the column coordinate of your placement: ");
        int columnPlacement = input.nextInt();
        System.out.println("Player " + currentPlayer + ", please enter the row coordinate of your placement: ");
        int rowPlacement = input.nextInt();

        Coordinates coordinates = new Coordinates(columnPlacement, rowPlacement);
        if(board.isValidMove(coordinates)) {
            board.makeMove(coordinates, currentPlayer);
        } else {
            System.out.println(coordinates.toString() + " is not a valid move");
            getPlayerCoord(currentPlayer, board); //recursively calls function until valid coordinates are entered
        }
    }

    //accepts as a parameter the current player and accordingly returns the other player to switch turns
    public static String changePlayer(String currentPlayer){
        if(currentPlayer == "X"){
            currentPlayer = "O";
        }else {
            currentPlayer = "X";
        }
        return currentPlayer;
    }
}

 */