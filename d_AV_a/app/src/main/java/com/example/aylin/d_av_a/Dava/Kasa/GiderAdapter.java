package com.example.aylin.d_av_a.Dava.Kasa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aylin.d_av_a.Dava.ClickListener;
import com.example.aylin.d_av_a.R;

/**
 * Created by aylin on 29.06.2016.
 */
public class GiderAdapter extends RecyclerView.Adapter<GiderAdapter.MyViewHolder>{
        public static ClickListener clicklistener = null;
        public String[] mDataset_3;
        public String[] mDataset_4;
        public String[] mDataset_5;
        public static Context context;
        public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView aciklama2,date2,para2;
            int sayac=0;
            public MyViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                this.aciklama2 = (TextView) itemView.findViewById(R.id.g_aciklama);
                this.date2=(TextView)itemView.findViewById(R.id.g_date);
                this.para2=(TextView)itemView.findViewById(R.id.g_para);
                para2.setVisibility(View.GONE);
            }
            @Override
            public void onClick(View v) {
                if (clicklistener != null) {
                    clicklistener.itemClicked(v, getAdapterPosition());
                    if(sayac%2==0) {
                        para2.setVisibility(View.VISIBLE);
                    }
                    else {
                        para2.setVisibility(View.GONE);
                    }++sayac;
                }
            }
        }

        public GiderAdapter( String[] mDataset3, String[] mDataset4, String[] mDataset5) {
            this.mDataset_3 = mDataset3;
            this.mDataset_4 = mDataset4;
            this.mDataset_5 = mDataset5;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.kasagider_content, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(view);

            return myViewHolder;
        }

        public void setClickListener(GiderFragment clicklistener ) {
            this.clicklistener = clicklistener ;
        }
        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
            TextView aciklama2 = holder.aciklama2;
            TextView date2 = holder.date2;
            TextView para2 = holder.para2;
            aciklama2.setText(mDataset_3[listPosition]);
            date2.setText(mDataset_4[listPosition]);
            para2.setText(mDataset_5[listPosition]);
        }

        @Override
        public int getItemCount() {
            return (mDataset_5.length-1);
        }
    }
