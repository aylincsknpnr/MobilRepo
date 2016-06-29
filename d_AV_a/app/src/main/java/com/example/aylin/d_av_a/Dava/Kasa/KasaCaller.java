package com.example.aylin.d_av_a.Dava.Kasa;

import com.example.aylin.d_av_a.Duyuru.DuyuruFragment;
import com.example.aylin.d_av_a.Duyuru.DuyuruSoap;

/**
 * Created by aylin on 29.06.2016.
 */
public class KasaCaller extends Thread {
        public KasaSoap ks;
        public Integer a,c,f;
        public String b;
        public Long d,e;
        public void run(){
            try{
                ks=new KasaSoap();
                String resp=ks.Call(a,b,c,d,e,f);
                KasaFragment.rslt=resp;
            }catch(Exception ex)
            {KasaFragment.rslt=ex.toString();

            }
        }
    }
