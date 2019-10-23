package com.example.pa5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    TicTacToeBoard board = new TicTacToeBoard(3);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        int icon;

        messageToUser = (TextView) findViewById(R.id.outputMessageTextView);
        currentPlayerImage = (ImageView) findViewById(R.id.currentPlayerImageView);

        Intent intent = getIntent();
        //checks that the intent is good
        if(intent != null){
            player1 = intent.getStringExtra("player1");
            player2 = intent.getStringExtra("player2");

            gameSetup();
            messageToUser.setText("It is " + currentPlayer + "'s turn\n Please tap a blank square to make a move");
            currentPlayer = changePlayer(currentPlayer, currentPlayerImage, messageToUser);
        }

        //gets reference to the Quit button and returns the players to the home screen if it is clicked
        Button quitButton = (Button) findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameActivity.this.finish();
            }
        });

        //gets reference to the Play Again button and calls resetGame() if it is clicked
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    //chooses a random start player and accordingly sets the image view and the message text view
    public void gameSetup() {
        currentPlayer = chooseStartPlayer(player1, player2);
        if(currentPlayer.equals(player1)){
            currentPlayerImage.setImageResource(R.drawable.sheep);
        } else {
            currentPlayerImage.setImageResource(R.drawable.pig);
        }
        messageToUser.setText("It is " + currentPlayer + "'s turn\n Please tap a blank square to make a move");
    }

    //When an imageView is clicked on by a player, onClick() is called
    //onClick() sets the imageView to the icon representative of the current player who clicked on the
    //imageView if it is a valid move and checks if the game has been won or if it has ended in a tie.
    public void onClick(View view) {
        ImageView image = (ImageView) view;
        int rowPlacement = Integer.parseInt(image.getTag().toString().substring(0,1)); //first digit of tag
        int columnPlacement = Integer.parseInt(image.getTag().toString().substring(1,2)); //second digit of tag
        Coordinates coordinates = new Coordinates(rowPlacement, columnPlacement);
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        int icon;

        //sets icon image according to the current player
        if(currentPlayer.equals(player1)){
            icon = R.drawable.sheep;
        } else {
            icon = R.drawable.pig;
        }

        //checks that the game has not been won and that the location selected is valid before setting the imageView
        if(!board.isWinner(icon) && board.isValidMove(coordinates)){
            image.setImageResource(icon);
            board.makeMove(coordinates, icon);

            //checks that most recent move did not result in the game being won to change current player
            if(!board.isWinner(icon)) {
                currentPlayer = changePlayer(currentPlayer, currentPlayerImage, messageToUser);
            }
        } else if(!board.isValidMove(coordinates)){
            //lets player know via a Toast message that the imageView they clicked was not a valid one to make their move
            Toast.makeText(GameActivity.this, "Not a valid move", Toast.LENGTH_SHORT).show();
        }

        //checks if the game has been won to accordingly update the message text view and
        //make the Play Again button visible to the players
        if(board.isWinner(icon)){
            messageToUser.setText("Congratulations " + currentPlayer + ", you have won!\n Would you like to play again?");
            playAgainButton.setVisibility(view.VISIBLE);
        }
        //checks if the game has resulted in a draw to accordingly update the message text view and
        //make the Play Again button visible to the players
        else if(board.isDraw()){
            messageToUser.setText("The game has ended in a draw.\n Would you like to play again?");
            playAgainButton.setVisibility(view.VISIBLE);
        }
    }

    //uses Random function to randomly choose between player 1 and player 2 for who goes first
    //returns the selected random start player
    public static String chooseStartPlayer(String player1, String player2){
        String[] players = {player1, player2};
        Random rand = new Random();
        int playerIndex = rand.nextInt(2);
        currentPlayer = players[playerIndex];
        return currentPlayer;
    }

    //accepts as a parameter the current player and accordingly returns the other player to switch turns
    //updates the current player imageView and the message text view accordingly
    public static String changePlayer(String currentPlayer, ImageView currentPlayerImage, TextView messageToUser) {
        if (currentPlayer.equals(player1)) {
            currentPlayer = player2;
            currentPlayerImage.setImageResource(R.drawable.pig);
        } else {
            currentPlayer = player1;
            currentPlayerImage.setImageResource(R.drawable.sheep);

        }
        messageToUser.setText("It is " + currentPlayer + "'s turn\n Please tap a blank square to make a move");

        return currentPlayer;
    }

    //resets all of the imageViews to be blank, sets the play again button to be invisible,
    //and calls gameSetup() to randomly choose who will go first and set the player imageView and the message
    // text view accordingly
    public void resetGame(){
        board = new TicTacToeBoard(3);
        ImageView imageView00 = (ImageView) findViewById(R.id.imageView00);
        imageView00.setImageResource(0);

        ImageView imageView01 = (ImageView) findViewById(R.id.imageView01);
        imageView01.setImageResource(0);

        ImageView imageView02 = (ImageView) findViewById(R.id.imageView02);
        imageView02.setImageResource(0);

        ImageView imageView10 = (ImageView) findViewById(R.id.imageView10);
        imageView10.setImageResource(0);

        ImageView imageView11 = (ImageView) findViewById(R.id.imageView11);
        imageView11.setImageResource(0);

        ImageView imageView12 = (ImageView) findViewById(R.id.imageView12);
        imageView12.setImageResource(0);

        ImageView imageView20 = (ImageView) findViewById(R.id.imageView20);
        imageView20.setImageResource(0);

        ImageView imageView21 = (ImageView) findViewById(R.id.imageView21);
        imageView21.setImageResource(0);

        ImageView imageView22 = (ImageView) findViewById(R.id.imageView22);
        imageView22.setImageResource(0);

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);
        gameSetup();
    }

}
