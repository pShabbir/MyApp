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

public class CaseStudy extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    String entireFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_study);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        //Calling the background thread
        new DataFetch().execute();

        //Creating the list view with predefine array
        String[] arr={"I was woken at four in the morning and then I had to wash the clothes, sweep and mop the floor"
                ,"Child Welfare Committee issues directions for registration of case",
                "Story of K.Sandhya from Chandupatla Village, Nalgonda",
                "Citing Muslim Personal Law Delhi Court Justifies Marriage Of A 15-Yr-Old Girl.",
                "TAMIL NADU RANKS FIRST IN IMMORAL TRAFFICKING.",
                "Initiatives pertaining to beedi work",
                "Assessment and affirmative action by a UNHCR HealtH officers",
                "Individual Cases alert UNHCR of Critical gaps in IYCF system "};
        ListView ls=(ListView)findViewById(R.id.caseStudyListView);
        //ArrayAdapter adapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arr);
        //ListAdapter adapter=new myAdapter(this,arr);
        adapter =  new ArrayAdapter<String>(this, R.layout.my_adapter, R.id.my_adapter_textview, arr);
        adapter=new myAdapter(this,arr);
        ls.setAdapter(adapter);

        //Addind listener to each list element
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pos=String.valueOf(position);
                Toast.makeText(CaseStudy.this,pos,Toast.LENGTH_LONG).show();
                Intent i=new Intent(CaseStudy.this,CaseStudyDetails.class);
                i.putExtra("json",entireFile);
                i.putExtra("position",pos);
                startActivity(i);
                //animate
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });

     ///


    }

    //This inner class is creating json file in background thread
    private class DataFetch extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            InputStream is = getResources().openRawResource(R.raw.casestudy);
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


            return null;
        }
    }
    @Override
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
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
}
