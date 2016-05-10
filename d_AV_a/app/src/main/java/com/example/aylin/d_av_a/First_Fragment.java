package com.example.aylin.d_av_a;
/**
 * Created by aylin on 05.05.2016.
 */
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class First_Fragment extends Fragment {
    TextView tv3;
    View v;
    public String[] result2;
    TextView tc,isim,sicil,tel,info;
    ArrayList<String> returnList=new ArrayList<String>();
    Caller c=new Caller();
    List<UserInfoModel>list2=new ArrayList<UserInfoModel>();
    UserInfoDatabaseHelper uidb;
    public ArrayList<String> array=new ArrayList<String>();
    Third_Fragment tf=new Third_Fragment();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v=inflater.inflate(R.layout.first_layout, container, false);
        info=(TextView) v.findViewById(R.id.info);
        uidb=new UserInfoDatabaseHelper(getActivity());
        KayitGoster();
        String [] result;
        String listString = "";
        for (String s : array)
        {
            listString += s + "\t";
        }
        result=listString.split(" ");
        listActivity.items2=result;
        System.out.println("resresRRRR"+result[3]);
        startActivity(new Intent("android.intent.action.DIGER"));
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
    public static String[] postValue(){
        return listActivity.items2;
    }
}