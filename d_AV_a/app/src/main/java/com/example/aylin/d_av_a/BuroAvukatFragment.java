package com.example.aylin.d_av_a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by aylin on 20.05.2016.
 */
public class BuroAvukatFragment extends android.support.v4.app.Fragment {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    public ArrayList<String> array=new ArrayList<String>();
    UserInfoDatabaseHelper uidb;
    public String[] getValue;
        TextView tc, ad, baro, sicil, tbbno, tel, tc2, ad2, baro2, barosicil2, tbbno2, tel2;
        TextView info;
        String [] part;
        String [] values;
        String [] values2;
        First_Fragment ff;
        public ImageView img;
        public static String rslt="";
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.buroav_fragment,container,false);
            uidb=new UserInfoDatabaseHelper(getActivity());
            sicil=null;
            recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
         //   sicil.setVisibility(v.INVISIBLE);
           // sicil=(TextView)v.findViewById(R.id.sicil);

            //img=(ImageView)v.findViewById(R.id.imageView);
            try{
                ad=(TextView)v.findViewById(R.id.adsoyad);
                img=(ImageView)v.findViewById(R.id.imageView);
                try {
                    //veritabanı bilgi
                    KayitGoster();
                    String listString = "";
                    for (String s : array)
                    {
                        listString += s + "\t";
                    }

                    getValue=listString.split("  ");
                    System.out.println("büroid:"+getValue[1]);
                    //bilgi ile servis çağrımı
                    rslt = "START";
                    BuroAvukatCaller bac = new BuroAvukatCaller();
                    bac.one="0";
                    bac.two=2;//büroid bağlancak
                    bac.join();
                    bac.start();
                    while (rslt == "START") {
                        try {
                            Thread.sleep(10);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    String [] part2;
                    part2=rslt.split(",");
                    System.out.println("Result:" + rslt);
                   rslt=rslt.replace("[", " ").replace("\n]", " ");
                 part=rslt.split("\n");
            //  Picasso.with(getActivity().getApplicationContext()).load("http://dmzws.barokart.com.tr/dmz.xml.info/TBB2Image.ashx?id=6&baroid="+part2[3]+"&t=1").into(img);
                 adapter = new BuroAvAdapter(part);
                    recyclerView.setAdapter(adapter);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }catch(Exception e){
                e.printStackTrace();
                Toast.makeText(getActivity().getApplicationContext(), "Başarısız Giriş", Toast.LENGTH_SHORT).show();
            }
            return v;
        }
    public void KayitGoster() {
        SQLiteDatabase db = uidb.getReadableDatabase();
        String selectQuery = "SELECT * FROM davaUserInfo";
        Cursor c = db.rawQuery(selectQuery, null);
        array.clear(); int id = 0;
        String tc = "";
        String avukat="";
        String sicil="";
        String tel="";
        String gelen="";
        while (c.moveToNext()) {
            id = c.getInt(c.getColumnIndex("id"));
            tc=c.getString(c.getColumnIndex("tc"));
            avukat=c.getString(c.getColumnIndex("avukat"));
            sicil=c.getString(c.getColumnIndex("sicil"));
            tel=c.getString(c.getColumnIndex("tel"));
            gelen+=id+" "+tc+" "+avukat+" "+sicil+" "+tel+"\n";
            array.add(id+ " "+tc+ " "+avukat+ " "+sicil+ " "+tel);
        }
    }
    }