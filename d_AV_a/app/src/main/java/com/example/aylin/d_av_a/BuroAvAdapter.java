package com.example.aylin.d_av_a;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aylin on 23.05.2016.
 */
public class BuroAvAdapter extends RecyclerView.Adapter<BuroAvAdapter.MyViewHolder>{

        private ArrayList<BuroAvModel> dataSet;

        public static class MyViewHolder extends RecyclerView.ViewHolder {

            TextView value;

            public MyViewHolder(View itemView) {
                super(itemView);
                this.value = (TextView) itemView.findViewById(R.id.adsoyad);
            }
        }

        public BuroAvAdapter(ArrayList<BuroAvModel> data) {
            this.dataSet = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.buroav_content, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

            TextView value = holder.value;
            value.setText(dataSet.get(listPosition).getValue());
        }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }
    }

