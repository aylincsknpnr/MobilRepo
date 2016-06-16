package com.example.aylin.d_av_a.Duyuru;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aylin.d_av_a.Dava.ClickListener;
import com.example.aylin.d_av_a.R;

/**
 * Created by aylin on 15.06.2016.
 */
public class DuyuruAdapter extends RecyclerView.Adapter<DuyuruAdapter.MyViewHolder>{
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
                this.baslik = (TextView) itemView.findViewById(R.id.baslik);
                this.date=(TextView)itemView.findViewById(R.id.d_date);
                this.detay=(TextView)itemView.findViewById(R.id.detay);
                detay.setVisibility(View.GONE);
            }
            @Override
            public void onClick(View v) {
                if (clicklistener != null) {
                    clicklistener.itemClicked(v, getAdapterPosition());
                    if(sayac%2==0) {
                        detay.setVisibility(View.GONE);
                    }
                    else {
                        detay.setVisibility(View.VISIBLE);
                    }
                    ++sayac;
                }
            }
        }

        public DuyuruAdapter( String[] mDataset1, String[] mDataset2, String[] mDataset3) {
            this.mDataset_1 = mDataset1;
            this.mDataset_2 = mDataset2;
            this.mDataset_3 = mDataset3;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.duyuru_content, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(view);

            return myViewHolder;
        }

        public void setClickListener(DuyuruFragment clicklistener ) {
          this.clicklistener = clicklistener ;
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

