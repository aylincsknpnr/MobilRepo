package com.example.aylin.d_av_a.Dava.Kasa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.aylin.d_av_a.Dava.ClickListener;
import com.example.aylin.d_av_a.R;

/**
 * Created by aylin on 28.06.2016.
 */
public class GiderFragment  extends Fragment implements ClickListener {
    public static String rslt = "";
    Button gelir2, gider2;
    GiderSoap gs;
    String[] part3;
    String[] part4;
    String[] part5;
    private static GiderAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
        public GiderFragment() {
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View v= inflater.inflate(R.layout.kasa_gider_fragment, container, false);
            gelir2=(Button)v.findViewById(R.id.gelir);
            gider2=(Button)v.findViewById(R.id.gider);
            gelir2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    KasaFragment kasaFragment = new KasaFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransactionkasa = getFragmentManager().beginTransaction();
                    fragmentTransactionkasa.replace(R.id.frame2, kasaFragment);
                    fragmentTransactionkasa.commit();
                }
            });
            gider2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    GiderFragment giderFragment = new GiderFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransactiongider = getFragmentManager().beginTransaction();
                    fragmentTransactiongider.replace(R.id.frame2, giderFragment);
                    fragmentTransactiongider.commit();
                    System.out.println("giderdeyizz");
                }
            });


            recyclerView = (RecyclerView) v.findViewById(R.id.kasagider_recycler_view);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            gs = new GiderSoap();
            try {
                try {

                    rslt = "START";
                    GiderCaller gc = new GiderCaller();
                    gc.g = 2;
                    gc.h = "15833927042";
                    gc.m = 0;
                    gc.j = 3995590822L;
                    gc.k = 1000883L;
                    gc.l = 2;
                    gc.join();
                    gc.start();
                    while (rslt == "START") {
                        try {
                            Thread.sleep(10);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    part3 = gs.returnAciklamak();
                    part4 = gs.returnKDate();
                    part5=  gs.returnPara();
                    adapter = new GiderAdapter(part3, part4, part5);
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