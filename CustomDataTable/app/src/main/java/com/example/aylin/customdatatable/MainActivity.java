package com.example.aylin.customdatatable;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Model> basvuruList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basvuruList = new ArrayList<Model>();
        ListView lview = (ListView) findViewById(R.id.listview);
        listviewAdapter adapter = new listviewAdapter(this, basvuruList);
        lview.setAdapter(adapter);

        populateList();

        adapter.notifyDataSetChanged();

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String id2 = ((TextView) view.findViewById(R.id.id2)).getText().toString();
                String call = ((TextView) view.findViewById(R.id.call)).getText().toString();
                String av = ((TextView) view.findViewById(R.id.av)).getText().toString();
                String sicil = ((TextView) view.findViewById(R.id.sicil)).getText().toString();

                Toast.makeText(getApplicationContext(),
                        "İd : " + id2 + "\n"
                                + "Arama Durumu : " + call + "\n"
                                + "Avukat : " + av + "\n"
                                + "Sicil No : " + sicil, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateList() {

        Model item1, item2, item3, item4, item5;

        item1 = new Model("1", "Aranabilir ", "Yasemin ", "16011");
        basvuruList.add(item1);

        item2 = new Model("2", "Aranabilir", "Ekin", "45663");
        basvuruList.add(item2);

    }
}
