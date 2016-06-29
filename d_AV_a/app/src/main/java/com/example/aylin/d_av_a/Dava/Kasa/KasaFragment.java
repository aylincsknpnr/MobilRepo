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
import com.example.aylin.d_av_a.Dava.Kasa.KasaAdapter;
import com.example.aylin.d_av_a.Dava.Kasa.KasaCaller;
import com.example.aylin.d_av_a.Dava.Kasa.KasaSoap;
import com.example.aylin.d_av_a.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class KasaFragment extends Fragment implements ClickListener {
    public static String rslt = "";
    Button gelir, gider;
    KasaSoap ks;
    String[] part1;
    String[] part2;
    String[] part3;
    private static KasaAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    public KasaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kasa, container, false);
        gelir = (Button) v.findViewById(R.id.gelir);
        gider = (Button) v.findViewById(R.id.gider);
        gelir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                KasaFragment kasaFragment = new KasaFragment();
                android.support.v4.app.FragmentTransaction fragmentTransactionkasa = getFragmentManager().beginTransaction();
                fragmentTransactionkasa.replace(R.id.frame2, kasaFragment);
                fragmentTransactionkasa.commit();
                System.out.println("gelirdeyimm");
            }
        });
        gider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                GiderFragment giderFragment = new GiderFragment();
                android.support.v4.app.FragmentTransaction fragmentTransactiongider = getFragmentManager().beginTransaction();
                fragmentTransactiongider.replace(R.id.frame2, giderFragment);
                fragmentTransactiongider.commit();
                System.out.println("giderdeyimmm");
            }
        });
        recyclerView = (RecyclerView) v.findViewById(R.id.kasagelir_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ks = new KasaSoap();
        try {
            try {

                rslt = "START";
                KasaCaller kc = new KasaCaller();
                kc.a = 2;
                kc.b = "15833927042";
                kc.c = 0;
                kc.d = 3995590822L;
                kc.e = 1000883L;
                kc.f = 1;
                kc.join();
                kc.start();
                while (rslt == "START") {
                    try {
                        Thread.sleep(10);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                part1 = ks.returnAciklamak();
                part2 = ks.returnKDate();
                part3=ks.returnPara();
                adapter = new KasaAdapter(part1, part2, part3);
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