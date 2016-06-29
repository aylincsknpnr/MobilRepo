package com.example.aylin.avukep.Kep;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aylin.avukep.R;

/**
 * Created by aylin on 29.06.2016.
 */
public class KepAdapter extends RecyclerView.Adapter<KepAdapter.MyViewHolder>{
        public static ClickListener clicklistener = null;
        public String[] mDataset_1;
        public String[] mDataset_2;
        public String[] mDataset_3;
        public static Context context;
        public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView gonderen,gontarih,tebtarih;
            int sayac=0;
            public MyViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                this.gonderen = (TextView) itemView.findViewById(R.id.gonderim);
                this.gontarih=(TextView)itemView.findViewById(R.id.gontarih);
                this.tebtarih=(TextView)itemView.findViewById(R.id.tebligtarih);
               // detay.setVisibility(View.GONE);
            }
            @Override
            public void onClick(View v) {
                if (clicklistener != null) {
                    clicklistener.itemClicked(v, getAdapterPosition());
                 /*   if(sayac%2==0) {
                        detay.setVisibility(View.VISIBLE);
                    }
                    else {
                        detay.setVisibility(View.GONE);
                    }++sayac;*/
                }
            }
        }

        public KepAdapter( String[] mDataset1, String[] mDataset2, String[] mDataset3) {
            this.mDataset_1 = mDataset1;
            this.mDataset_2 = mDataset2;
            this.mDataset_3 = mDataset3;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.kep_content, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(view);

            return myViewHolder;
        }

        public void setClickListener(KepFragment clicklistener ) {
            this.clicklistener = clicklistener ;
        }
        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
            TextView gonderen = holder.gonderen;
            TextView gontarih = holder.gontarih;
            TextView tebtarih = holder.tebtarih;
            gonderen.setText(mDataset_1[listPosition]);
            gontarih.setText(mDataset_2[listPosition]);
            tebtarih.setText(mDataset_3[listPosition]);
        }

        @Override
        public int getItemCount() {
            return (mDataset_3.length-1);
        }

    }
