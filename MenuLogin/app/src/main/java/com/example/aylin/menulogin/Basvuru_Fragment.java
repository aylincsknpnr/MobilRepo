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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by aylin on 27.04.2016.
 */
public class Basvuru_Fragment extends Fragment {

    View myView;
    public static String rslt3="";

    BasvuruCaller  bc=new BasvuruCaller();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        myView=inflater.inflate(R.layout.basvuru_layout, container, false);

        final GridView grid = (GridView) myView.findViewById(R.id.gridView1);
        final ArrayList<String> items = new ArrayList<String>();

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

                String  result=bc.ReturnValue();
                result = result.replace("[","");
                result = result.replace("]","");
                result = result.replace(" ","");
                System.out.println("Dönenin Resultı:"+result);
                int index=result.indexOf(",");
               //

                items.add(result.substring(0, result.indexOf(",", 10)));
                items.add(result.substring(result.indexOf(",", 10), result.indexOf(",", 12)));
                items.add(result.substring(result.indexOf(",", 12), result.indexOf(",", 20)));
                items.add(result.substring(result.indexOf(",", 20), result.indexOf(",", 25)));
                items.add(result.substring(result.indexOf(",", 25), result.indexOf(",", 46)));
                items.add(result.substring(result.indexOf(",", 46), result.indexOf(",", 53)));
                System.out.println("items :" + items);
                grid.setAdapter(new GridAdapter(items));

            } });
        return myView;
    }
    private static final int ROW_ITEMS = 3;

    private static final class GridAdapter extends BaseAdapter {

        final ArrayList<String> mItems;
        final int mCount;

        /**
         * Default constructor
         * @param items to fill data to
         */
        private GridAdapter(final ArrayList<String> items) {

            mCount = items.size() ;
            mItems = new ArrayList<String>(mCount);

            // for small size of items it's ok to do it here, sync way
            for (String item : items) {
                // get separate string parts, divided by ,
                final String[] parts = item.split(",");

                // remove spaces from parts
                for (String part : parts) {

                    mItems.add(part);
                }
            }
        }

        @Override
        public int getCount() {
            return mCount;
        }

        @Override
        public Object getItem(final int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(final int position) {
            return position;
        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {

            View view = convertView;

            if (view == null) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basvuru_layout, parent, false);
            }

            final TextView text = (TextView) view.findViewById(R.id.GridShow);

            text.setText(mItems.get(position));

            return view;
        }
    }
}

