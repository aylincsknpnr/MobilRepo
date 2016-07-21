package com.example.aylin.avukep.Haber;

/**
 * Created by aylin on 15.06.2016.
 */
public class HaberCaller extends Thread {
        public HaberSoap hs;
        public Integer  a;
        public void run(){
            try{
                hs=new HaberSoap();
                String resp=hs.Call(a);
                HaberFragment.rslt=resp;
            }catch(Exception ex)
            {
                HaberFragment.rslt=ex.toString();

            }
        }
    }

