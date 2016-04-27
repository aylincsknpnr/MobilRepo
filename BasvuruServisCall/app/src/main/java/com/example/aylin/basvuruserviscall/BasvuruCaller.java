package com.example.aylin.basvuruserviscall;

import android.content.Context;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by aylin on 27.04.2016.
 */
public class BasvuruCaller extends Thread {

        private static Context context;


        public BasvuruCallSoap bcs;
        public Integer x,y;
        public String resp2;
        public void run(){
            try{

                bcs=new BasvuruCallSoap();
                resp2=bcs.CallBasvuru(x,y);
                MainActivity.rslt3=resp2;
            }catch(Exception ex)
            {
                MainActivity.rslt3=ex.toString();
            }
        }
       // public String ReturnValue(){
            // System.out.println("Dönen Değer:" + Second_Fragment.rslt);
         //   return Second_Fragment.rslt;
        //}


    }

