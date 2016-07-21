package com.example.aylin.avukep.Haber;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.aylin.avukep.Duyuru.ClickListener;
import com.example.aylin.avukep.Duyuru.DuyuruFragment;
import com.example.aylin.avukep.R;

/**
 * Created by aylin on 15.06.2016.
 */
public class HaberFragment extends Fragment implements ClickListener {
    String[] part1;
    String[] part2;
    String[] part3;
    Boolean check=false;
    public TextView detay;
    Button detail,duyuru,haber;
    public static String rslt = "";
    private static HaberAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    HaberSoap ds;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.haber_fragment, container, false);
            recyclerView = (RecyclerView) v.findViewById(R.id.haber_recycler_view);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            ds=new HaberSoap();
            duyuru=(Button)v.findViewById(R.id.duyuru);
            haber=(Button)v.findViewById(R.id.haber);
            duyuru.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    DuyuruFragment duyuruFragment = new DuyuruFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransactionduyuru = getFragmentManager().beginTransaction();
                    fragmentTransactionduyuru.replace(R.id.frame, duyuruFragment);
                    fragmentTransactionduyuru.commit();
                }
            });
            haber.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    HaberFragment haberFragment = new HaberFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransactionhaber = getFragmentManager().beginTransaction();
                    fragmentTransactionhaber.replace(R.id.frame, haberFragment);
                    fragmentTransactionhaber.commit();
                }
            });
            try {
                try {

                    rslt = "START";
                    HaberCaller dc = new HaberCaller();
                    dc.a = 1;
                    dc.join();
                    dc.start();
                    while (rslt == "START") {
                        try {
                            Thread.sleep(10);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    part1=ds.returnBaslik();
                    part2=ds.returnDDate();
                    part3=ds.returnDetay();

                    adapter = new HaberAdapter(part1,part2,part3);
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