package com.example.aylin.d_av_a;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView tv1,tv2, tv3, tv4;
    ImageView imageView;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        tv1 = (TextView) itemView.findViewById(R.id.ad);
        tv2 = (TextView) itemView.findViewById(R.id.tc);
        tv3= (TextView) itemView.findViewById(R.id.sicil);
        tv4 = (TextView) itemView.findViewById(R.id.tel);
    }
}