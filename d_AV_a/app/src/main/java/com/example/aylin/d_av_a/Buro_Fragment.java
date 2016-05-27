
package com.example.aylin.d_av_a;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by aylin on 18.05.2016.
 */
public class Buro_Fragment extends android.support.v4.app.Fragment {
    EditText officalName, address, tel1, tel2, fax, taxNo, taxDepartment,tc;
    String [] part;
    First_Fragment ff;
    public String [] getValue;
    UserInfoDatabaseHelper uidb;
    public static String rslt="";
    public ArrayList<String> array=new ArrayList<String>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.buro_fragment,container,false);
        uidb=new UserInfoDatabaseHelper(getActivity());
        try{
            officalName=(EditText)v.findViewById(R.id.buroAd);
            address=(EditText)v.findViewById(R.id.buroAdres);
            tel1=(EditText)v.findViewById(R.id.buroTel1);
            tel2=(EditText)v.findViewById(R.id.buroTel2);
            fax=(EditText)v.findViewById(R.id.fax);
            taxNo=(EditText)v.findViewById(R.id.vergiNo);
            taxDepartment=(EditText)v.findViewById(R.id.vergiDairesi);
                    try {
                        // veritabanından alınan bilgi ile servis çağrılıyor
                        KayitGoster();
                        String listString = "";
                        for (String s : array)
                        {
                            listString += s + "\t";
                        }
                        System.out.println(listString);
                        getValue=listString.split("  ");
                       //servis işlem
                        rslt = "START";
                        BuroCaller c = new BuroCaller();
                        c.a=getValue[1];
                        c.join();
                        c.start();
                        while (rslt == "START") {
                            try {
                                Thread.sleep(10);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        System.out.println("Result:" + rslt);
                        part=rslt.split(" ");
                        officalName.setText(part[1]);
                        address.setText(part[3]);
                        tel1.setText(part[6]);
                        tel2.setText(part[7]);
                        fax.setText(part[8]);
                        taxDepartment.setText(part[9] + part[10]);
                        taxNo.setText(part[11]);
                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                    }

        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getActivity().getApplicationContext(), "Servis Hata", Toast.LENGTH_SHORT).show();
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