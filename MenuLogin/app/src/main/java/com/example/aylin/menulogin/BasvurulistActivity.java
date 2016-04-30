package com.example.aylin.menulogin;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by aylin on 30.04.2016.
 */
public class BasvurulistActivity extends Activity {
    Basvuru_Fragment bf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        ListView listView=(ListView)findViewById(R.id.listv);
        ViewGroup headerView=(ViewGroup)getLayoutInflater().inflate(R.layout.header, listView,false);
        listView.addHeaderView(headerView);
        ArrayList<String> items=bf.items_list;
        String[] items2 = new String[bf.items_list.size()];
        items2 = bf.items_list.toArray(items2);
        ListAdapter adapter=new ListAdapter(this, R.layout.row_layout, R.id.txtmodel,  items2);
        listView.setAdapter(adapter);
    }

}
