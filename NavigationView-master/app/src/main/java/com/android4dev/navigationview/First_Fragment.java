package com.android4dev.navigationview;

/**
 * Created by aylin on 05.05.2016.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class First_Fragment extends Fragment  {
    TextView tv3;
    View v;
    TextView tc,isim,sicil,tel,info;
    ArrayList<String> returnList=new ArrayList<String>();
    Caller c=new Caller();
    List<UserInfoModel>list2=new ArrayList<UserInfoModel>();
    UserInfoDatabaseHelper uidb;
    ArrayList<String> array=new ArrayList<String>();
    Third_Fragment tf=new Third_Fragment();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v=inflater.inflate(R.layout.first_layout, container, false);
        tc=(TextView) v.findViewById(R.id.tc);
        isim=(TextView) v.findViewById(R.id.ad);
        sicil=(TextView)v.findViewById(R.id.sicil);
        tel=(TextView) v.findViewById(R.id.tel);
        info=(TextView) v.findViewById(R.id.info);
        uidb=new UserInfoDatabaseHelper(getActivity());

        KayitGoster();

//        System.out.println("veritabanından gelen :..." + array.get(3));

        String listString = "";

        for (String s : array)
        {
            listString += s + "\t";
            System.out.println("liststring: "+listString);
        }
        String[] result=listString.split("  ");

        tc.setText(result[1]);
        isim.setText(result[2]);
        sicil.setText(result[3]);
        tel.setText(result[4]);

        // System.out.println(tf.returnDatabaseValue());
        return v;
    }



    public void KayitGoster() {
        SQLiteDatabase db = uidb.getReadableDatabase();
        String selectQuery = "SELECT  * FROM  davaUserInfo";
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
            array.add(id+  "  "+tc+  "  "+avukat+  "  "+sicil+ " "+tel);
        }
    }

}
