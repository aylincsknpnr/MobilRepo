package com.example.aylin.d_av_a;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by aylin on 20.05.2016.
 */
public class BuroAvukatFragment extends android.support.v4.app.Fragment {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<BuroAvModel> data;
    static View.OnClickListener myOnClickListener;

        TextView tc, ad, baro, barosicil, tbbno, tel, tc2, ad2, baro2, barosicil2, tbbno2, tel2;
        TextView info;
        String [] part;
        String [] values;
        String [] values2;
        First_Fragment ff;
        public ImageView img;
        public static String rslt="";
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.buroav_fragment,container,false);
            recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            try{
                ad=(TextView)v.findViewById(R.id.adsoyad);
                img=(ImageView)v.findViewById(R.id.imageView);
                try {
                    rslt = "START";
                    BuroAvukatCaller bac = new BuroAvukatCaller();
                    bac.one="0";
                    bac.two=2;
                    bac.join();
                    bac.start();
                    while (rslt == "START") {
                        try {
                            Thread.sleep(10);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    System.out.println("Result:" + rslt);
                   rslt=rslt.replace("[", " ").replace("]", " ");
                    part=rslt.split("\n");

                 adapter = new BuroAvAdapter(part);
                    recyclerView.setAdapter(adapter);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }catch(Exception e){
                e.printStackTrace();
                Toast.makeText(getActivity().getApplicationContext(), "Başarısız Giriş", Toast.LENGTH_SHORT).show();
            }
            return v;
        }
    }