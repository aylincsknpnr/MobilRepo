package com.example.aylin.menulogin;
import android.content.Context;
import android.widget.Toast;
/**
 * Created by aylin on 20.04.2016.
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
            Third_Fragment.rslt=resp;
        }catch(Exception ex)
        {Second_Fragment.rslt=ex.toString();
        }
    }
    public String ReturnValue(){
        return Third_Fragment.rslt;
    }
}