package com.example.aylin.avukep.Kep;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aylin.avukep.BuroAvukat.BuroAvAdapter;
import com.example.aylin.avukep.BuroAvukat.BuroAvukatCaller;
import com.example.aylin.avukep.R;
import com.example.aylin.avukep.User.First_Fragment;
import com.example.aylin.avukep.User.UserInfoDatabaseHelper;

import java.util.ArrayList;

/**
 * Created by aylin on 29.06.2016.
 */
public class KepFragment extends android.support.v4.app.Fragment implements ClickListener {
        String[] part1;
        String[] part2;
        String[] part3;
        Boolean check=false;
        public TextView detay;
        Button detail,duyuru,haber;
        public static String rslt = "";
       private static KepAdapter adapter;
        private RecyclerView.LayoutManager layoutManager;
        private static RecyclerView recyclerView;
        KepSoap kes;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.kep_fragment, container, false);
            recyclerView = (RecyclerView) v.findViewById(R.id.kep_recycler_view);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            kes=new KepSoap();
            try {
                try {

                    rslt = "START";
                    KepCaller kc = new KepCaller();
                    kc.a = "15833927042";
                    kc.join();
                    kc.start();
                    while (rslt == "START") {
                        try {
                            Thread.sleep(10);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    part1=kes.returnGonderen();
                    part2=kes.returnGondate();
                    part3=kes.returnTebdate();
                    System.out.println("dön bakam :"+part1[0]);
                    adapter = new KepAdapter(part1,part2,part3);
                    recyclerView.setAdapter(adapter);
                    adapter.setClickListener(this);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity().getApplicationContext(), "Başarısız Giriş", Toast.LENGTH_SHORT).show();
            }
            return v;
        }
        @Override
        public void itemClicked(View view, int position) {
            System.out.println("tik:" + position);

        }
    }

