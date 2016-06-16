package com.example.aylin.d_av_a.Duyuru;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aylin.d_av_a.Dava.ClickListener;
import com.example.aylin.d_av_a.Dava.DavaAdapter;
import com.example.aylin.d_av_a.Dava.DavaSoap;
import com.example.aylin.d_av_a.Dava.DavaTabActivity;
import com.example.aylin.d_av_a.Dava.DavalarCaller;
import com.example.aylin.d_av_a.Login.Caller;
import com.example.aylin.d_av_a.R;
import com.example.aylin.d_av_a.User.UserInfoModel;
import com.example.aylin.d_av_a.User.UserModel;

/**
 * Created by aylin on 15.06.2016.
 */
public class DuyuruFragment extends Fragment implements ClickListener {
    String[] part1;
    String[] part2;
    String[] part3;
    public TextView detay;
    Button detail;
    public static String rslt = "";
    private static DuyuruAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    DuyuruSoap ds;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.duyuru_fragment,container,false);
            recyclerView = (RecyclerView) v.findViewById(R.id.duyuru_recycler_view);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            ds=new DuyuruSoap();


            try {

                try {

                    rslt = "START";
                    DuyuruCaller dc = new DuyuruCaller();
                    dc.a = 2;
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
                    System.out.println("dön bakam :"+part1[0]);
                    adapter = new DuyuruAdapter(part1,part2,part3);
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