package com.example.aylin.avukep.Kep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aylin.avukep.Hatirlatici.takvimuygulamasi.HatirlaticiKur;
import com.example.aylin.avukep.Hatirlatici.takvimuygulamasi.RemindFragment;
import com.example.aylin.avukep.Login.MainActivity;
import com.example.aylin.avukep.R;

/**
 * Created by aylin on 01.07.2016.
 */
public class KepDetayActivity extends AppCompatActivity {
    private Toolbar toolbar;
    String[] part6,part7,part8,part9;
    public static int sayac=0;
    public static String alici,gonderenler,konular,value;
    public static String rslt2 = "";
    public static Intent intent;
    public static TableLayout tl;
    public static CardView card1,card2,card3,card4;
    public final static String TAG_FRAGMENT = "TAG_FRAGMENT";
    KepSoap kes;
    KepDetaySoap kds;
    public static ImageButton backbutton,homebutton,hatirButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kep_detay);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        intent = getIntent();
        value =  intent.getStringExtra("idim");
        alici=intent.getStringExtra("alici");
        gonderenler=intent.getStringExtra("gonderen");
        konular=intent.getStringExtra("konu");
        TextView date=(TextView)findViewById(R.id.detdate);
        TextView icerik=(TextView)findViewById(R.id.detdetay);
        TextView alicilar=(TextView)findViewById(R.id.alici);
        TextView gonderen=(TextView)findViewById(R.id.gonderen);
        TextView konu=(TextView)findViewById(R.id.subjects);
        kds=new KepDetaySoap();
        card1=(CardView)findViewById(R.id.icerikk);
        card2=(CardView)findViewById(R.id.butonlar);
        card3=(CardView)findViewById(R.id.mailtamami);
        card4=(CardView)findViewById(R.id.konucard);
        backbutton=(ImageButton)findViewById(R.id.back_button);
        homebutton=(ImageButton)findViewById(R.id.homebut);
        hatirButton=(ImageButton)findViewById(R.id.hatirclock);
        homebutton.setVisibility(View.GONE);
        tl=(TableLayout)findViewById(R.id.tablelayout);
        backbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    KepFragment kepFragment2 = new KepFragment();
                        android.support.v4.app.FragmentTransaction ftkep2 = getSupportFragmentManager().beginTransaction();
                        ftkep2.replace(R.id.frame2, kepFragment2);
                ftkep2.commit();
                backbutton.setVisibility(View.GONE);
                homebutton.setVisibility(View.VISIBLE);
                tl.setVisibility(View.GONE);
                     card1.setVisibility(View.GONE);
                     card2.setVisibility(View.GONE);
                     card3.setVisibility(View.GONE);
                     card4.setVisibility(View.GONE);
            }
        });
        homebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KepDetayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        try {
            try {

                rslt2 = "START";
                KepDetayCaller kdc = new KepDetayCaller();
                kdc.a = "15833927042";
                kdc.b=Integer.parseInt(value);
                kdc.join();
                kdc.start();
                while (rslt2 == "START") {
                    try {
                        Thread.sleep(10);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                part6=kds.returnIcerik1();
                part7=kds.returnIcerik2();
                part8=kds.returnId();
                date.setText(part6[0].toString());
                icerik.setText(part7[0].toString());
                alicilar.setText(alici);
                gonderen.setText(gonderenler);
                konu.setText(konular);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this.getApplicationContext(), "Başarısız Giriş", Toast.LENGTH_SHORT).show();
        }
        hatirButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*RemindFragment fragment2 = new RemindFragment();
                android.support.v4.app.FragmentTransaction ftrem2 = getSupportFragmentManager().beginTransaction();
                ftrem2.replace(R.id.frame2, fragment2);
                ftrem2.commit();*/
                Intent intent = new Intent(KepDetayActivity.this,HatirlaticiKur.class);
                intent.putExtra("baslik2", konular);
                intent.putExtra("not2", part6[0].toString() + " " + "tarihli" + konular +" "+ "tebligatı için gerekli işlemleri yap");
                startActivity(intent);
            }
        });
    }
}
