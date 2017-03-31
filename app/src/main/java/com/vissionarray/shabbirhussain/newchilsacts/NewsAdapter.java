package com.vissionarray.shabbirhussain.newchilsacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

/**
 * Created by Shabbir Hussain on 3/29/2017.
 */

public class NewsAdapter extends ArrayAdapter<NewsItem> {
    public NewsAdapter(Context context, NewsItem[] name) {
        super(context,R.layout.news_layout,name);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.news_layout,parent,false);

        NewsItem newsItem=getItem(position);
        TextView title=(TextView)customView.findViewById(R.id.title);
        TextView des=(TextView)customView.findViewById(R.id.description);
        ImageView imageView=(ImageView)customView.findViewById(R.id.imageView);

        title.setText(newsItem.getTitle());
        des.setText(newsItem.getDescription());

        Ion.with(getContext())
                .load(newsItem.getBitmap())
                .withBitmap()
                .error(R.drawable.error)
                .intoImageView(imageView);
       // imageView.setImageBitmap(newsItem.getBitmap());

        return customView;
    }
}
