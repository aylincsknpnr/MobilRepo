package com.example.aylin.d_av_a.Ajanda;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.example.aylin.d_av_a.R;

/**
 * Created by aylin on 31.05.2016.
 */
public class MainFragment extends Fragment{
    public static String[] part;

    public static String rslt="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.calendar_main,container,false);
        final Button b1=(Button)v.findViewById(R.id.button1);
        final AlertDialog ad=new AlertDialog.Builder(getActivity()).create();

        b1.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub

                try
                {
                    EditText ed1=(EditText)v.findViewById(R.id.editText1);
                    EditText ed2=(EditText)v.findViewById(R.id.editText2);
                    EditText ed3=(EditText) v.findViewById(R.id.editText3);
                    int a=Integer.parseInt(ed1.getText().toString());
                    String b=ed2.getText().toString();
                    int ce=Integer.parseInt(ed3.getText().toString());
                    rslt="START";
                    CalenderCall c=new CalenderCall(); c.a=a;
                    c.b=b;c.c=ce;
                    c.join(); c.start();
                    Intent intent = new Intent(getActivity(), CalendarView.class);
                    startActivity(intent);
                 //   startActivity(new Intent("android.intent.action.THIRD"));
                    while(rslt=="START") {
                        try {
                            Thread.sleep(10);
                        }catch(Exception ex) {
                        }
                    }
                    //ad.setTitle("RESULT ");
                    //ad.setMessage(rslt);
                    rslt=rslt.replace("[", " ").replace("\n]", " ").replace(".","-").replace(", ","");
                    part=rslt.split("\n");
                }catch(Exception ex) {
                    //ad.setTitle("Error!"); ad.setMessage(ex.toString());
                }
                // ad.show();
            } });
        return v;
    }
    public String [] getValue(){

        return part;
    }
}