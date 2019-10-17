package com.example.pa5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "TicTacToeMainTag";
    static final int PLAYERS_REQUEST_CODE = 1;

    //Credit for sheep icon
    //<div>Icons made by <a href="https://www.flaticon.com/authors/smashicons" title="Smashicons">Smashicons</a> from <a href="https://www.flaticon.com/"
    // title="Flaticon">www.flaticon.com</a></div>

    //Credit for pig icon
    //<div>Icons made by <a href="https://www.flaticon.com/authors/smashicons" title="Smashicons">Smashicons</a> from <a href="https://www.flaticon.com/"
    // title="Flaticon">www.flaticon.com</a></div>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button startGameButton = (Button) findViewById(R.id.button);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);

                EditText player1Name = (EditText) findViewById(R.id.player1EditText);
                EditText player2Name = (EditText) findViewById(R.id.player2EditText);

                if(player1Name.getText().toString().compareTo(player2Name.getText().toString()) != 0){
                    if((player1Name.getText().length() > 0) && (player2Name.getText().length() > 0)){
                        String player1 = player1Name.getText().toString();
                        intent.putExtra("player1", player1);
                        String player2 = player2Name.getText().toString();
                        intent.putExtra("player2", player2);

                        startActivityForResult(intent, PLAYERS_REQUEST_CODE);
                    } else {
                        Log.d(TAG, "No entry name for at least one of the players");
                    }
                } else {
                    Log.d(TAG, "Cannot have matching names for player 1 and player 2");
                }
            }
        });
    }
}
