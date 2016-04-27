package com.example.aylin.serviskontrol;

/**
 * Created by aylin on 20.04.2016.
 */
public class Caller  extends Thread
{
    public CallSoap cs;
    public String  a,b;

    public void run(){
        try{
            cs=new CallSoap();
            String resp=cs.Call(a, b);
            MainActivity.rslt=resp;
        }catch(Exception ex)
        {MainActivity.rslt=ex.toString();}
    }
}