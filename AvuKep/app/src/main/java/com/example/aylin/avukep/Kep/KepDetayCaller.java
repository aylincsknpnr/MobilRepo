package com.example.aylin.avukep.Kep;

/**
 * Created by aylin on 30.06.2016.
 */
public class KepDetayCaller extends Thread
    {
        public KepDetaySoap kds;
        public String a;
        public int b;

        public void run(){
            try{
                kds=new KepDetaySoap();
                String resp=kds.Call(a,b);
                KepDetayActivity.rslt2=resp;
            }catch(Exception ex)
            {KepDetayActivity.rslt2=ex.toString();}
        }
    }
