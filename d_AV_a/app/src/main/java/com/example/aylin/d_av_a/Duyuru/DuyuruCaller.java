package com.example.aylin.d_av_a.Duyuru;

import com.example.aylin.d_av_a.BuroDava.BuroDava_Fragment;
import com.example.aylin.d_av_a.Dava.DavaSoap;
import com.example.aylin.d_av_a.Dava.Dava_Fragment;

/**
 * Created by aylin on 15.06.2016.
 */
public class DuyuruCaller extends Thread {
        public DuyuruSoap ds;
        public Integer  a;
        public void run(){
            try{
                ds=new DuyuruSoap();
                String resp=ds.Call(a);
                DuyuruFragment.rslt=resp;
            }catch(Exception ex)
            {DuyuruFragment.rslt=ex.toString();

            }
        }
    }

