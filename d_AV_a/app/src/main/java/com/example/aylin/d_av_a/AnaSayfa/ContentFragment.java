package com.example.aylin.d_av_a.AnaSayfa;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aylin.d_av_a.Duyuru.DuyuruAdapter;
import com.example.aylin.d_av_a.Duyuru.DuyuruCaller;
import com.example.aylin.d_av_a.Duyuru.DuyuruSoap;
import com.example.aylin.d_av_a.R;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Admin on 04-06-2015.
 */
public class ContentFragment extends Fragment {
    String[] part1;
    String[] part2;
    String[] part3;
    String[] part4;
    String[] part5;
    String[] part6;
    String[] part7;
    ImageView icon1;
    TextView mainKonu;
    public static String rslt = "";
    private static HomeAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    HomeSoap hs;
    EditText dateInput;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_fragment, container, false);
        dateInput = (EditText) v.findViewById(R.id.datepickerTxt);
        recyclerView = (RecyclerView) v.findViewById(R.id.home_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        hs = new HomeSoap();
        return v;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDatePicker();

                int delay = 20000;// in ms

                Timer timer = new Timer();

                timer.schedule( new TimerTask(){
                    public void run() {
                        System.out.println("Wait, what..:");
                        try {

                            try {

                                rslt = "START";
                                HomeCaller hc = new HomeCaller();
                                hc.a = "15833927042";
                                hc.b = dateInput.getText().toString();
                                hc.join();
                                hc.start();
                                while (rslt == "START") {
                                    try {
                                        Thread.sleep(10);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }

                                part1 = hs.returnBaslik();
                                // part2=hs.returnIcon();
                                part3 = hs.returnSaat();
                                adapter = new HomeAdapter(part1, part3);
                                recyclerView.setAdapter(adapter);

                                // adapter.setClickListener(this);
                            } catch (Exception ex) {
                                System.out.println(ex.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity().getApplicationContext(), "Başarısız Giriş", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, delay);

                System.out.println("Would it run?");
            }




        });
    }
public void callService(String dateGet){
    try {

        try {

            rslt = "START";
            HomeCaller hc = new HomeCaller();
            hc.a = "15833927042";
            hc.b = dateGet;
            hc.join();
            hc.start();
            while (rslt == "START") {
                try {
                    Thread.sleep(10);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            part1 = hs.returnBaslik();
            // part2=hs.returnIcon();
            part3 = hs.returnSaat();
            adapter = new HomeAdapter(part1, part3);
            recyclerView.setAdapter(adapter);

            // adapter.setClickListener(this);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    } catch (Exception e) {
        e.printStackTrace();
        Toast.makeText(getActivity().getApplicationContext(), "Başarısız Giriş", Toast.LENGTH_SHORT).show();
    }

}

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

    public void onDateSet(DatePicker view, int year, int monthOfYear,
                          int dayOfMonth) {

        dateInput.setText(String.valueOf(year) + "." + "0"+String.valueOf(monthOfYear+1)
                + "." + String.valueOf(dayOfMonth));

        System.out.println(dateInput.getText().toString());
    }
};
}
