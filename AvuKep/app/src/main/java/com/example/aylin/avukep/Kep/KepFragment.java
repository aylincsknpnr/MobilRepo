package com.example.aylin.avukep.Kep;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aylin.avukep.BuroAvukat.BuroAvAdapter;
import com.example.aylin.avukep.BuroAvukat.BuroAvukatCaller;
import com.example.aylin.avukep.Hatirlatici.dao.AlarmDAO;
import com.example.aylin.avukep.Hatirlatici.takvimuygulamasi.HatirlaticiKur;
import com.example.aylin.avukep.Login.DatabaseHelper;
import com.example.aylin.avukep.Login.MainActivity;
import com.example.aylin.avukep.R;
import com.example.aylin.avukep.User.First_Fragment;
import com.example.aylin.avukep.User.UserInfoDatabaseHelper;

import java.util.ArrayList;

/**
 * Created by aylin on 29.06.2016.
 */
public class KepFragment extends android.support.v4.app.Fragment implements ClickListener {
        String[] part1,part2,part3,part4,part5,part7,part8;
        public static String rslt = "";
        public static Button backButton;
        public static ImageButton home;
        KepSoap kes;
        KepDetaySoap kds;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.kep_fragment, container, false);
            final ListView list;

            kes=new KepSoap();
            try {
                try {

                    rslt = "START";
                    KepCaller kc = new KepCaller();
                    kc.a = "15833927042";
                    kc.join();
                    kc.start();
                    while (rslt == "START") {
                        try {
                            Thread.sleep(10);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    part1=kes.returnGonderen();
                    part2=kes.returnGondate();
                    part3=kes.returnTebdate();
                    part4=kes.returnKonu();
                    part5=kes.returnSaat();
                    part7=kes.returnKid();
                    part8=kes.returnAlici();

                    KepAdapter adapter = new
                            KepAdapter(getActivity(), part1,part2,part4,part5);
                    list=(ListView)v.findViewById(R.id.list);
                    list.setAdapter(adapter);

                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            list.setVisibility(View.GONE);
                            Intent intent = new Intent(getActivity(), KepDetayActivity.class);
                            intent.putExtra("idim", part7[position]);
                            intent.putExtra("alici", part8[position]);
                            intent.putExtra("gonderen",part1[position]);
                            intent.putExtra("konu",part4[position]);
                            intent.putExtra("Check",1);
                            startActivity(intent);
                        }
                    });
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity().getApplicationContext(), "Başarısız Giriş", Toast.LENGTH_SHORT).show();
            }

            return v;
        }
        @Override
        public void itemClicked(View view, int position) {
            System.out.println("tik:" + position);

        }
    }

