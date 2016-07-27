package com.example.aylin.avukep.Anasayfa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.aylin.avukep.R;

/**
 * Created by aylin on 26.07.2016.
 */
public class AnaFragment extends android.support.v4.app.Fragment  implements ClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mainpage, container, false);
        return v;
    }

    @Override
    public void itemClicked(View view, int position) {
        System.out.println("tik:" + position);
    }
}