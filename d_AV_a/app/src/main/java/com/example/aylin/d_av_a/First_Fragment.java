package com.example.aylin.d_av_a;
/**
 * Created by aylin on 05.05.2016.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class First_Fragment extends Fragment {
    RecyclerView recyclerView;
    List<UserInfoModel> userInfoModels;
    TextView tv3;
    Caller c=new Caller();
    UserInfoDatabaseHelper uidb;
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v=inflater.inflate(R.layout.first_layout, container, false);
        uidb=new UserInfoDatabaseHelper(getActivity());

        recyclerView= (RecyclerView) v.findViewById(R.id.my_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        KayitGoster();
        Recyclerview adapter=new Recyclerview(getActivity(),userInfoModels);
        recyclerView.setAdapter(adapter);


        return v;
    }
    public void KayitGoster() {
        userInfoModels=new ArrayList<>();
        SQLiteDatabase db = uidb.getReadableDatabase();
        String selectQuery = "SELECT * FROM davaUserInfo";
        Cursor c = db.rawQuery(selectQuery, null);
        userInfoModels.clear(); int id = 0;
        String tc = "";
        String avukat="";
        String sicil="";
        String tel="";
        String gelen="";
        while (c.moveToNext()) {
            id = c.getInt(c.getColumnIndex("id"));
            tc=c.getString(c.getColumnIndex("tc"));
            avukat=c.getString(c.getColumnIndex("avukat"));
            sicil=c.getString(c.getColumnIndex("sicil"));
            tel=c.getString(c.getColumnIndex("tel"));
            gelen+=id+" "+tc+" "+avukat+" "+sicil+" "+tel+"\n";
            userInfoModels.add(new UserInfoModel(id, tc, avukat, sicil, tel));
        }
    }
}