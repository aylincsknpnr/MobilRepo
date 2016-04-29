package com.example.aylin.custumdatatable;

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


    private ArrayList<Model> productList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = new ArrayList<Model>();
        ListView lview = (ListView) findViewById(R.id.listview);
        listviewAdapter adapter = new listviewAdapter(this, productList);
        lview.setAdapter(adapter);

        populateList();

        adapter.notifyDataSetChanged();

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String sno = ((TextView) view.findViewById(R.id.sNo)).getText().toString();
                String product = ((TextView) view.findViewById(R.id.product)).getText().toString();
                String category = ((TextView) view.findViewById(R.id.category)).getText().toString();
                String price = ((TextView) view.findViewById(R.id.price)).getText().toString();

                Toast.makeText(getApplicationContext(),
                        "İd : " + sno + "\n"
                                + "Arama Durumu : " + product + "\n"
                                + "Avukat : " + category + "\n"
                                + "Sicil No : " + price, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateList() {

        Model item1, item2, item3, item4, item5;

        item1 = new Model("1", "Aranabilir ", "Yasemin ", "16011");
        productList.add(item1);

        item2 = new Model("2", "Aranabilir", "Ekin", "45663");
        productList.add(item2);

    }
}
