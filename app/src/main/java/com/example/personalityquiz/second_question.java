package com.example.personalityquiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class second_question extends AppCompatActivity {

    private Button toQuestion3;
    private Button toQuestion1;
    private Button backToStart;

    QuizActivity main = new QuizActivity();
    public String retrieve[] = new String[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_question);
        retrieve = getIntent().getExtras().getStringArray("addValue");
        for(int i = 0; i < 5; i++){
            main.answerValues[i] = retrieve[i];
        }
    }

    public void onClickRadioM(View view){
        boolean checked = ((RadioButton) view).isChecked();
        String getName = "";
        if(view.getId() == R.id.tpain && checked)
        {
            getName = "T-Pain";
            main.answerValues[1] = getName;
        }else if(view.getId() == R.id.weezer && checked)
        {
            getName = "Weezer";
            main.answerValues[1] = getName;
        }else{
            getName = "Willie Nelson";
            main.answerValues[1] = getName;
        }
    }

    public void onClickMusicNextButton(View view){
        toQuestion3 = findViewById(R.id.next);
        Intent intentNext = new Intent(this, third_question.class);
        intentNext.putExtra("addValue", main.answerValues);
        startActivity(intentNext);
    }

    public void onClickMusicPrevButton(View view){
        /**/
        toQuestion1 = findViewById(R.id.next);
        Intent intentNext = new Intent(this, first_question.class);
        intentNext.putExtra("addValue", main.answerValues);
        startActivity(intentNext);
    }

    public void onClickMusicReset(View view){
        backToStart = findViewById(R.id.buttonBegin);
        Intent intentRestart = new Intent(this, QuizActivity.class);
        startActivity(intentRestart);
    }

    public void onSubmitMusic(View view){
        if((!main.answerValues[0].isEmpty()) && (!main.answerValues[1].isEmpty()) && (!main.answerValues[2].isEmpty()) &&
                (!main.answerValues[3].isEmpty()) && (!main.answerValues[4].isEmpty()) ){
            String results;

            int nerd = 0;
            int mild = 0;
            int not = 0;

            for(int i = 0; i < 5; i++){
                String check = main.answerValues[i];
                switch (check){
                    case "Ghostbusters":
                    case "Weezer":
                    case "The Simpsons":
                    case "Reading":
                    case "Iron Man":
                        nerd++;
                        break;
                    case "The Dark Knight":
                    case "Willie Nelson":
                    case "House":
                    case "Hiking":
                    case "Sherlock Holmes":
                        mild++;
                        break;
                    default: not++;

                }
            }
            if(nerd > 2)
            {
                results = "You are the optimal amount of Nerd, good job!";
            }else if(mild > 2)
            {
                results = "You are mildly nerdy, but there is still hope for you";
            }else if(nerd == 2 && mild == 2){
                results = "You are nice and nerdy, but with some non-nerdy tendencies";
            }else if(nerd == 2 && not == 2){
                results = "You make an effort to be nerdy, but you should realize nerdiness comes from within";
            }else if(mild == 2 && not == 2){
                results = "You have some nerd-ish traits, but there is much room for improvement";
            } else
                results = "You aren't really nerdy enough, check this movie called Star Wars and your" +
                        "life will start heading in the right direction";

            AlertDialog alertDialog = new AlertDialog.Builder(second_question.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage(results);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Reset",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            backToStart = findViewById(R.id.buttonBegin);
                            Intent intentRestart = new Intent(second_question.this, QuizActivity.class);
                            startActivity(intentRestart);
                        }
                    });
            alertDialog.show();
        }else
        {
            AlertDialog alertDialog = new AlertDialog.Builder(second_question.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("You haven't answered all the questions. Go through the quiz" +
                    " and select answer from each question so we can give you your results.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

    }

}
