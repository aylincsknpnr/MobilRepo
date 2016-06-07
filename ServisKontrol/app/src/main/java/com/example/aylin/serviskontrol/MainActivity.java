package com.example.aylin.serviskontrol;
import android.app.Activity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    public static String rslt="";    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1=(Button)findViewById(R.id.button1);
        final  AlertDialog ad=new AlertDialog.Builder(this).create();

        b1.setOnClickListener(new OnClickListener() {

            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub

                try
                {
                    EditText ed1=(EditText)findViewById(R.id.editText1);
                    EditText ed2=(EditText)findViewById(R.id.editText2);
                    EditText ed3=(EditText)findViewById(R.id.editText3);
                    int a=Integer.parseInt(ed1.getText().toString());
                    String b=ed2.getText().toString();
                    int ce=Integer.parseInt(ed3.getText().toString());
                    rslt="START";
                    Caller c=new Caller(); c.a=a;
                    c.b=b;c.c=ce;
                    c.join(); c.start();
                    while(rslt=="START") {
                        try {
                            Thread.sleep(10);
                        }catch(Exception ex) {
                        }
                    }
                    ad.setTitle("RESULT ");
                    ad.setMessage(rslt);
                }catch(Exception ex) {
                    ad.setTitle("Error!"); ad.setMessage(ex.toString());
                }
                ad.show();
            } });
    }
}