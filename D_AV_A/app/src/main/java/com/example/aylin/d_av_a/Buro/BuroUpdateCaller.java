package com.example.aylin.d_av_a.Buro;

/**
 * Created by aylin on 20.05.2016.
 */
public class BuroUpdateCaller extends Thread {

        public BuroUpdateSoap bus;
        public String one,two,three,four,five,six,seven;

        public void run(){
            try{
                bus=new BuroUpdateSoap();
                String resp=bus.Call(one, two, three, four, five, six, seven);
                //Buro_Fragment.rslt2=resp;
            }catch(Exception ex)
            {/*Buro_Fragment.rslt2=ex.toString();*/}
        }
    }

