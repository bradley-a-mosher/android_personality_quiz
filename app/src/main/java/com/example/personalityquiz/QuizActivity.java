package com.example.personalityquiz;

import android.app.Application;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.Button;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class QuizActivity extends AppCompatActivity {

    public Integer string = R.layout.activity_first_question;
    public Integer f = 4;
    public String answerValues[] = {"", "", "", "", ""};
    public Integer nerd = 0;
    public Integer mild = 0;
    public Integer not = 0;



    public void setAnswers(String[] name){



    }
    public String results(String i){
        String result;
        if(i == "Nerd"){
            return result = "You are the optimal amount of nerd, congrats!";
        }else if(i == "Mild"){
            return result = "You are an average nerd, but there's hope for you yet!";
        }else
            return result = "You aren't much of a nerd, but maybe one day.";


    }

    public Integer counter(String s){

        return 0;
    }
    private Button startButton;
    public Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            string = savedInstanceState.getInt("layoutId", R.layout.activity_first_question);
            f = savedInstanceState.getInt("f");
        }
        setContentView(R.layout.activity_quiz);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        savedInstanceState.putInt("layoutId", string);
        savedInstanceState.putInt("f", f);

    }

    private String someVariable;

    public String getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(String someVariable) {
        this.someVariable = someVariable;
    }

    public void onClickStartButton(View view){
        startButton = findViewById(R.id.buttonBegin);
        Intent intentStart = new Intent(this, first_question.class);
        intentStart.putExtra("addValue", answerValues);
        startActivity(intentStart);
    }


}
