package com.example.aylin.menulogin;

import android.content.Context;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by aylin on 27.04.2016.
 */
public class BasvuruCaller extends Thread {

        private static Context context;


        public BasvuruCallSoap bcs;
        public String x,y;
        public String resp2;
        public void run(){
            try{

                bcs=new BasvuruCallSoap();
                resp2=bcs.CallBasvuru(x,y);
                Basvuru_Fragment.rslt3=resp2;
            }catch(Exception ex)
            {
                Basvuru_Fragment.rslt3=ex.toString();
            }
        }
        public String ReturnValue(){
             System.out.println("Dönen Değer:" + Basvuru_Fragment.rslt3);
           return Basvuru_Fragment.rslt3;
        }


    }

