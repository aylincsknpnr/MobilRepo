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
        public String[] mDataset;
      public static   Context context;
        public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView dava;

            public MyViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                this.dava = (TextView) itemView.findViewById(R.id.dava);

            }
            @Override
            public void onClick(View v) {
                if (clicklistener != null) {
                    clicklistener.itemClicked(v, getAdapterPosition());
                }
            }
        }

        public DavaAdapter( String[] mDataset) {
            this.mDataset = mDataset;
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
            TextView dava = holder.dava;
            dava.setText(mDataset[listPosition].toString().replace(",","\n").replace("#","\n"));
        }

        @Override
        public int getItemCount() {
            return (mDataset.length);
        }

    }
