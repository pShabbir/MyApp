package com.vissionarray.shabbirhussain.newchilsacts;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/avenir.otf");

        TextView[] arr=new TextView[18];
        for(int i=0;i<18;i++){
            String uri = "a"+String.valueOf(i);
            int resourceId = this.getResources().getIdentifier(uri, "id", this.getPackageName());
            arr[i]=(TextView)findViewById(resourceId);
            arr[i].setTypeface(custom_font);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
