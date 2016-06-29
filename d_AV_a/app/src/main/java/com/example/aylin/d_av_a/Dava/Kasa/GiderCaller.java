package com.example.aylin.d_av_a.Dava.Kasa;

/**
 * Created by aylin on 29.06.2016.
 */
public class GiderCaller extends Thread {
        public GiderSoap gs;
        public Integer g,m,l;
        public String h;
        public Long j,k;
        public void run(){
            try{
                gs=new GiderSoap();
                String resp=gs.Call(g,h,m,j,k,l);
                GiderFragment.rslt=resp;
            }catch(Exception ex)
            {GiderFragment.rslt=ex.toString();

            }
        }
    }
