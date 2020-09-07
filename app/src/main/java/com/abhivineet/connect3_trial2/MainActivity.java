package com.abhivineet.connect3_trial2;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.id;
import static android.R.layout;

public class MainActivity extends AppCompatActivity {

    // yellow = 0 ===== red = 1
    int activePlayer = 0;

    int count = 0;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPosition = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6} };

    boolean gameIsActive = true;


    public void doesWin() {

        for (int[] winning : winPosition) {
            if ((gameState[winning[0]] == gameState[winning[1]]) && (gameState[winning[1]] == gameState[winning[2]]) && (gameState[winning[0]] != 2)) {

                gameIsActive = false;

                String winner = "Red";

                if (gameState[winning[0]] == 0) {
                    winner = "Yellow";
                }
                //LinearLayout tayout;
                LinearLayout tayout = (LinearLayout) findViewById(R.id.playAgain);

                tayout.setVisibility(LinearLayout.VISIBLE);

                TextView winnerMess = (TextView) findViewById(R.id.winnerMessage);

                winnerMess.setText(winner + " has won!");

            }else{
                if (count==9){
                    LinearLayout tayout = (LinearLayout) findViewById(R.id.playAgain);

                    tayout.setVisibility(LinearLayout.VISIBLE);

                    TextView winnerMess = (TextView) findViewById(R.id.winnerMessage);

                    winnerMess.setText("Draw match!");
                }
            }
        }
    }

    public void playAgain(View view){

        LinearLayout tayout = (LinearLayout) findViewById(R.id.playAgain);

        tayout.setVisibility(LinearLayout.INVISIBLE);

        activePlayer = 0;

        count = 0;

        gameIsActive = true;

        for (int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i=0; i<gridLayout.getChildCount(); i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    public void dropin(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter]==2 && gameIsActive) {

            count++;

            counter.setTranslationY(-1000f);

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360f).setDuration((long) 300);

            doesWin();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
