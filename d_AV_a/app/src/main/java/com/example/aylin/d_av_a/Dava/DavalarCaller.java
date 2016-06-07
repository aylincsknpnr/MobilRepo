package com.example.aylin.d_av_a.Dava;

import com.example.aylin.d_av_a.BuroDava.BuroDava_Fragment;

/**
 * Created by aylin on 23.05.2016.
 */
public class DavalarCaller  extends Thread {
        public DavaSoap ds;
        public String  a;
        public Integer b,c;
        public void run(){
            try{
                ds=new DavaSoap();
                String resp=ds.Call(a,b,c);
                Dava_Fragment.rslt=resp;
                BuroDava_Fragment.rslt=resp;
            }catch(Exception ex)
            {Dava_Fragment.rslt=ex.toString();
                BuroDava_Fragment.rslt=ex.toString();
            }
        }
    }
