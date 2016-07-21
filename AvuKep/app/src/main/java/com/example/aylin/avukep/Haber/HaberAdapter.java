package com.example.aylin.avukep.Haber;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aylin.avukep.Duyuru.ClickListener;
import com.example.aylin.avukep.R;

/**
 * Created by aylin on 15.06.2016.
 */
public class HaberAdapter extends RecyclerView.Adapter<HaberAdapter.MyViewHolder>{
        public static ClickListener clicklistener = null;
        public String[] mDataset_1;
        public String[] mDataset_2;
        public String[] mDataset_3;
        public static Context context;
        public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView baslik,date,detay;
            int sayac=0;
            public MyViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                this.baslik = (TextView) itemView.findViewById(R.id.h_baslik);
               this.date=(TextView)itemView.findViewById(R.id.h_date);
                this.detay=(TextView)itemView.findViewById(R.id.h_detay);
                detay.setVisibility(View.GONE);
            }
            @Override
            public void onClick(View v) {
                if (clicklistener != null) {
                    clicklistener.itemClicked(v, getAdapterPosition());
                    if(sayac%2!=0) {
                        detay.setVisibility(View.GONE);
                    }
                    else {
                        detay.setVisibility(View.VISIBLE);
                    }
                    ++sayac;
                }
            }
        }

        public HaberAdapter(String[] mDataset1, String[] mDataset2, String[] mDataset3) {
            this.mDataset_1 = mDataset1;
            this.mDataset_2 = mDataset2;
            this.mDataset_3 = mDataset3;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.haber_content, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(view);

            return myViewHolder;
        }

        public void setClickListener(HaberFragment clicklistener ) {
          this.clicklistener = (ClickListener) clicklistener;
      }
        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
            TextView baslik = holder.baslik;
          TextView date = holder.date;
            TextView detay = holder.detay;
            baslik.setText(mDataset_1[listPosition]);
           date.setText(mDataset_2[listPosition]);
            detay.setText(mDataset_3[listPosition]);
        }

        @Override
        public int getItemCount() {
            return (mDataset_3.length-1);
        }

    }

