package com.example.aylin.avukep.BuroAvukat;
/**
 * Created by aylin on 20.05.2016.
 */
public class BuroAvukatCaller  extends Thread {
        public BuroAvukatSoap bas;
        public String one;
        public  Integer two;

        public void run(){
            try{
                bas=new BuroAvukatSoap();
                String resp=bas.Call(one, two);
                BuroAvukatFragment.rslt=resp;
            }catch(Exception ex)
            {BuroAvukatFragment.rslt=ex.toString();}
        }
    }