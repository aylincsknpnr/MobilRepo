package com.example.aylin.serviskontrol;

/**
 * Created by aylin on 20.04.2016.
 */
public class Caller  extends Thread
{
    public CallSoap cs;
    public int  a,c;
    public String b;

    public void run(){
        try{
            cs=new CallSoap();
            String resp=cs.Call(a, b, c);
            MainActivity.rslt=resp;
        }catch(Exception ex)
        {MainActivity.rslt=ex.toString();}
    }
}