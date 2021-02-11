package com.saileshorg.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0; //0 is red and 1 is yellow
    boolean isGameActive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5} , {6,7,8} ,{0,3,6} , {1,4,7} ,{2,5,8} , {0,4,8} , {2,4,6} };
    LinearLayout score_layout;
    TextView winner;
    Button playagain;
    ImageView image0,image1,image2,image3,image4,image5,image6,image7,image8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score_layout = findViewById(R.id.score_layout);
        winner = findViewById(R.id.winner);
        playagain = findViewById(R.id.playagain);
        image0 = findViewById(R.id.image0);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
    }

    public void onTap(View view)
    {
        ImageView tappedImage = (ImageView)view;
        int tappedPosition = Integer.parseInt(tappedImage.getTag().toString());
        if(gameState[tappedPosition] == 2 && isGameActive)
        {
            gameState[tappedPosition] = activePlayer;
            tappedImage.setTranslationY(-1000f);
            if(activePlayer == 0)
            {
                tappedImage.setImageResource(R.drawable.redcircle);
            }
            else
            {
                tappedImage.setImageResource(R.drawable.yellowcircle);
            }
            tappedImage.animate().translationYBy(1000f).setDuration(500);
            if (activePlayer == 0){
                activePlayer = 1;
            }else
            {
                activePlayer = 0;
            }
            for (int[] winningPosition : winningPositions)
            {
                if ((gameState[winningPosition[0]]==gameState[winningPosition[1]]) &&
                        (gameState[winningPosition[1]]==gameState[winningPosition[2]]) &&
                        (gameState[winningPosition[2]]!=2))
                {
                        isGameActive = false;
                        String winnerText = "";
                        if (gameState[winningPosition[0]] == 0)
                        {
                            winnerText = "Red had won the game";
                        }
                        else
                        {
                            winnerText = "Yellow had won the game";
                        }
                        winner.setText(winnerText);
                        score_layout.setVisibility(View.VISIBLE);
                }else {
                        boolean isGameDraw = true;
                        for (int state : gameState){
                            if(state == 2){
                                isGameDraw = false;
                            }
                        }

                        if (isGameDraw){
                            winner.setText("This Game is a draw");
                            score_layout.setVisibility(View.VISIBLE);
                        }
                }
            }
        }
    }
    public void playAgain(View view)
    {
        isGameActive = true;
        activePlayer = 0;
        score_layout.setVisibility(View.INVISIBLE);
        for (int i=0 ; i<9 ;i++)
        {
            gameState[i] = 2;
        }
        image0.setImageResource(0);
        image1.setImageResource(0);
        image2.setImageResource(0);
        image3.setImageResource(0);
        image4.setImageResource(0);
        image5.setImageResource(0);
        image6.setImageResource(0);
        image7.setImageResource(0);
        image8.setImageResource(0);
    }
}