package com.example.aylin.avukep.Login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.Bind;
import com.example.aylin.avukep.R;
import com.example.aylin.avukep.User.UserInfoDatabaseHelper;
import com.example.aylin.avukep.User.UserInfoModel;
import com.example.aylin.avukep.User.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aylin on 09.05.2016.
 */
public class LoginActivity extends AppCompatActivity {
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
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.un) EditText _emailText;
    @Bind(R.id.pw) EditText _passwordText;
    @Bind(R.id.add) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;
    public ArrayList<String> array = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        db = new DatabaseHelper(this);
        uidb = new UserInfoDatabaseHelper(this);
        drawerUsername=(TextView)findViewById(R.id.username);
        drawerAccount=(TextView)findViewById(R.id.email);
        if (giriskontrol(getApplicationContext())) {//önceden giriş yapmış ise

            startActivity(new Intent("android.intent.action.FIRST"));

        } else {//giriş yapmamış ise login sayfasına yönlenecek
            Toast.makeText(getApplicationContext(), "Lütfen Giriş Yapınız!", Toast.LENGTH_SHORT).show();
        }
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

}
    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        try {
            rslt = "START";
            Caller c = new Caller();
            c.a =email;
            c.b = password;
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
            //Toast.makeText(getApplicationContext(), rslt, Toast.LENGTH_SHORT).show();
            //kullanıcı veritabanına ekleniyor
            UserModel dbim = new UserModel();
            dbim.username = email;
            dbim.password = password;
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
        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }
    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty()) {
            _emailText.setError(Html.fromHtml("<font color='red'>enter a valid email username</font>"));
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 3 || password.length() > 10) {
            _passwordText.setError(Html.fromHtml("<font color='red'>between 3 and 10 alphanumeric characters</font>"));
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
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