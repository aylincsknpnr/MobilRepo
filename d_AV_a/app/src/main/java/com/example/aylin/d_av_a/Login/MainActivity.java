package com.example.aylin.d_av_a.Login;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.aylin.d_av_a.Ajanda.MainFragment;
import com.example.aylin.d_av_a.Ajanda.MainFragment;
import com.example.aylin.d_av_a.AnaSayfa.ContentFragment;
import com.example.aylin.d_av_a.Buro.Buro_Fragment;
import com.example.aylin.d_av_a.BuroAvukat.BuroAvukatFragment;
import com.example.aylin.d_av_a.BuroDava.BuroDava_Fragment;
import com.example.aylin.d_av_a.Dava.Dava_Fragment;
import com.example.aylin.d_av_a.Duyuru.DuyuruFragment;
import com.example.aylin.d_av_a.R;
import com.example.aylin.d_av_a.User.First_Fragment;
import com.example.aylin.d_av_a.User.UserInfoDatabaseHelper;
import com.example.aylin.d_av_a.User.UserModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //my add
    Button addButton, deleteButton;
    TextView tv;
    List<UserModel> list = new ArrayList<UserModel>();
    DatabaseHelper db;
    UserInfoDatabaseHelper udb;
    public CallSoap cs;
    public Second_Fragment sf;
    TextView drawerUsername;
     TextView drawerAccount;
    LoginActivity la;
    ImageView profile;

    First_Fragment ff;
    public ArrayList<String> array = new ArrayList<String>();

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        udb=new UserInfoDatabaseHelper(this);
        drawerUsername=(TextView)findViewById(R.id.username);
        drawerAccount=(TextView)findViewById(R.id.email);
        profile=(ImageView)findViewById(R.id.profile_image);
        la=new LoginActivity();
        if (LoginActivity.giriskontrol(getApplicationContext())) {//önceden giriş yapmış ise
         KayitGoster();
            String [] result;
            String listString = "";

            for (String s : array)
            {
                listString += s + "\t";
            }
            result=listString.split("  ");
            drawerUsername.setText(result[2]);
            drawerAccount.setText(result[3]);
            ContentFragment contentFragment = new ContentFragment();
            android.support.v4.app.FragmentTransaction fragmentTransactioncon = getSupportFragmentManager().beginTransaction();
            fragmentTransactioncon.replace(R.id.frame, contentFragment);
            fragmentTransactioncon.commit();
            Picasso.with(this).load("http://dmzws.barokart.com.tr/dmz.xml.info/TBB2Image.ashx?id=6&baroid="+result[3].replace(" ", "")+"&t=1").into(profile);
        } else {//giriş yapmamış ise login sayfasına yönlenecek
            drawerUsername.setText("Unknown Username");
            drawerAccount.setText("Unknown Info");
        }
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        // View headerView = navigationView.inflateHeaderView(R.layout.header);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
              case R.id.main:
                        MainFragment mainFragment = new MainFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction214 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction214.replace(R.id.frame, mainFragment);
                        fragmentTransaction214.commit();
                        return true;
                    case R.id.duyuru:
                        DuyuruFragment duyuruFragment = new DuyuruFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransactionduyuru = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionduyuru.replace(R.id.frame, duyuruFragment);
                        fragmentTransactionduyuru.commit();
                        return true;

                    case R.id.inbox:
                        Buro_Fragment buro_fragment = new Buro_Fragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction21 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction21.replace(R.id.frame, buro_fragment);
                        fragmentTransaction21.commit();
                        return true;

                    case R.id.starred:

                        First_Fragment first_fragment = new First_Fragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.frame, first_fragment);
                        fragmentTransaction1.commit();
                        return true;
                    case R.id.sent_mail:

                        BuroAvukatFragment buroAvukatFragment = new BuroAvukatFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction31 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction31.replace(R.id.frame, buroAvukatFragment);
                        fragmentTransaction31.commit();
                        return true;
                    case R.id.drafts:
                        Dava_Fragment dava_fragment = new Dava_Fragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction123 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction123.replace(R.id.frame, dava_fragment);
                        fragmentTransaction123.commit();
                        return true;
                    case R.id.drafts2:
                        BuroDava_Fragment buroDava_fragment = new BuroDava_Fragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction223 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction223.replace(R.id.frame, buroDava_fragment);
                        fragmentTransaction223.commit();
                        return true;
                    case R.id.ana:
                        ContentFragment contentFragment = new ContentFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransactionana = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionana.replace(R.id.frame, contentFragment);
                        fragmentTransactionana.commit();
                        return true;
                    case R.id.allmail:
                        drawerUsername.setText("Unknown Username");
                        drawerAccount.setText("Unknown Info");
                        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                        db.resetTables(); //Databasi sıfırlıyoruz.Verileri siliyoruz.Ve Login sayfasına gidiyoruz.
                        UserInfoDatabaseHelper udb = new UserInfoDatabaseHelper(getApplicationContext());
                        udb.resetTables();

                        Second_Fragment second_fragment2 = new Second_Fragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction22 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction22.replace(R.id.frame, second_fragment2);
                        fragmentTransaction22.commit();
                        //startActivity(new Intent("android.intent.action.MAIN"));
                        return true;

                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }

        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void KayitGoster() {
        SQLiteDatabase db = udb.getReadableDatabase();
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