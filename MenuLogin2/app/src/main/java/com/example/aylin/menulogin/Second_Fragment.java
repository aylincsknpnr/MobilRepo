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
 * Created by aylin on 16.04.2016.
 */
public class Second_Fragment extends Fragment {
    View myView;
    public static String rslt="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        myView=inflater.inflate(R.layout.second_layout, container, false);


        Button b1=(Button) myView.findViewById(R.id.button1);
        b1.setOnClickListener(new OnClickListener() {

            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub

                try
                {
                    EditText ed1=(EditText)myView.findViewById(R.id.editText1);
                    EditText ed2=(EditText)myView.findViewById(R.id.editText2);
                    String a=ed1.getText().toString();
                    String b=ed2.getText().toString();
                    rslt="START";
                    Caller c=new Caller(); c.a=a;
                    c.b=b;
                    c.join(); c.start();
                    while(rslt=="START") {
                        try {
                            Thread.sleep(10);
                        }catch(Exception ex) {
                        }
                    }
                    System.out.println("Result:" + rslt);
                    Toast.makeText(getActivity().getApplicationContext(), rslt, Toast.LENGTH_SHORT).show();

                }catch(Exception ex) {
                    System.out.println( ex.toString());
                }

            } });
        return myView;
    }
}
