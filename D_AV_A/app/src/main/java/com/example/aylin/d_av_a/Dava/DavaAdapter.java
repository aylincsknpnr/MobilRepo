package com.example.aylin.d_av_a.Dava;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aylin.d_av_a.R;

/**
 * Created by aylin on 26.05.2016.
 */
public class DavaAdapter extends RecyclerView.Adapter<DavaAdapter.MyViewHolder>{
    public static ClickListener clicklistener = null;
         public String[] mDataset1;
         public String[] mDataset2;
         public String[] mDataset3;
      public static   Context context;
        public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView birim,date,dosyaN;

            public MyViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                this.birim = (TextView) itemView.findViewById(R.id.birim);
                this.date=(TextView)itemView.findViewById(R.id.date);
                this.dosyaN=(TextView)itemView.findViewById(R.id.dosyano);

            }
            @Override
            public void onClick(View v) {
                if (clicklistener != null) {
                    clicklistener.itemClicked(v, getAdapterPosition());
                }
            }
        }

        public DavaAdapter( String[] mDataset1, String[] mDataset2, String[] mDataset3) {
            this.mDataset1 = mDataset1;
            this.mDataset2 = mDataset2;
            this.mDataset3 = mDataset3;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.dava_content, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(view);

            return myViewHolder;
        }

    public void setClickListener(ClickListener clicklistener ) {
        this.clicklistener = clicklistener ;
    }
        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
            TextView birim = holder.birim;
            TextView date = holder.date;
            TextView dosyaN = holder.dosyaN;
            birim.setText(mDataset1[listPosition]);
            date.setText(mDataset2[listPosition]);
            dosyaN.setText(mDataset3[listPosition]);
                    //.toString().replace(",","\n").replace("#","\n"));
        }

        @Override
        public int getItemCount() {
            return (mDataset1.length);
        }

    }
