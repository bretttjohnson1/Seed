package com.seed.search.seed;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    static String selectedFile="";
    Bundle queryresults[];
    public void search(View view){

        Bundle b[] = Search.performSearch("are ayyylmaos real");
        queryresults= b;
        String items[] = new String[queryresults.length];
        for(int a =0;a<queryresults.length;a++) {
            items[a] = "Page: " + queryresults[a].get("page") + "\n" + queryresults[a].get("sentence");
            queryresults[a].putString("filedirectory",selectedFile);
        }
        ArrayAdapter<String> ListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        ListView lv = (ListView)findViewById(R.id.listoutput);
        lv.setAdapter(ListAdapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent =  new Intent(this, SummaryActivity.class);
        intent.putExtra("Datum", queryresults[position]);
        startActivity(intent);
    }
    public void files(View view){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        FileSelector df = FileSelector.newInstance();
        df.show(ft, "dialogue");


    }
    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("Clicked","CLIICKCKEDIDKDID");
        if (requestCode == FileSelector.SELECT_NEW) {
            if (resultCode == RESULT_OK) {

                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("files.txt", Context.MODE_PRIVATE));
                    outputStreamWriter.write(data.getDataString());
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }*/

}
