package com.example.aylin.dav_a;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aylin.dav_a.R;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by aylin on 16.04.2016.
 */
public class Third_Fragment extends Fragment {
    View myView;
    List<UserModel> list = new ArrayList<UserModel>();
   public static List<UserInfoModel>list2=new ArrayList<UserInfoModel>();
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
        myView=inflater.inflate(R.layout.third_layout, container, false);
        try{
            super.onCreate(savedInstanceState);
            username = (EditText) myView.findViewById(R.id.un);
            password = (EditText) myView.findViewById(R.id.pw);
            tv = (TextView) myView.findViewById(R.id.tv);
            id2 = (EditText) myView.findViewById(R.id.id2);
            Button add = (Button) myView.findViewById(R.id.add);
            add.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    tv.setText("");
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
                    uım.sicil=strArray[3];
                    uım.tel=strArray[4];
                    uidb.addUserDetail(uım);
                    list2=uidb.getAllUserInfoList();
                    print(list2);
                }


            });
            Button b1=(Button) myView.findViewById(R.id.delete);
            b1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    tv.setText("");
                    String user_id = id2.getText().toString();
                    db.deleteEntry(Integer.parseInt(user_id));
                    list = db.getAllUsersList();
                  //  print(list);
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        return myView;
        }

    private void print(List<UserInfoModel> list2) {
// TODO Auto-generated method stub
        String value = "";
       /* for(UserModel sm : list){
            value = value+"id: "+sm.id+", name: "+sm.username+" Ph_no: "+sm.password+"\n";
        }*/
        for (UserInfoModel uı:list2){
            value=value+"id: "+uı.id +", tc: "+uı.tc+"avukat: "+uı.avukat+"sicil: "+uı.sicil+"tel :"+uı.tel+"\n";
            System.out.println("liste başarılı" +value);
        }
        tv.setText(value);
    }

    public static boolean giriskontrol(Context context){
        DatabaseHelper db = new DatabaseHelper(context);
        int count = db.getRowCount();// databasedeki table logindeki row sayısı
        if(count > 0){//0 dan fazla ise giriş yapmıs önceden demek
//kullanıcı giriş yapmıs
            return true;
        }
        return false;
    }
    public List<UserInfoModel> returnDatabaseValue(){
        return list2;
    }
}
