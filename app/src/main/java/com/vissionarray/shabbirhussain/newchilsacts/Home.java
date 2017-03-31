package com.vissionarray.shabbirhussain.newchilsacts;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    private Boolean isFabOpen = false;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;

    Bitmap acts,caseStudy,stake,quiz,credits,ack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //This method calls method that animates the moving image in the background
        callAnimate();

    }
    public void callAnimate(){

        //Getting id of imageView  of moving in background
        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_one);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_two);

        //These commands facilitates the moving of the background image in HOME screen
        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * progress;
                backgroundOne.setTranslationX(translationX);
                backgroundTwo.setTranslationX(translationX - width);
            }
        });
        animator.start();
    }







    //This method calls Activity that display the child acts lists.
    public void childActs(View view){
        Intent i=new Intent(Home.this,MainActivity.class);
                startActivity(i);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
    }
    //This method calls Activity that display the case study  lists.
    public void caseStudy(View view){
        Intent i=new Intent(Home.this,CaseStudy.class);
                startActivity(i);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

    }
    //This method calls Activity that display the stakeholder lists.
    public void stakeHolder(View view){
        Intent i=new Intent(Home.this,StakeHolder.class);
                startActivity(i);
        overridePendingTransition( R.anim.slide_up_animation, R.anim.slide_down_animation );

    }
    //This method calls Activity that opens the quiz activity to play.
    public void Quiz(View view){
        Intent i=new Intent(Home.this,QuizSplash.class);
                startActivity(i);
        overridePendingTransition(R.anim.flip_in, R.anim.flip_out);

    }

    //This method calls Activity that  display the credit page
    public void credit(View view){
        Intent i=new Intent(Home.this,Credits.class);
        overridePendingTransition(R.anim.flip_in, R.anim.flip_out);
        startActivity(i);

    }

    //This method calls Activity that display the acknowledgement page
    public void ack(View view){
        Toast.makeText(this,"This is Acknowledgement Button",Toast.LENGTH_LONG).show();
       // finish();
    }

    //This method calls Activity thats display latest news.
    public void news(View view){
        Intent i=new Intent(this,News.class);
        overridePendingTransition(R.anim.flip_in, R.anim.flip_out);
        startActivity(i);
    }

    //This method calls Activity that take us to web page where we can connect to NCPCR.
    public void contactUs(View view){
       Intent i=new Intent(Home.this,Here.class);
        overridePendingTransition(R.anim.flip_in, R.anim.flip_out);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {

        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
