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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    private ImageView searchActs;
    private EditText txt;
    private Button btn;
    private String entireFile;
    private JSONObject jsonObject;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Providing the toolbar on top of casestudy list to facilitates the searching of laws.
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbarchildActs);
        setSupportActionBar(myToolbar);

        //This method runs a thread on background that is used to load the case study json file and creates a string representation of json file.
        new LoadJson().execute();

        //String array containing the name of child acts

        String[] arr={"The Child Labour (Prohibition and Regulation) Amendment Act, 2016","Advisory for eliminating of Corporal Punishment in Schools",
                "Amendment Proposed in Immoral Traffic Prevention Act 1956","Bonded Labour system (abolition)  Act ,1976",
                "The Factories Act","Prenatural diagnostic techniques (regulation and prevention of misuse) act 1994",
                "The infant Milk Substitute Act, 2003","Juvenile  Justice (Care and Protection of Children) Act, 2015",
                "Act 9","National Guidelines on Infant and Young Child Feeding",
                "National Commission for Protection of Child Rights","Guardians and Wards Act, 1890",
                "The Immoral Traffic (Prevention) Act, 1956","Scheduled Castes and Tribes (Prevention of Atrocities) Act, 1989",
                "Hindu Adoption and Maintenance Act, 1956","Right to Food Legislation and Children",
                "The Right of Children to Free and Compulsory Education Act "};

        //Getting id of the listView in the variable 'ls'
        ListView ls=(ListView)findViewById(R.id.listView);

        //An ArrayAdapter<String> adapter that takes the string array and display the list as per my_adapter layout
         adapter =  new ArrayAdapter<String>(this, R.layout.my_adapter, R.id.my_adapter_textview, arr);
         adapter=new myAdapter(this,arr);
         ls.setAdapter(adapter);

        //Setting listner on the list item
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pos=String.valueOf(position);
                Toast.makeText(MainActivity.this,pos,Toast.LENGTH_LONG).show();
                Intent i=new Intent(MainActivity.this,ChildActsDetails.class);
                i.putExtra("json",entireFile);
                i.putExtra("position",pos);
                startActivity(i);
                //Animate
                overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
            }
        });

    }

    private class LoadJson extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            InputStream is = getResources().openRawResource(R.raw.acts);
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

            if(entireFile != null){
                try {
                     jsonObject=new JSONObject(entireFile);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
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
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
