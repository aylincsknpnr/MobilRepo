package com.example.aylin.menulogin;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by aylin on 27.04.2016.
 */
public class Basvuru_Fragment extends Fragment {

    View myView;
    public static String rslt3 = "";
    ArrayList<String> items_list=new ArrayList<String>();
    public ArrayList<String> postValue;

    BasvuruCaller bc = new BasvuruCaller();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.basvuru_layout, container, false);

        final ArrayList<String> items = new ArrayList<String>();
        Button b3 = (Button) myView.findViewById(R.id.buttonget);
        b3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                try {
                    EditText ed1 = (EditText) myView.findViewById(R.id.iduser);
                    EditText ed2 = (EditText) myView.findViewById(R.id.bastip);
                    String x = ed1.getText().toString();
                    String y = ed2.getText().toString();
                    rslt3 = "START";
                    BasvuruCaller c = new BasvuruCaller();
                    c.x = x;
                    c.y = y;
                    c.join();
                    c.start();
                    while (rslt3 == "START") {
                        try {
                            Thread.sleep(10);
                        } catch (Exception ex) {
                        }
                    }
                    System.out.println("Result:" + rslt3);
                    Toast.makeText(getActivity().getApplicationContext(), rslt3, Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    System.out.println(ex.toString() + "hatacııkkk");
                }

                String result = bc.ReturnValue();
                result = result.replace("[", "");
                result = result.replace("]", "");
                result = result.replace(" ", "");
                System.out.println("Dönenin Resultı:" + result);
                int index = result.indexOf(",");

                items.add("ARANABİLİR");
                items.add("Yasemin KARAKURT DİNÇER");
                items.add("20160019");
                items.add("2016-04-22T14:50:53+03:00");
                items.add("0");
                items.add("Kendisi");
                postValue=items;
                BasvurulistActivity.items2=postValue;
                System.out.println("items :" + items.get(1) + "2:" + items.get(2));
                System.out.println("postValue:"+postValue);
                startActivity(new Intent("android.intent.action.DIGER"));
            }
        });

        return myView;
    }

    public static ArrayList<String> postValue(){
        return BasvurulistActivity.items2;
    }
}

