package com.example.aylin.d_av_a.Dava.Kasa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aylin.d_av_a.Dava.ClickListener;
import com.example.aylin.d_av_a.Dava.Kasa.KasaFragment;
import com.example.aylin.d_av_a.R;

/**
 * Created by aylin on 29.06.2016.
 */
public class KasaAdapter extends RecyclerView.Adapter<KasaAdapter.MyViewHolder>{
        public static ClickListener clicklistener = null;
        public String[] mDataset_1;
        public String[] mDataset_2;
        public String[] mDataset_3;
        public static Context context;
        public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView aciklama,date,para;
            int sayac=0;
            public MyViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                this.aciklama = (TextView) itemView.findViewById(R.id.kg_aciklama);
                this.date=(TextView)itemView.findViewById(R.id.kg_date);
               this.para=(TextView)itemView.findViewById(R.id.kg_para);
               para.setVisibility(View.GONE);
            }
            @Override
            public void onClick(View v) {
                if (clicklistener != null) {
                    clicklistener.itemClicked(v, getAdapterPosition());
                    if(sayac%2==0) {
                        para.setVisibility(View.VISIBLE);
                    }
                    else {
                        para.setVisibility(View.GONE);
                    }++sayac;
                }
            }
        }

        public KasaAdapter( String[] mDataset1, String[] mDataset2, String[] mDataset3) {
            this.mDataset_1 = mDataset1;
            this.mDataset_2 = mDataset2;
            this.mDataset_3 = mDataset3;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.kasagelir_content, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(view);

            return myViewHolder;
        }

        public void setClickListener(KasaFragment clicklistener ) {
            this.clicklistener = clicklistener ;
        }
        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
            TextView aciklama = holder.aciklama;
            TextView date = holder.date;
            TextView para = holder.para;
            aciklama.setText(mDataset_1[listPosition]);
            date.setText(mDataset_2[listPosition]);
            para.setText(mDataset_3[listPosition]);
        }

        @Override
        public int getItemCount() {
            return (mDataset_3.length-1);
        }

    }