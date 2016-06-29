package com.example.aylin.avukep.Kep;

import com.example.aylin.avukep.BuroAvukat.BuroAvukatFragment;
import com.example.aylin.avukep.BuroAvukat.BuroAvukatSoap;

/**
 * Created by aylin on 29.06.2016.
 */
public class KepCaller extends Thread {
        public KepSoap kes;
        public String a;

        public void run(){
            try{
                kes=new KepSoap();
                String resp=kes.Call(a);
                KepFragment.rslt=resp;
            }catch(Exception ex)
            {KepFragment.rslt=ex.toString();}
        }
    }