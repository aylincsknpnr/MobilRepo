package com.example.aylin.menulogin;

import android.app.Fragment;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by aylin on 27.04.2016.
 */
public class Basvuru_Fragment extends Fragment {

    View myView;
    public static String rslt3="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        myView=inflater.inflate(R.layout.basvuru_layout, container, false);


        Button b3=(Button) myView.findViewById(R.id.buttonget);
        b3.setOnClickListener(new OnClickListener() {

            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub

                try
                {
                    EditText ed1=(EditText)myView.findViewById(R.id.iduser);
                    EditText ed2=(EditText)myView.findViewById(R.id.bastip);
                    String x=ed1.getText().toString();
                    String y=ed2.getText().toString();
                    rslt3="START";
                    BasvuruCaller c=new BasvuruCaller(); c.x=x;
                    c.y=y;
                    c.join(); c.start();
                    while(rslt3=="START") {
                        try {
                            Thread.sleep(10);
                        }catch(Exception ex) {
                        }
                    }
                    System.out.println("Result:" + rslt3);
                    Toast.makeText(getActivity().getApplicationContext(), rslt3, Toast.LENGTH_SHORT).show();

                }catch(Exception ex) {
                    System.out.println( ex.toString()+"hatacııkkk");
                }

            } });
        return myView;
    }
}
