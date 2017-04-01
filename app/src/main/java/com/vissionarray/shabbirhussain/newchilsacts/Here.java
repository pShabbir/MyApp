package com.vissionarray.shabbirhussain.newchilsacts;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Here extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_here);

        //This method is used to check internet connection before opening app
//        if(!checkConnection())
//        {
//            Toast.makeText(this,"No internet connection",Toast.LENGTH_LONG).show();
//            Intent i=new Intent(Here.this,Home.class);
//            startActivity(i);
//            finish();
//        }
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("http://www.ncpcr.gov.in/user_complaints.php");


    }

    public boolean checkConnection(){
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo == null){
            return false;
        }
        else{
            return true;
        }
    }
}
