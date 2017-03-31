package com.vissionarray.shabbirhussain.newchilsacts;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ChildActsDetails extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener{

    private String info;
    TextView title,introduction,punishment,reference;
    //
    FloatingActionButton button;
    private GestureDetectorCompat gestureDetector;
    SeekBar seekBar1;
    FrameLayout frameLayout;
    Switch aSwitch;
    ViewGroup lt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_acts_details);
        Bundle bundle=getIntent().getExtras();
        String jsonfile=bundle.getString("json");
        String getPosition=bundle.getString("position");

        try {
            JSONObject jsonObject=new JSONObject(jsonfile);
            JSONArray acts=jsonObject.getJSONArray("Acts");
            JSONObject c=acts.getJSONObject(Integer.parseInt(getPosition));
            //data = c.getString("name");

             title=(TextView)findViewById(R.id.title);
            title.setText(c.getString("title"));

             introduction=(TextView) findViewById(R.id.introduction);
            introduction.setText(c.getString("introduction"));

             punishment=(TextView)findViewById(R.id.punishment);
            //Getting the punishment and displaying it in bulleted
            String test=c.getString("punishment");
            String[] arr=test.split(";");
            test="";
            for (int i=0;i<arr.length;i++)
                test = test+arr[i]+"\n";
            //punishment.setText(c.getString("punishment"));
            punishment.setText(test);

             reference=(TextView)findViewById(R.id.reference);
            reference.setText(c.getString("reference"));

            info=new String(c.getString("introduction"));


        }catch (Exception e){

        }
        ////////////////
        //Floating Button Animation
//        final ViewGroup transitionsContainer = (ViewGroup)findViewById(R.id.transitions_container);
//        final TextView text = (TextView) transitionsContainer.findViewById(R.id.text);
//        button = (FloatingActionButton) transitionsContainer.findViewById(R.id.button);
//
//        frameLayout=(FrameLayout) findViewById(R.id.frameLayout);
//
//
//        button.setOnClickListener(new View.OnClickListener() {
//
//            boolean visible;
//
//            @Override
//            public void onClick(View v) {
//                TransitionManager.beginDelayedTransition(transitionsContainer);
//                visible = !visible;
//                frameLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
//            }
//
//        });
        //Ends Here
        //SeekBAr Functionality
        seekBar1=(SeekBar)findViewById(R.id.seekBarChildActs);
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();

                introduction.setTextSize(progress);
                punishment.setTextSize(progress);
                reference.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //SeekBar Functionality End
        //Switch Button Functionality
        aSwitch = (Switch)findViewById(R.id.myswitchChildActs);
        aSwitch.setChecked(false);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    introduction.setTextColor(Color.WHITE);
                    punishment.setTextColor(Color.WHITE);
                    reference.setTextColor(Color.WHITE);


                    introduction.setBackgroundColor(Color.BLACK);
                    punishment.setBackgroundColor(Color.BLACK);
                    reference.setBackgroundColor(Color.BLACK);

                }
                else {

                    introduction.setTextColor(Color.BLACK);
                    punishment.setTextColor(Color.BLACK);
                    reference.setTextColor(Color.BLACK);


                    introduction.setBackgroundColor(Color.WHITE);
                    punishment.setBackgroundColor(Color.WHITE);
                    reference.setBackgroundColor(Color.WHITE);
                }
            }
        });
        //Ends Here
        //Gesture Detector Code
        this.gestureDetector = new GestureDetectorCompat(this,this);
        gestureDetector.setOnDoubleTapListener(this);

        new Handler().postDelayed(new Runnable() {

            //Initial Viewing of Float Button
            @Override
            public void run() {
                lt=(ViewGroup) findViewById(R.id.transitions_container);
                TransitionManager.beginDelayedTransition(lt);
                lt.setVisibility(View.GONE);
            }
        }, 1000);
    }

    public void shareContent(View view){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, info);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    //When user swipe right to left movement define here














    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e)
    {
        //Toast.makeText(this,"HEI",Toast.LENGTH_LONG).show();
        lt=(ViewGroup) findViewById(R.id.transitions_container);
        frameLayout=(FrameLayout) findViewById(R.id.frameLayout);
        TransitionManager.beginDelayedTransition(lt);
        if(lt.getVisibility() == View.GONE)
        {
            lt.setVisibility(View.VISIBLE);
            frameLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            lt.setVisibility(View.GONE);
            frameLayout.setVisibility(View.GONE);

        }
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
//        this.gestureDetector.onTouchEvent(event);
//        return super.onTouchEvent(event);
        super.dispatchTouchEvent(event);
        lt=(ViewGroup) findViewById(R.id.transitions_container);
        return gestureDetector.onTouchEvent(event);
//        if(lt.getVisibility() == View.GONE)
//            return gestureDetector.onTouchEvent(event);
//        else
//            return false;
    }

    public void goGone(View view){
        TransitionManager.beginDelayedTransition(lt);
        button.performClick();
        lt.setVisibility(View.GONE);
    }

}
