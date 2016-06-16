package com.example.aylin.d_av_a.AnaSayfa;

import com.example.aylin.d_av_a.Duyuru.DuyuruFragment;
import com.example.aylin.d_av_a.Duyuru.DuyuruSoap;

/**
 * Created by aylin on 16.06.2016.
 */
public class HomeCaller  extends Thread {
        public HomeSoap hs;
        public String  a, b;
        public void run(){
            try{
                hs=new HomeSoap();
                String resp=hs.Call(a,b);
                ContentFragment.rslt=resp;
            }catch(Exception ex)
            {ContentFragment.rslt=ex.toString();

            }
        }
    }
