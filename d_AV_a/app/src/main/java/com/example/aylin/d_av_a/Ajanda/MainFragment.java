package com.example.aylin.d_av_a.Ajanda;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.aylin.d_av_a.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by aylin on 31.05.2016.
 */
public class MainFragment extends Fragment{
    public static String[] part;
    public static String rslt="";

    CalenderCallSoap cs;
    TextView davam,topdava,tarih;
    public GregorianCalendar month, itemmonth;// calendar instances.

    public CalendarAdapter adapter;// adapter instance
    public Handler handler;// for grabbing some event values for showing the dot
    // marker.
    public List<String> items; // container to store calendar items which
    // needs showing the event marker
    public  static TextView title;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.calendar, container, false);

                try
                {
                    rslt="START";
                    CalenderCall c=new CalenderCall(); c.a=2;
                    c.b="15833927042";c.c=0;
                    c.join(); c.start();
                    while(rslt=="START") {
                        try {
                            Thread.sleep(10);
                        }catch(Exception ex) {
                        }
                    }
                    rslt=rslt.replace("[", " ").replace("\n]", " ").replace(".","-").replace(", ","");
                    part=rslt.split("\n");
                }catch(Exception ex) {
                }

        cs=new CalenderCallSoap();
        davam=(TextView)v.findViewById(R.id.kendidavam);
        topdava=(TextView)v.findViewById(R.id.davatoplam);
        tarih=(TextView)v.findViewById(R.id.tarih);
        Locale.setDefault(Locale.US);
        month = (GregorianCalendar) GregorianCalendar.getInstance();
        itemmonth = (GregorianCalendar) month.clone();


        adapter = new CalendarAdapter(getActivity(), month);

        GridView gridview = (GridView) v.findViewById(R.id.gridview);
        gridview.setAdapter(adapter);

        handler = new Handler();
        handler.post(calendarUpdater);

      title = (TextView) v.findViewById(R.id.title);
        title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));

        RelativeLayout previous = (RelativeLayout) v.findViewById(R.id.previous);

        previous.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setPreviousMonth();
                refreshCalendar();
            }
        });

        RelativeLayout next = (RelativeLayout) v.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setNextMonth();
                refreshCalendar();

            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                ((CalendarAdapter) parent.getAdapter()).setSelected(v);
                String selectedGridDate = CalendarAdapter.dayString
                        .get(position);
                String[] separatedTime = selectedGridDate.split("-");
                String gridvalueString = separatedTime[2].replaceFirst("^0*",
                        "");// taking last part of date. ie; 2 from 2012-12-02.
                int gridvalue = Integer.parseInt(gridvalueString);
                // navigate to next or previous month on clicking offdays.
                if ((gridvalue > 10) && (position < 8)) {
                    setPreviousMonth();
                    refreshCalendar();
                } else if ((gridvalue < 7) && (position > 28)) {
                    setNextMonth();
                    refreshCalendar();
                }
                ((CalendarAdapter) parent.getAdapter()).setSelected(v);
                for (int i=0; i<30;i++){
                    if(i==position){
                        if(Integer.parseInt(cs.returnTip()[i])==1){
                            System.out.println(cs.returnCount()[i]+"  adet dava var");
                            tarih.setText(selectedGridDate);
                            davam.setText (cs.returnCount()[i] + " " + "adet dava var");
                            topdava.setText("0 adet diğer dava");
                        }else{
                            tarih.setText(selectedGridDate);
                            System.out.println(cs.returnCount()[i]+"  kadar");
                            davam.setText("0 adet dava var");
                            topdava.setText(cs.returnCount()[i]+" "+ "adet  dava var");
                        }

                    }
                }
                showToast(selectedGridDate);

            }
        });

        return v;
    }


    protected void setNextMonth() {
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMaximum(GregorianCalendar.MONTH)) {
            month.set((month.get(GregorianCalendar.YEAR) + 1),
                    month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            month.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) + 1);
        }

    }

    protected void setPreviousMonth() {
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMinimum(GregorianCalendar.MONTH)) {
            month.set((month.get(GregorianCalendar.YEAR) - 1),
                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            month.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) - 1);
        }

    }

    protected void showToast(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();

    }

    public void refreshCalendar() {


        adapter.refreshDays();
        adapter.notifyDataSetChanged();
        handler.post(calendarUpdater); // generate some calendar items

        title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
    }

    public Runnable calendarUpdater = new Runnable() {

        @Override
        public void run() {

            // Print dates of the current week
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
            String itemvalue;
            String []getValue=part;
            System.out.println("ndfklnd:"+part[0]);
            String []pass;
            pass=part;
            for (int i = 0; i < 7; i++) {
                itemvalue = df.format(itemmonth.getTime());
                itemmonth.add(GregorianCalendar.DATE, 1);
                items= Arrays.asList(pass);

            }
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
    };
    public String [] getValue(){

        return part;
    }
}