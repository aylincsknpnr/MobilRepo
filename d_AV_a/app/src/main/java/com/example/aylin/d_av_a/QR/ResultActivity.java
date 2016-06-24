package com.example.aylin.d_av_a.QR;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.aylin.d_av_a.R;

/**
 * Created by aylin on 24.06.2016.
 */
public class ResultActivity extends AppCompatActivity {
    TextView prt1,prt2,prt3;
    public static String read=" ";
    SimpleScannerActivity ssa;
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.result_layout);

        ssa=new SimpleScannerActivity();
        prt1=(TextView)findViewById(R.id.prt1);
        prt2=(TextView)findViewById(R.id.prt2);
        prt3=(TextView)findViewById(R.id.prt3);

        prt1.setText(ssa.retRes());
    }
}
