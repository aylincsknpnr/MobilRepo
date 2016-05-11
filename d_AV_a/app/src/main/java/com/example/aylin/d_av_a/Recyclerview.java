package com.example.aylin.d_av_a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aylin on 10.05.2016.
 */
public class Recyclerview extends RecyclerView.Adapter<RecyclerViewHolder>{
    public String[] result2;
    TextView tc,isim,sicil,tel,info;

    List<UserInfoModel> userInfoModels;
    Context context;
    LayoutInflater inflater;
    public Recyclerview(Context context, List<UserInfoModel> userInfoModels) {
        this.context=context;
        this.userInfoModels=userInfoModels;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.card_item, parent, false);
        RecyclerViewHolder viewHolder=new RecyclerViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        System.out.println("gelennnn:"+userInfoModels.get(0).avukat);
        holder.tv1.setText(userInfoModels.get(0).avukat);
        holder.tv2.setText(userInfoModels.get(0).tc);
        holder.tv3.setText(userInfoModels.get(0).sicil);
        holder.tv4.setText(userInfoModels.get(0).tel);
    }
    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerViewHolder vholder = (RecyclerViewHolder) v.getTag();
            int position = vholder.getPosition();
            Toast.makeText(context, "This is position " + position, Toast.LENGTH_LONG).show();
        }
    };
    @Override
    public int getItemCount() {
        return 1;
        //userInfoModels.size();
    }

}