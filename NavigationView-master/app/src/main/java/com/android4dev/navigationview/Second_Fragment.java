package com.android4dev.navigationview;

/**
 * Created by aylin on 05.05.2016.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Second_Fragment extends Fragment {
    View v;
    public static String rslt="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v=inflater.inflate(R.layout.second_layout, container, false);

        return v;
    }
}
