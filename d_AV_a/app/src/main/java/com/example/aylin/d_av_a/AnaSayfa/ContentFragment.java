package com.example.aylin.d_av_a.AnaSayfa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aylin.d_av_a.Duyuru.DuyuruAdapter;
import com.example.aylin.d_av_a.Duyuru.DuyuruCaller;
import com.example.aylin.d_av_a.Duyuru.DuyuruSoap;
import com.example.aylin.d_av_a.R;

/**
 * Created by Admin on 04-06-2015.
 */
public class ContentFragment extends Fragment {
    String[] part1;
    String[] part2;
    String[] part3;
    String[] part4;
    String[] part5;
    String[] part6;
    String[] part7;
    public static String rslt = "";
    HomeSoap hs;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_fragment,container,false);
        hs=new HomeSoap();
        try {

            try {

                rslt = "START";
                HomeCaller hc = new HomeCaller();
                hc.a="15833927042";
                hc.b="2016.06.15";
                hc.join();
                hc.start();
                while (rslt == "START") {
                    try {
                        Thread.sleep(10);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getApplicationContext(), "Başarısız Giriş", Toast.LENGTH_SHORT).show();
        }
        return v;
    }
}
