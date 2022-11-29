package com.example.topquiz2.controler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz2.R;
import com.example.topquiz2.model.User;

public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    private static final String SHARED_PREF_USER_INFO_NAME = "SHARED_PREF_USER_INFO_NAME";
    private static final String SHARED_PREF_USER_INFO_SCORE = "SHARED_PREF_USER_INFO_SCORE";


    private TextView mGreetingTextView;
    private EditText mNameEditText;
    private Button mPlayButton;
    private User mUser = new User(" ");

    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;

    int score = 0;


    //SharedPreferences preferences = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE); // pour stocker les valeurs quand l'application est quittee

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            // int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);

            score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            String str = "Bonjour " + getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_NAME, null) + " votre score est de " + score;
            mGreetingTextView.setText(str);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGreetingTextView = findViewById((R.id.main_textview_greeting));

        String firstName = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_NAME, null);

        if (firstName != null){
            String str = "Bonjour " + firstName + " votre score est de " + score;
            mGreetingTextView.setText(str);
            mUser.mFirstName = firstName;
        }
        mNameEditText = findViewById(R.id.main_edittext_name);
        mPlayButton = findViewById(R.id.main_button_play);
        mPlayButton.setEnabled(false);


        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                charSequence = mUser.getFirstName();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPlayButton.setEnabled(!editable.toString().isEmpty());
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSharedPreferences(SHARED_PREF_USER_INFO,MODE_PRIVATE);
                SharedPreferences preferences = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(SHARED_PREF_USER_INFO_NAME, mNameEditText.getText().toString());
                editor.apply();



                Intent gameActivityIntent;
                gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                //startActivity(gameActivityIntent);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);

            }
        });
        Log.d("cyprien", "onCreate() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("cyprien", "onDestroy() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("cyprien", "onStop() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("cyprien", "onResume() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("cyprien", "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("cyprien", "onPause() called");
    }


}