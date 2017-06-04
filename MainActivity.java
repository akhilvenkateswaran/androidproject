package com.example.dell.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView sumTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    int locationOfAnswer=0;
    TextView resultTextView;
    TextView pointsTextView;
    int score;
    int numberOfQuestions;
    CountDownTimer countDownTimer;
    TextView timerTextView;
    Button startButton;
    RelativeLayout gameRelativeLayout;
    Button playAgainButton;
    boolean gameFinished;

    public void generate()
    {

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
        answers.clear();
        locationOfAnswer = rand.nextInt(4);


        for(int i=0;i<4;i++)
        {
            if(i==locationOfAnswer)
                answers.add(a+b);
            else
            {
                int incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer == a+b)
                    incorrectAnswer = rand.nextInt(41);

                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));



    }
    public void selectAnswer(View view)
    {
        if(!gameFinished)
        {
            if (view.getTag().toString().equals(Integer.toString(locationOfAnswer))) {
                score++;
                resultTextView.setText("CORRECT!!!!");
            } else
                resultTextView.setText("INCORRECT!!!");

            numberOfQuestions++;
            pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            generate();
        }
    }
    public void playAgain(View view)
    {
        score=0;
        numberOfQuestions=0;
        gameFinished = false;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generate();
        countDownTimer = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(Long.toString(l / 1000) + 's');
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("You have scored" + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                gameFinished = true;
                playAgainButton.setVisibility(View.VISIBLE);

            }
        }.start();
    }
    public void startGame(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(resultTextView);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = (TextView)findViewById(R.id.sumTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        startButton = (Button)findViewById(R.id.startButton);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);

    }
}
