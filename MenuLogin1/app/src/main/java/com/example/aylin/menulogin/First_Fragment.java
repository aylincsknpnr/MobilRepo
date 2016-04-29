package com.example.aylin.menulogin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aylin on 16.04.2016.
 */
public class First_Fragment extends Fragment  {
    TextView tv3;
    View myView;
    TextView tc,isim,sicil,tel,info;
    ArrayList<String> returnList=new ArrayList<String>();
    Caller c=new Caller();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        myView=inflater.inflate(R.layout.first_layout, container, false);

        tc=(TextView) myView.findViewById(R.id.tc);
        isim=(TextView) myView.findViewById(R.id.ad);
        sicil=(TextView)myView.findViewById(R.id.sicil);
        tel=(TextView) myView.findViewById(R.id.tel);
        info=(TextView) myView.findViewById(R.id.info);

        String  result=c.ReturnValue();


        result = result.replace("[","");
        result = result.replace("]","");
        result = result.replace(" ","");
        int index=result.indexOf(",");
        System.out.println(result+":gelenşiiuyy");
        System.out.println("index"+index);
      //   String[] items = result.split(",");
        String items1="TC:"+result.substring(2, result.indexOf(",", 2));
       String items2="Avukat:"+result.substring(result.indexOf(",",7),result.indexOf(",",14));
        String items3="Sicil:"+result.substring(result.indexOf(",",30),result.indexOf(",",40));
        String items4="Tel:"+result.substring(result.indexOf(",",40),result.indexOf(",",50));
            tc.setText(items1);
           isim.setText(items2);
            sicil.setText(items3);
            tel.setText(items4);

   //     List<String> itemList = Arrays.asList(items);
        //System.out.println(itemList.size());
      //  System.out.println(itemList.get(1));
     //   System.out.println(items[2]);

        //isim.setText(itemList.get(2));
        //sicil.setText(itemList.get(3));
        //tel.setText(itemList.get(4));

        return myView;
    }


}
