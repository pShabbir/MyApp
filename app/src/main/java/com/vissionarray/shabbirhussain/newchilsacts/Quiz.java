package com.vissionarray.shabbirhussain.newchilsacts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Quiz extends AppCompatActivity {
    String entireFile,answer;
    Boolean test;
    JSONObject jsonObject;
    JSONArray jsonArray;
    JSONObject c;
    //String score;
    int start=0;
    int score;
    RadioGroup rgp;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        sharedPref();

        ////////////////////////////////
        //Coverting json file into string representation
        InputStream is = getResources().openRawResource(R.raw.quiz);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        entireFile = "";
        try {
            while((line = br.readLine()) != null) { // <--------- place readLine() inside loop
                entireFile += (line + "\n"); // <---------- add each line to entireFile
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //////////////////////////////////////////////

        displayQuestion(start);


    }

    public void option1answer(View view)throws Exception{
        String ans=c.getString("answer");
        TextView option1=(TextView)findViewById(R.id.option1);
        String s = option1.getText().toString();
        if(s.equals(ans))
        {
            dispayScore();
        }
        advanceQuestion();
    }

    public void option2answer(View view) throws Exception{
        String ans=c.getString("answer");
        TextView option2=(TextView)findViewById(R.id.option2);
        String s = option2.getText().toString();
        if(s.equals(ans))
        {
            dispayScore();
        }
        advanceQuestion();
    }

    /////////////////////////////////
    //This function parse the question and display it
    public void displayQuestion(int start){
        try{
            jsonObject=new JSONObject(entireFile);
            jsonArray=jsonObject.getJSONArray("quiz");
            c=jsonArray.getJSONObject(start);
            //Displaying Question
            TextView qu=(TextView)findViewById(R.id.questionTextView);
            qu.setText(c.getString("question"));
            //Displaying Optons
            TextView r1=(TextView) findViewById(R.id.option1);
            r1.setText(c.getString("option1"));

            TextView r2=(TextView) findViewById(R.id.option2);
            r2.setText(c.getString("option2"));

            answer = c.getString("answer");

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void sharedPref(){

        SharedPreferences mprefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!mprefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here
            Toast.makeText(this,"Hhhahahahaha",Toast.LENGTH_LONG).show();
            SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("key", score);
            editor.commit();

            // mark first time has runned.
            SharedPreferences.Editor editor2 = mprefs.edit();
            editor2.putBoolean("firstTime", true);
            editor2.commit();
        }

    }
    ////////////////////////////////////////////
    //Displaying Score
    public void dispayScore() throws Exception{

        int i=score;
        i++;
        String sco=String.valueOf(i);
        TextView t=(TextView)findViewById(R.id.scoreTextView);
        t.setText(sco);
        score++;
    }
    //Moving to next question
    public void advanceQuestion(){


        if(start < 2){
            start++;
            displayQuestion(start);
        }
        else {
            Toast.makeText(this,"Quiz is Completed",Toast.LENGTH_LONG).show();
            SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int score2 = prefs.getInt("key", 0); //0 is the default value
            if(score > score2)
            {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("key", score);
                editor.commit();
            }
            else {
            }
            Toast.makeText(this,String.valueOf(score2),Toast.LENGTH_LONG).show();
            Intent i=new Intent(this,QuizEndingActivity.class);
            i.putExtra("score",String.valueOf(score));
            i.putExtra("highScore",score2);

            startActivity(i);
            finish();
        }
    }


    @Override
    public void onBackPressed() {

    }
}

