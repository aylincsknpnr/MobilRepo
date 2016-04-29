package com.example.aylin.listviewdata;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.SCHEDULE);

        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("train", "101");
        map.put("from", "6:30 AM");
        map.put("to", "7:40 AM");
        mylist.add(map);
        map = new HashMap<String, String>();
        map.put("train", "103(x)");
        map.put("from", "6:35 AM");
        map.put("to", "7:45 AM");
        mylist.add(map);
// ...
        SimpleAdapter mSchedule = new SimpleAdapter(this, mylist, R.layout.row,
                new String[]{"train", "from", "to"}, new int[]{R.id.TRAIN_CELL, R.id.FROM_CELL, R.id.TO_CELL});
        list.setAdapter(mSchedule);
    }


}
