package com.seed.search.seed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    Bundle queryresults[];
    public void search(View view){

        Bundle b[] = Search.performSearch("are ayyylmaos real");
        queryresults= b;
        String items[] = new String[queryresults.length];
        for(int a =0;a<queryresults.length;a++)
            items[a]= "Page: " + queryresults[a].get("page") + "\n" + queryresults[a].get("sentence");
        ArrayAdapter<String> ListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        ListView lv = (ListView)findViewById(R.id.listoutput);
        Intent intent = new Intent(this, SummaryActivity.class);

        lv.setAdapter(ListAdapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent =  new Intent(this, SummaryActivity.class);
        intent.putExtra("Datum",queryresults[position]);
    }
}
