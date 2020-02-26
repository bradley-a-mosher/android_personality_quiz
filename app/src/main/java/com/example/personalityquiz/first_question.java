package com.example.personalityquiz;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.Button;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class first_question extends AppCompatActivity {

    private Button toQuestion2;
    private Button backToStart;
    private Button finalSubmit;
    private Button button;

    QuizActivity main = new QuizActivity();
    public String retrieve[] = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (savedInstanceState != null) {
            main.answerValues[0] = savedInstanceState.getString("answerValue");
        }*/
        setContentView(R.layout.activity_first_question);
        retrieve = getIntent().getExtras().getStringArray("addValue");
            for(int i = 0; i < 5; i++) {
                main.answerValues[i] = retrieve[i];
            }
    }

    /*@Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("answerValue", main.answerValues[0]);

    }*/


    public void onClickRadio(View view){
        boolean checked = ((RadioButton) view).isChecked();
        String getName = "";
        if(view.getId() == R.id.ghostbusters && checked)
        {
            getName = "Ghostbusters";
            main.answerValues[0] = getName;
        }else if(view.getId() == R.id.dark_knight && checked)
        {
            getName = "The Dark Knight";
            main.answerValues[0] = getName;
        }else{
            getName = "Die Hard";
            main.answerValues[0] = getName;
        }
    }

    public void onClickMovieNextButton(View view){

        toQuestion2 = findViewById(R.id.next);
        Intent intentNext = new Intent(this, second_question.class);
        intentNext.putExtra("addValue", main.answerValues);
        startActivity(intentNext);
    }

    public void onClickReset(View view){
        backToStart = findViewById(R.id.buttonBegin);
        Intent intentRestart = new Intent(this, QuizActivity.class);
        startActivity(intentRestart);
    }

    public void onSubmitFilm(View view){
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

            AlertDialog alertDialog = new AlertDialog.Builder(first_question.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage(results);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Reset",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            backToStart = findViewById(R.id.buttonBegin);
                            Intent intentRestart = new Intent(first_question.this, QuizActivity.class);
                            startActivity(intentRestart);
                        }
                    });
            alertDialog.show();
        }else
        {
            AlertDialog alertDialog = new AlertDialog.Builder(first_question.this).create();
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
