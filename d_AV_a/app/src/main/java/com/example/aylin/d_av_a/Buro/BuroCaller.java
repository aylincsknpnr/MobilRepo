package com.example.aylin.d_av_a.Buro;

/**
 * Created by aylin on 18.05.2016.
 */
public class BuroCaller extends Thread {
    public BuroSoap bs;
    public String  a;

    public void run(){
        try{
            bs=new BuroSoap();
            String resp=bs.Call(a);
            Buro_Fragment.rslt=resp;
        }catch(Exception ex)
        {Buro_Fragment.rslt=ex.toString();}
    }
}
