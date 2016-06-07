package com.example.aylin.d_av_a.Login;
import android.content.Context;

/**
 * Created by aylin on 05.05.2016.
 */
public class Caller extends Thread
{
    private static Context context;
    public CallSoap cs;
    public String a,b;
    public String resp;
    public void run(){
        try{
            cs=new CallSoap();
            resp=cs.Call(a, b);
            Second_Fragment.rslt=resp;
            LoginActivity.rslt=resp;
        }catch(Exception ex)
        {Second_Fragment.rslt=ex.toString();
        }
    }
    public String ReturnValue(){
        return Third_Fragment.rslt;
    }
}
