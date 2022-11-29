package com.example.topquiz2.controler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquiz2.R;
import com.example.topquiz2.model.QuestionBank;
import com.example.topquiz2.model.Questions;
import com.example.topquiz2.R;
import com.example.topquiz2.model.QuestionBank;
import com.example.topquiz2.model.Questions;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    private TextView mgame_activity_textview_question;
    private Button mgame_activity_button_1;
    private Button mgame_activity_button_2;
    private Button mgame_activity_button_3;
    private Button mgame_activity_button_4;
    QuestionBank mQuestionBank = generateQuestions();
    Questions mCurrentQuestion;
    private int mScore;
    private int mRemainingQuestionCount;

    public static final String BUNDLE_STATE_SCORE = "BUNDLE_STATE_SCORE";
    public static final String BUNDLE_STATE_QUESTION = "BUNDLE_STATE_QUESTION";

    @Override
    /*
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mRemainingQuestionCount);
    }

     */


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        mRemainingQuestionCount = 2;

        mgame_activity_textview_question = findViewById(R.id.game_activity_textview_question);
        mgame_activity_button_1 = findViewById(R.id.game_activity_button_1);
        mgame_activity_button_2 = findViewById(R.id.game_activity_button_2);
        mgame_activity_button_3 = findViewById(R.id.game_activity_button_3);
        mgame_activity_button_4 = findViewById(R.id.game_activity_button_4);


        // Use the same listener for the four buttons.
        // The view id value will be used to distinguish the button triggered
        mgame_activity_button_1.setOnClickListener(this);
        mgame_activity_button_2.setOnClickListener(this);
        mgame_activity_button_3.setOnClickListener(this);
        mgame_activity_button_4.setOnClickListener(this);


        /*
        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mRemainingQuestionCount = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mScore = 0;
            mRemainingQuestionCount = 4;
        }

         */

        mCurrentQuestion = mQuestionBank.getCurrentQuestion();
        displayQuestion(mCurrentQuestion);

        Log.d("cyprien", "onCreate2() called");
    }

    private void displayQuestion(final Questions question) {
        mgame_activity_textview_question.setText(question.getQuestion());
        mgame_activity_button_1.setText(question.getChoiceList().get(0));
        mgame_activity_button_2.setText((question.getChoiceList().get(1)));
        mgame_activity_button_3.setText(question.getChoiceList().get(2));
        mgame_activity_button_4.setText(question.getChoiceList().get(3));
    }

    public QuestionBank generateQuestions (){
        Questions question1 = new Questions(
                "Who is the creator of Android?",
                Arrays.asList(
                        "Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"
                ),
                0
        );

        Questions question2 = new Questions(
                "When did the first man land on the moon?",
                Arrays.asList(
                        "1958",
                        "1962",
                        "1967",
                        "1969"
                ),
                3
        );

        Questions question3 = new Questions(
                "What is the house number of The Simpsons?",
                Arrays.asList(
                        "42",
                        "101",
                        "666",
                        "742"
                ),
                3
        );

        return new QuestionBank(Arrays.asList(question1, question2, question3));
    }

    @Override
    public void onClick(View view) {
        int index;

        if (view == mgame_activity_button_1) {
            index = 0;
        } else if (view == mgame_activity_button_2) {
            index = 1;
        } else if (view == mgame_activity_button_3) {
            index = 2;
        } else if (view == mgame_activity_button_4) {
            index = 3;
        } else {
            throw new IllegalStateException("Unknown clicked view : " + view);
        }

        if (index == mQuestionBank.getCurrentQuestion().getAnswerIndex())
        {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            mScore++;
        }else{
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }

        mRemainingQuestionCount--;

        if (mRemainingQuestionCount > 0) {
            mCurrentQuestion = mQuestionBank.getNextQuestion();
            displayQuestion(mCurrentQuestion);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Well done!")
                    .setMessage("Your score is " + mScore)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    })
                    .create()
                    .show();
        }

    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d("cyprien", "onDestroy2() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("cyprien", "onStop2() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("cyprien", "onResume2() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("cyprien", "onStart2() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("cyprien", "onPause2() called");
    }
}