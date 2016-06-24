package com.example.aylin.d_av_a.AnaSayfa;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aylin.d_av_a.Dava.ClickListener;
import com.example.aylin.d_av_a.Duyuru.DuyuruFragment;
import com.example.aylin.d_av_a.R;

/**
 * Created by aylin on 18.06.2016.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
        public static ClickListener clicklistener = null;
        public String[] mDataset_1;
        public String[] mDataset_2;
        public String[] mDataset_3;
        public static Context context;
        public static  CardView card;
        public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView baslik,saat;
            ImageView icon;
            public MyViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                this.baslik = (TextView) itemView.findViewById(R.id.anatur1);
                this.saat=(TextView)itemView.findViewById(R.id.hour);

               // this.icon=(ImageView)itemView.findViewById(R.id.cloud);
              //  card=(CardView) itemView.findViewById(R.id.cardMAin);
            }
            @Override
            public void onClick(View v) {
                if (clicklistener != null) {
                    clicklistener.itemClicked(v, getAdapterPosition());
                }
            }
        }

        public HomeAdapter( String[] mDataset1, String[] mDataset_3) {
            this.mDataset_1 = mDataset1;
           // this.mDataset_2 = mDataset2;
            this.mDataset_3 = mDataset_3;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.home_content, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(view);

            return myViewHolder;
        }

        public void setClickListener(DuyuruFragment clicklistener ) {
            this.clicklistener = clicklistener ;
        }
        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
            TextView baslik = holder.baslik;
         //   ImageView icon = holder.icon;
            baslik.setText(mDataset_1[listPosition]);
            TextView saat=holder.saat;
            saat.setText((mDataset_3[listPosition]).substring(0, 5));
         /*   if (Integer.parseInt(mDataset_2[listPosition])==2){
                icon.setImageResource(R.drawable.ic_cloud_queue_black_24dp);
              //  card.setCardBackgroundColor(Color.YELLOW);
            }
            else if (Integer.parseInt(mDataset_2[listPosition])==1){
                icon.setImageResource(R.drawable.duyurum);
                //card.setCardBackgroundColor(Color.GREEN);
            }*/
        }

        @Override
        public int getItemCount() {
            return (mDataset_1.length-1);
        }
    }
