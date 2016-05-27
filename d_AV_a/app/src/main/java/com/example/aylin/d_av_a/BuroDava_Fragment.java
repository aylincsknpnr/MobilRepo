package com.example.aylin.d_av_a;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by aylin on 23.05.2016.
 */
public class BuroDava_Fragment extends android.support.v4.app.Fragment {
    public ArrayList<String> array=new ArrayList<String>();
    public String[] getValue;
    UserInfoDatabaseHelper uidb;
        EditText BirimAdi, TarihSaat, DosyaNo, DosyaTurKod, DosyaVekilleri, Durumu, YORA, DERECE;
        String[] part;
        String[] values;
        public static String rslt = "";
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.buro_dava_fragment, container, false);
            uidb=new UserInfoDatabaseHelper(getActivity());
            recyclerView = (RecyclerView) v.findViewById(R.id.burodava_recycler_view);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            try {

                try {
                    //veritabanı bilgi getirme
                    KayitGoster();
                    String listString = "";
                    for (String s : array)
                    {
                        listString += s + "\t";
                    }
                    System.out.println(listString);
                    getValue=listString.split("  ");
                    System.out.println(getValue[1]);
                    //bilgi ile servisi işleme
                    rslt = "START";
                    DavalarCaller dc = new DavalarCaller();
                    dc.a = getValue[1].toString();
                    dc.b = 1;
                    dc.c = 2;//büroid bağlanacak
                    dc.join();
                    dc.start();
                    while (rslt == "START") {
                        try {
                            Thread.sleep(10);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    System.out.println("Result:" + rslt);
                    rslt=rslt.replace("[", " ").replace("\n]", " ");
                    part=rslt.split("\n");
                    //      Picasso.with(getActivity().getApplicationContext()).load("http://dmzws.barokart.com.tr/dmz.xml.info/TBB2Image.ashx?id=6&baroid="+sicil.getText().toString()+"&t=1").into(img);

                    adapter = new BuroAvAdapter(part);
                    recyclerView.setAdapter(adapter);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            } catch (Exception e) {
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
