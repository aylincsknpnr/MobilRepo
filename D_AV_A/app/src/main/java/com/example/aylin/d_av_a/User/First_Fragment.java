package com.example.aylin.d_av_a.User;
/**
 * Created by aylin on 05.05.2016.
 */

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aylin.d_av_a.Login.Caller;
import com.example.aylin.d_av_a.Login.Third_Fragment;
import com.example.aylin.d_av_a.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class First_Fragment extends Fragment {
    TextView tv3;
    View v;
    public String[] result2;
    TextView tc,isim,sicil,tel,info;
    public String [] result;
    ArrayList<String> returnList=new ArrayList<String>();
    Caller c=new Caller();
    List<UserInfoModel>list2=new ArrayList<UserInfoModel>();
    UserInfoDatabaseHelper uidb;
    public ArrayList<String> array=new ArrayList<String>();
    Third_Fragment tf=new Third_Fragment();
    ImageView profile;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v=inflater.inflate(R.layout.avinfo, container, false);
        tc=(TextView) v.findViewById(R.id.tc);
        isim=(TextView) v.findViewById(R.id.ad);
        sicil=(TextView)v.findViewById(R.id.sicil);
        tel=(TextView) v.findViewById(R.id.tel);
        info=(TextView) v.findViewById(R.id.info);
        uidb=new UserInfoDatabaseHelper(getActivity());
        profile=(ImageView)v.findViewById(R.id.imageView1);
        KayitGoster();

        String listString = "";
        for (String s : array)
        {
            listString += s + "\t";
        }
        System.out.println(listString);
        result=listString.split("  ");
        tc.setText(result[1]);
      //  Buro_Fragment.returnValue=result[1];
        isim.setText(result[2]);
        sicil.setText(result[3]);
        tel.setText(result[4]);
        Picasso.with(getActivity().getApplicationContext()).load("http://dmzws.barokart.com.tr/dmz.xml.info/TBB2Image.ashx?id=6&baroid="+result[3]+"&t=1").into(profile);
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