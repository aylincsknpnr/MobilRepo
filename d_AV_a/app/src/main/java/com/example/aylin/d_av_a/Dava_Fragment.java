package com.example.aylin.d_av_a;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by aylin on 23.05.2016.
 */
public class Dava_Fragment extends android.support.v4.app.Fragment {
    EditText BirimAdi, TarihSaat, DosyaNo, DosyaTurKod, DosyaVekilleri, Durumu, YORA, DERECE;
    String[] part;
    String[] values;
    public static String rslt = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dava_fragment, container, false);
        try {
            BirimAdi = (EditText) v.findViewById(R.id.BirimAdi);
            TarihSaat = (EditText) v.findViewById(R.id.TarihSaat);
            DosyaNo = (EditText) v.findViewById(R.id.DosyaNo);
            DosyaTurKod = (EditText) v.findViewById(R.id.DosyaTurKod);
            DosyaVekilleri = (EditText) v.findViewById(R.id.DosyaVekilleri);
            Durumu = (EditText) v.findViewById(R.id.Durumu);
            YORA = (EditText) v.findViewById(R.id.YORA);
            DERECE = (EditText) v.findViewById(R.id.DERECE);
            try {
                rslt = "START";
                DavalarCaller dc = new DavalarCaller();
                dc.a = "15833927042";
                dc.b = 0;
                dc.c = 2;
                dc.join();
                dc.start();
                while (rslt == "START") {
                    try {
                        Thread.sleep(10);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                System.out.println("Result:" + rslt);
                rslt = rslt.replace("[", " ").replace("]", " ");
                part = rslt.split("  ,  ");
                for (int i = 0; i < part.length; i++) {
                    values = part[0].split("; ");
                }
                BirimAdi.setText(values[1]);
                TarihSaat.setText(values[2]);
                DosyaNo.setText(values[3]);
                DosyaTurKod.setText(values[4]);
                DosyaVekilleri.setText(values[5]);
                Durumu.setText(values[6]);
                YORA.setText(values[7]);
                DERECE.setText(values[1]);
                Toast.makeText(getActivity().getApplicationContext(), values[0], Toast.LENGTH_SHORT).show();
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