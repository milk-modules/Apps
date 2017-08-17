package edu.rit.se.milk.demoapp08;

import android.sax.StartElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinner = (Spinner)findViewById(R.id.spinner);
        final String[] spinnerItems = new String[]{
                "Breaking News",
                "World News",
                "Local News",
                "Sports News"
        };
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems);
        spinner.setAdapter(adapter);

        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        populateNews("Breaking News");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?>arg0, View view, int arg2, long arg3) {
                populateNews(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void populateNews(String type){
        String[] newsItems = new String[15];

        for(int i=0;i<15;i++){
            newsItems[i] = type + " " + i;
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, newsItems);
        listView.setAdapter(adapter);
    }
}
