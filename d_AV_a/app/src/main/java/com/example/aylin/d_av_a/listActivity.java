package com.example.aylin.d_av_a;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import static android.location.Location.convert;

/**
 * Created by aylin on 09.05.2016.
 */
public class listActivity extends Activity{

    public static String [] items2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        ListView listView = (ListView) findViewById(R.id.listv);
        ViewGroup headerView = (ViewGroup) getLayoutInflater().inflate(R.layout.list_header, listView, false);
        listView.addHeaderView(headerView);

        items2 = First_Fragment.postValue();
        String[] stringArray = items2;
        ListAdapter adapter = new ListAdapter(this, R.layout.list_row, R.id.avTxt, stringArray);
        listView.setAdapter(adapter);
        Button back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                try
                {
                    onBackPressed();
                }catch(Exception ex) {
                    System.out.println( ex.toString());
                }
            } });
    }
    @Override
    public void onBackPressed() {
        //Display alert message when back button has been pressed
        backButtonHandler();
        return;
    }

    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                listActivity.this);
        // Setting Dialog Title
        alertDialog.setTitle("Leave application?");
        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want to leave the application?");

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
        // Showing Alert Message
        alertDialog.show();
    }
}
