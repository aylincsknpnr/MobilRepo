package com.example.aylin.d_av_a.BuroDava;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aylin.d_av_a.R;

/**
 * Created by aylin on 26.05.2016.
 */
public class BuroDavaAdapter extends RecyclerView.Adapter<BuroDavaAdapter.MyViewHolder>{
        public String[] mDataset;
        public  static View view;
        public static class MyViewHolder extends RecyclerView.ViewHolder {

            TextView dava;

            public MyViewHolder(View itemView) {
                super(itemView);
                view=itemView;
                this.dava = (TextView) itemView.findViewById(R.id.burodava);

            }
        }

        public BuroDavaAdapter( String[] mDataset) {
            this.mDataset = mDataset;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.buro_dava_content, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
            TextView dava = holder.dava;
            dava.setText(mDataset[listPosition].toString().replace("#", "\n"));
        }

        @Override
        public int getItemCount() {
            return (mDataset.length);
        }
    }
