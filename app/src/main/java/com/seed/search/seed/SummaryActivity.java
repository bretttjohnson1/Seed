package com.seed.search.seed;

import android.app.Activity;
import android.os.Bundle;

public class SummaryActivity extends Activity {

    Bundle data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Bundle datum = getIntent().getExtras();
        data = (Bundle)datum.get("Datum");

    }

}
