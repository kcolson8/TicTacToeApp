package com.example.pa5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    static String currentPlayer;
    static String player1;
    static String player2;
    TextView messageToUser;
    ImageView currentPlayerImage;
    TicTacToeBoard board;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        board = new TicTacToeBoard();

        messageToUser = (TextView) findViewById(R.id.outputMessageTextView);
        currentPlayerImage = (ImageView) findViewById(R.id.currentPlayerImageView);
        Intent intent = getIntent();
        if(intent != null){
            player1 = intent.getStringExtra("player1");
            player2 = intent.getStringExtra("player2");

            currentPlayer = chooseStartPlayer(player1, player2);

            gameLoop(currentPlayerImage,messageToUser);
        }

        Button quitButton = (Button) findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameActivity.this.finish();
            }
        });
    }

    public void gameLoop(ImageView currentPlayerImage, TextView messageToUser){
        int icon;
        if(currentPlayer == player1) {
            //player 1 is represented by a sheep
            icon = R.drawable.sheep;
            currentPlayerImage.setImageResource(icon);
            messageToUser.setText("It is " + currentPlayer + "'s turn\n Please tap a blank square to make a move");
        } else {
            //player 2 is represented by a pig
            icon = R.drawable.pig;
            currentPlayerImage.setImageResource(icon);
            messageToUser.setText("It is " + currentPlayer + "'s turn\n Please tap a blank square to make a move");
        }
    }

    public void onClick(View view) {
        ImageView image = (ImageView) view;
        if(currentPlayer == player1){
            //player 1 is represented by a sheep
            image.setImageResource(R.drawable.sheep);
        } else {
            //player 2 is represented by a pig
            image.setImageResource(R.drawable.pig);
        }
        int rowPlacement = image.getTag().toString().charAt(0); //first digit of tag
        int columnPlacement = image.getTag().toString().charAt(1); //second digit of tag
        //getPlayerCoord(currentPlayer, board, rowPlacement, columnPlacement);
        currentPlayer = changePlayer(currentPlayer);
        gameLoop(currentPlayerImage, messageToUser);
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

    //accepts as a parameter the current player and accordingly returns the other player to switch turns
    public static String changePlayer(String currentPlayer){
        if(currentPlayer == player1){
            currentPlayer = player2;
        }else {
            currentPlayer = player1;
        }
        return currentPlayer;
    }

    //asks for player to enter the coordinates for where they would like to place their X or O
    //checks that the specified coordinates are available and do not already have an X or O in its cell
    public static void getPlayerCoord(String currentPlayer, TicTacToeBoard board, int rowPlacement, int columnPlacement){
        Coordinates coordinates = new Coordinates(columnPlacement, rowPlacement);
        if(board.isValidMove(coordinates)) {
            board.makeMove(coordinates, currentPlayer);
        } else {
            System.out.println(coordinates.toString() + " is not a valid move");
            getPlayerCoord(currentPlayer, board, rowPlacement, columnPlacement); //recursively calls function until valid coordinates are entered
        }
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

}

 */