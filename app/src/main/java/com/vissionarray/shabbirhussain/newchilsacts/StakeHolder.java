package com.vissionarray.shabbirhussain.newchilsacts;

import android.animation.LayoutTransition;
import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StakeHolder extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    String entireFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stake_holder);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbarstakeHolder);
        setSupportActionBar(myToolbar);


        new DataFetch().execute();


        String[] arr={"StakeHolder 1","StakeHolder 2","StakeHolder 3","StakeHolder 4"};
        ListView ls=(ListView)findViewById(R.id.stakeHolderListView);
        //ArrayAdapter adapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,stakeHolder);
        //ListAdapter adapter=new myAdapter(this,stakeHolder);
        adapter =  new ArrayAdapter<String>(this, R.layout.my_adapter, R.id.my_adapter_textview, arr);
        adapter=new myAdapter(this,arr);
        ls.setAdapter(adapter);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pos=String.valueOf(position);
                Toast.makeText(StakeHolder.this,pos,Toast.LENGTH_LONG).show();
                Intent i=new Intent(StakeHolder.this,StakeHolderDetails.class);
                i.putExtra("json",entireFile);
                i.putExtra("position",pos);
                startActivity(i);

                //Animate
                overridePendingTransition( R.anim.slide_up_animation, R.anim.slide_down_animation );
            }
        });

    }

    private class DataFetch extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            InputStream is = getResources().openRawResource(R.raw.stakeholder);
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

//            if(entireFile != null){
//                try {
//                   JSONObject jsonObject=new JSONObject(entireFile);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
            return null;
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menuSearch));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        MenuItem item=menu.findItem(R.id.menuSearch);
        searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        LinearLayout searchBar = (LinearLayout) searchView.findViewById(R.id.search_bar);
        searchBar.setLayoutTransition(new LayoutTransition());
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition( R.anim.fade_in, R.anim.fade_out );
    }
}
