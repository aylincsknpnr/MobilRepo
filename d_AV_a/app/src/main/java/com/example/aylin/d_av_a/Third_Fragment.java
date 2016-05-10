package com.example.aylin.d_av_a;

/**
 * Created by aylin on 05.05.2016.
 */

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Third_Fragment extends Fragment {
    View v;
    MainActivity ma;
    List<UserModel> list = new ArrayList<UserModel>();
    public static List<UserInfoModel> list2=new ArrayList<UserInfoModel>();
    EditText username, password, id2;
    TextView tv;
    public CallSoap cs;
    public static String rslt="";
    Caller c;
    DatabaseHelper db;
    UserInfoDatabaseHelper uidb;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        db = new DatabaseHelper(activity);
        uidb=new UserInfoDatabaseHelper(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v=inflater.inflate(R.layout.third_layout, container, false);
        ma=new MainActivity();

        try{
            super.onCreate(savedInstanceState);
            username = (EditText) v.findViewById(R.id.un);
            password = (EditText) v.findViewById(R.id.pw);

            Button add = (Button) v.findViewById(R.id.add);
            add.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    try {
                        rslt = "START";
                        Caller c = new Caller();
                        c.a = username.getText().toString();
                        c.b = password.getText().toString();
                        c.join();
                        c.start();
                        while (rslt == "START") {
                            try {
                                Thread.sleep(10);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        System.out.println("Result:" + rslt);
                        Toast.makeText(getActivity().getApplicationContext(), rslt, Toast.LENGTH_SHORT).show();
                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                    }
                    //kullanıcı veritabanına ekleniyor
                    UserModel dbim = new UserModel();
                    dbim.username = username.getText().toString();
                    dbim.password = password.getText().toString();
                    db.addUserDetail(dbim);
                    list = db.getAllUsersList();
                    // print(list);
                    System.out.println("başarılı");

//sonuc split edlip tek tek veritabanına ekleniyor
                    String result = rslt.replace("[", "");
                    result = result.replace("]","");
                    String strArray[] = result.split(",");
                    System.out.println("strarray"+strArray[1]);
                    UserInfoModel uım=new UserInfoModel();
                    //  uım.id=Integer.parseInt(strArray[0]);
                    uım.tc=strArray[1];
                    uım.avukat=strArray[2];
                    uım.sicil = strArray[3];
                    uım.tel=strArray[4];
                    uidb.addUserDetail(uım);
                    list2=uidb.getAllUserInfoList();

                    print(list2);

                }


            });

        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getActivity().getApplicationContext(), "Başarısız Giriş", Toast.LENGTH_SHORT).show();
        }
        return v;
    }

    private void print(List<UserInfoModel> list2) {
// TODO Auto-generated method stub
        String value = "";

        for (UserInfoModel uı:list2){
            value=value+"id: "+uı.id +", tc: "+uı.tc+"avukat: "+uı.avukat+"sicil: "+uı.sicil+"tel :"+uı.tel+"\n";
            System.out.println("liste başarılı" +value);
        }

    }

    public static boolean giriskontrol(Context context){
        DatabaseHelper db = new DatabaseHelper(context);
        int count = db.getRowCount();
        if(count > 0){
            return true;
        }
        return false;
    }
    public List<UserInfoModel> returnDatabaseValue(){
        return list2;
    }
}
