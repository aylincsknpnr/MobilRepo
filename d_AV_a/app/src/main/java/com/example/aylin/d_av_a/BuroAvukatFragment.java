package com.example.aylin.d_av_a;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by aylin on 20.05.2016.
 */
public class BuroAvukatFragment extends android.support.v4.app.Fragment {

        TextView tc, ad, baro, barosicil, tbbno, tel, tc2, ad2, baro2, barosicil2, tbbno2, tel2;
        TextView info;
        String [] part;
        String [] values;
        String [] values2;
        First_Fragment ff;
        public static String rslt="";
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.buroav_fragment,container,false);
            try{
                tc=(TextView)v.findViewById(R.id.buroAd2);
                ad=(TextView)v.findViewById(R.id.buroAdres2);
                baro=(TextView)v.findViewById(R.id.buroTel12);
                barosicil=(TextView)v.findViewById(R.id.buroTel22);
                tbbno=(TextView)v.findViewById(R.id.fax2);
                tel=(TextView)v.findViewById(R.id.vergiNo2);

                tc2=(TextView)v.findViewById(R.id.buroAd21);
                ad2=(TextView)v.findViewById(R.id.buroAdres21);
                baro2=(TextView)v.findViewById(R.id.buroTel12);
                barosicil2=(TextView)v.findViewById(R.id.buroTel121);
                tbbno2=(TextView)v.findViewById(R.id.fax21);
                tel2=(TextView)v.findViewById(R.id.vergiNo2);

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
                    part=rslt.split("  ,  ");
                    for (int i=0;i<part.length;i++){
                        values=part[0].split("; ");
                        System.out.println("part::"+part[1]);
                    }

                    tc.setText(values[0]);
                    ad.setText(values[1]);
                    baro.setText(values[2]);
                    barosicil.setText(values[3]);
                    tbbno.setText(values[4]);
                    tel.setText(values[5]);

                    values2=part[1].split("; ");
                    tc2.setText(values2[0]);
                    ad2.setText(values2[1]);
                    baro2.setText(values2[2]);
                    barosicil2.setText(values2[3]);
                    tbbno2.setText(values2[4]);
                    tel2.setText(values2[5]);
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