package com.vissionarray.shabbirhussain.newchilsacts;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Locale;

public class StakeHolderDetails extends AppCompatActivity {
    String[] arr=new String[100];
    private String info;
    private JSONObject data;
    private String phone,email,image;
    float lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stake_holder_details);

        Bundle bundle=getIntent().getExtras();
        String jsonfile=bundle.getString("json");
        String getPosition=bundle.getString("position");


        try {

            JSONObject jsonObject=new JSONObject(jsonfile);
            JSONArray stakedetails=jsonObject.getJSONArray("StakeHolder");
             data=stakedetails.getJSONObject(Integer.parseInt(getPosition));

             lat=Integer.parseInt(data.getString("lat"));
             lon=Integer.parseInt(data.getString("long"));
             phone=data.getString("phone");
             email=data.getString("email");
            image=data.getString("image");
            arr[0]=email;

            TextView t1=(TextView)findViewById(R.id.stakeName);
            t1.setText(data.getString("name"));

            TextView t2=(TextView)findViewById(R.id.stakeAddress);
            t2.setText(data.getString("address"));

//            TextView t3=(TextView)findViewById(R.id.stakeContactInfo);
//            t3.setText(data.getString("contact"));

            String s=data.getString("name")+"\n\n"+"Address:\n"+data.getString("address")+"\n\n"+"Contact:\n"+data.getString("contact");
            info=new String(s);

        }catch (Exception e){
            e.printStackTrace();
        }
        ImageView stakeHolderImage=(ImageView)findViewById(R.id.stakeHolderImage);
        String uri = "@drawable/splash";  // where myresource (without the extension) is the file
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        stakeHolderImage.setImageResource(imageResource);

        ImageView img=(ImageView)findViewById(R.id.myMap);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Image is clicked",Toast.LENGTH_SHORT).show();
               // String uri="geo:0,0?q="+lat+","+lon+"(My Location)";//34.99,-106.61(Treasure)";
                //String uri="geo:"+lat+","+lon;
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", lat, lon);
               // Intent intent = new Intent(Intent.ACTION_VIEW);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                //intent.setData(Uri.parse(uri));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });
        //geo:47.6,-122.3
    }

    public void shareContent(View view){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, info);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void mailMe(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, arr);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public void makeCall(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }
    }

    //When user swipe right to left activity is define here
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition( R.anim.fade_in, R.anim.fade_out );
    }



}
