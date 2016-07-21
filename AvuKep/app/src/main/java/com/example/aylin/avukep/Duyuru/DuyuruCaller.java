package com.example.aylin.avukep.Duyuru;

/**
 * Created by aylin on 15.06.2016.
 */
public class DuyuruCaller extends Thread {
        public DuyuruSoap ds;
        public Integer  a;
        public void run(){
            try{
                ds=new DuyuruSoap();
                String resp=ds.Call(a);
                DuyuruFragment.rslt=resp;
            }catch(Exception ex)
            {DuyuruFragment.rslt=ex.toString();

            }
        }
    }

