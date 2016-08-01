package com.example.aylin.avukep.Anasayfa;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aylin.avukep.Kep.KepAdapter;
import com.example.aylin.avukep.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AnaFragment extends android.support.v4.app.Fragment {
    private TextView txt_Sehir, txt_Sicaklik, txt_Weather, txt_Aciklama;
    private Button buton;
    private EditText editText;
    public ListView list;
    private ImageView im1,im2,im3,im4,im5,im6,im7;
    private TextView th1,th2,th3,th4,th5,th6,th7;
    private TextView pzt,sal,crs,prs,cum,cts,pzr,ay;
    String sehir;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mainpage, container, false);
       // txt_Sehir = (TextView) v.findViewById(R.id.txt_sehir);
        //txt_Aciklama = (TextView) v.findViewById(R.id.txt_aciklama);
        //txt_Sicaklik = (TextView) v.findViewById(R.id.txt_sicaklik);
        //txt_Weather = (TextView) v.findViewById(R.id.txt_weather);
      //  buton = (Button) v.findViewById(R.id.button);
        //editText = (EditText) v.findViewById(R.id.editText);
        im1 = (ImageView) v.findViewById(R.id.hi1);
        im2 = (ImageView) v.findViewById(R.id.hi2);
        im3 = (ImageView) v.findViewById(R.id.hi3);
        im4 = (ImageView) v.findViewById(R.id.hi4);
        im5 = (ImageView) v.findViewById(R.id.hi5);
        im6 = (ImageView) v.findViewById(R.id.hi6);
        im7 = (ImageView) v.findViewById(R.id.hi7);

        th1=(TextView)v.findViewById(R.id.th1);
        th2=(TextView)v.findViewById(R.id.th2);
        th3=(TextView)v.findViewById(R.id.th3);
        th4=(TextView)v.findViewById(R.id.th4);
        th5=(TextView)v.findViewById(R.id.th5);
        th6=(TextView)v.findViewById(R.id.th6);
        th7=(TextView)v.findViewById(R.id.th7);

        pzt=(TextView)v.findViewById(R.id.Pzt);
        sal=(TextView)v.findViewById(R.id.Sal);
        crs=(TextView)v.findViewById(R.id.Crs);
        prs=(TextView)v.findViewById(R.id.Prs);
        cum=(TextView)v.findViewById(R.id.Cum);
        cts=(TextView)v.findViewById(R.id.Cts);
        pzr=(TextView)v.findViewById(R.id.Paz);
        ay=(TextView)v.findViewById(R.id.ay);
        JsonParse jsonParse = new JsonParse();
       // sehir = String.valueOf(editText.getText());
        sehir = String.valueOf("Ankara");
        new JsonParse().execute();


        Date simdikiZaman = new Date();
        System.out.println(simdikiZaman.toString());
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println(df.format(simdikiZaman));
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        int fark=dayOfWeek-2;
        int day=c.get(Calendar.DATE);
        String month=new SimpleDateFormat("MMM").format(c.getTime());
        ay.setText(month);
        int firstweek=day-fark;

        System.out.println(firstweek);
        pzt.setText(Integer.toString(firstweek));
        sal.setText(Integer.toString(firstweek + 1));
        crs.setText(Integer.toString(firstweek+2));
        prs.setText(Integer.toString(firstweek+3));
        cum.setText(Integer.toString(firstweek + 4));
        cum.setTextColor(Color.YELLOW);
        cts.setText(Integer.toString(firstweek + 5));
        pzr.setText(Integer.toString(firstweek + 6));
        String [] timearray={"10:30","12:50"};
        String [] noteArray={"Ankara Barosu Toplantı","Yeni büro açılışı"};
        mainAdapter mainAdapter = new
                mainAdapter(getActivity(),timearray,noteArray);
        list=(ListView)v.findViewById(R.id.event);
        list.setAdapter(mainAdapter);
        return v;
    }
    protected class JsonParse extends AsyncTask<Void, Void, Void>{
        List<String> mainlist = new ArrayList<String>();
        List<String> desclist = new ArrayList<String>();
        List<String> iconlist = new ArrayList<String>();
        List<Bitmap> bitmaplist=new ArrayList<Bitmap>();
        List<String> templist=new ArrayList<String>();
        String result_main="";
        String result_description="";
        String result_icon = "";
        int result_temp=0;
        String result_city;
        Bitmap bitImage;
        @Override
        protected Void doInBackground(Void... params) {
            String result="";
            try {
                URL weather_url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q="+sehir+"&mode=json&cnt=7&appid=5519df78a91952f50079565124888a76");
//HttpURLConnection weather_url_con = (HttpURLConnection) weather_url.openConnection();
//InputStreamReader inputStreamReader = new InputStreamReader(weather_url_con.getInputStream());
                BufferedReader bufferedReader = null;
                bufferedReader = new BufferedReader(new InputStreamReader(weather_url.openStream()));
                String line = null;
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                JSONObject jsonObject_city = jsonObject.getJSONObject("city");
                result_city = jsonObject_city.getString("name");

                for(int i=0; i<jsonArray.length();i++){
                    JSONObject jsonObject_list = jsonArray.getJSONObject(i);
                    JSONObject jsonObject_temp = jsonObject_list.getJSONObject("temp");
                    Double temp = jsonObject_temp.getDouble("day");
                    temp=temp-273;
                    result_temp = (int) (temp.intValue());

                    JSONArray jsonArray_2=jsonObject_list.getJSONArray("weather");
                  JSONObject jsonObject_list2 = jsonArray_2.getJSONObject(0);
                   result_main = jsonObject_list2.getString("main");
                    result_description = jsonObject_list2.getString("description");
                    result_icon = jsonObject_list2.getString("icon");
                    URL icon_url = new URL("http://openweathermap.org/img/w/"+result_icon+".png");
                    bitImage = BitmapFactory.decodeStream(icon_url.openConnection().getInputStream());
                    bitmaplist.add(bitImage);
                    mainlist.add(result_main);
                    templist.add(Integer.toString(result_temp)+"°C");
                    System.out.println(result_temp);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
           // txt_Sicaklik.setText(String.valueOf(result_temp[0]));
           // txt_Weather.setText(result_main[0]+result_main[3]);
            //txt_Sehir.setText(result_city);
            //txt_Aciklama.setText(result_description[0]);
           // txt_Aciklama.setText(mainlist.toString());
            im1.setImageBitmap(bitmaplist.get(0));
            im2.setImageBitmap(bitmaplist.get(1));
            im3.setImageBitmap(bitmaplist.get(2));
            im4.setImageBitmap(bitmaplist.get(3));
            im5.setImageBitmap(bitmaplist.get(4));
            im6.setImageBitmap(bitmaplist.get(5));
            im7.setImageBitmap(bitmaplist.get(6));

            th1.setText(templist.get(0).toString());
            th2.setText(templist.get(1).toString());
            th3.setText(templist.get(2).toString());
            th4.setText(templist.get(3).toString());
            th5.setText(templist.get(4).toString());
            th6.setText(templist.get(5).toString());
            th7.setText(templist.get(6).toString());

            super.onPostExecute(aVoid);
        }
    }
   }