package com.vissionarray.shabbirhussain.newchilsacts;

import android.content.Context;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

public class myAdapter extends ArrayAdapter<String> {
    public myAdapter(Context context, String[] str){
        super(context,R.layout.my_adapter,str);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.my_adapter,parent,false);

        String s=getItem(position);
        TextView txt=(TextView)customView.findViewById(R.id.my_adapter_textview);
        txt.setText(s);

        return customView;
    }

}
