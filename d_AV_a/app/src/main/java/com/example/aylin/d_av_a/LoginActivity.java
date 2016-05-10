package com.example.aylin.d_av_a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aylin on 09.05.2016.
 */
public class LoginActivity extends Activity {
    UserInfoDatabaseHelper udb;
    List<UserModel> list = new ArrayList<UserModel>();
    public static List<UserInfoModel> list2 = new ArrayList<UserInfoModel>();
    EditText username, password, id2;
    TextView tv;
    public CallSoap cs;
    public static String rslt = "";
    Caller c;
    DatabaseHelper db;
    UserInfoDatabaseHelper uidb;
    TextView drawerUsername;
    TextView drawerAccount;
    public ArrayList<String> array = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);
        db = new DatabaseHelper(this);

        uidb = new UserInfoDatabaseHelper(this);
        username = (EditText) findViewById(R.id.un);
        password = (EditText) findViewById(R.id.pw);
        Button add = (Button) findViewById(R.id.add);
        drawerUsername=(TextView)findViewById(R.id.username);
        drawerAccount=(TextView)findViewById(R.id.email);
        if (giriskontrol(getApplicationContext())) {//önceden giriş yapmış ise

            startActivity(new Intent("android.intent.action.FIRST"));

        } else {//giriş yapmamış ise login sayfasına yönlenecek
            Toast.makeText(getApplicationContext(), "Lütfen Giriş Yapınız!", Toast.LENGTH_SHORT).show();
        }
        try {
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
                                Toast.makeText(getApplicationContext(), "Başarısız Giriş", Toast.LENGTH_SHORT).show();
                            }
                        }
                        System.out.println("Result:" + rslt);
                        Toast.makeText(getApplicationContext(), rslt, Toast.LENGTH_SHORT).show();
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
                        result = result.replace("]", "");
                        String strArray[] = result.split(",");
                        System.out.println("strarray" + strArray[1]);
                        UserInfoModel uım = new UserInfoModel();
                        //  uım.id=Integer.parseInt(strArray[0]);
                        uım.tc = strArray[1];
                        uım.avukat = strArray[2];
                        uım.sicil = strArray[3];
                        uım.tel = strArray[4];
                        uidb.addUserDetail(uım);
                        list2 = uidb.getAllUserInfoList();
                        startActivity(new Intent("android.intent.action.FIRST"));
                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                        Toast.makeText(getApplicationContext(), "Başarısız Giriş", Toast.LENGTH_SHORT).show();
                    }
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this.getApplicationContext(), "Başarısız Giriş", Toast.LENGTH_SHORT).show();
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
    public void KayitGoster() {
        SQLiteDatabase db = uidb.getReadableDatabase();
        String selectQuery = "SELECT  * FROM  davaUserInfo";
        Cursor c = db.rawQuery(selectQuery, null);
        array.clear(); int id = 0;
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
            array.add(id+  "  "+tc+  "  "+avukat+  "  "+sicil+ " "+tel);
        }
    }
}