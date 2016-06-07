package com.example.aylin.d_av_a.Ajanda;

/**
 * Created by aylin on 06.06.2016.
 */
public class CalenderCall extends Thread
{
    public CalenderCallSoap cs;
    public int  a,c;
    public String b;

    public void run(){
        try{
            cs=new CalenderCallSoap();
            String resp=cs.Call(a, b, c);
            MainFragment.rslt=resp;
        }catch(Exception ex)
        {MainFragment.rslt=ex.toString();}
    }
}
