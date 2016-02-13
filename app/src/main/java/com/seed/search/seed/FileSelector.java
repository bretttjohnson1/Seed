package com.seed.search.seed;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FileSelector#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FileSelector extends DialogFragment implements AdapterView.OnItemClickListener {

    public static FileSelector newInstance() {
        FileSelector fragment = new FileSelector();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    String[] savedfiles;
    View v;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Holo_Light_Dialog);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ArrayList <String> strings = new ArrayList <String>();
        strings.add("Add new file");
        File files = new File("files.txt");
        if(files.exists()) {
            try {
                FileInputStream fin = new FileInputStream(files);
                Scanner sc =  new Scanner(fin);

                while(sc.hasNextLine()){
                    strings.add(sc.nextLine());
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        savedfiles= new String[strings.size()];
        for(int a = 0;a< strings.size();a++)
            savedfiles[a]=strings.get(a);
        v = inflater.inflate(R.layout.fragment_file_selector, container, false);
        ListView lv = (ListView) v.findViewById(R.id.pdflist);
        ArrayAdapter<String> ListAdapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1,savedfiles);
        lv.setAdapter(ListAdapter);
        lv.setOnItemClickListener(this);
        return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
    static final int SELECT_NEW = 1;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position!=0){
            MainActivity.selectedFile=savedfiles[position];
        }else {
            Intent fileIntent = new Intent(Intent.ACTION_GET_CONTENT);
            fileIntent.setType("file/pdf"); // intent type to filter application based on your requirement
            startActivityForResult(fileIntent,SELECT_NEW);

        }

    }

}
