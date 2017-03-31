package com.vissionarray.shabbirhussain.newchilsacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONObject;

public class NewsDetails extends AppCompatActivity {

    JSONObject jsonObject, jsonObject1;
    JSONArray news;

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);



        Bundle b = getIntent().getExtras();
        String s = b.getString("url");



        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(s);

//            Ion.with(this)
//                    .load(imageURL)
//                    .withBitmap()
//                    .error(R.mipmap.sh)
//                    .intoImageView(imageView);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition( R.anim.fade_in, R.anim.fade_out );
    }
}
