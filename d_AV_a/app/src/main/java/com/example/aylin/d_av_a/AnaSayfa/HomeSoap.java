package com.example.aylin.d_av_a.AnaSayfa;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by aylin on 16.06.2016.
 */
public class HomeSoap {

        public static String [] a_tur;
        public static String [] a_baslik;
        public static String [] a_date;
        public static String [] a_detay;
        public static String [] a_icon;
        public static String [] a_durum;
        public static String [] a_kim;
        public static String [] a_hour;
        public String val1,val2,val3,val4,val5,val6,val7,val8,val9,val10,val11;
        public final String SOAP_ACTION2 = "http://tempuri.org/Get_AnaSayfaBildirim_Listesi";

        public  final String OPERATION_NAME2 = "Get_AnaSayfaBildirim_Listesi";

        public  final String WSDL_TARGET_NAMESPACE2 = "http://tempuri.org/";

        public  final String SOAP_ADDRESS2 = "http://213.14.73.146:82/gelincik/Davalarim_WebService.asmx";
        public HomeSoap()
        {
        }
        public String Call(String a, String b)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE2,OPERATION_NAME2);

            PropertyInfo pi2=new PropertyInfo();
            pi2.setName("avukattc");
            pi2.setValue(a);
            pi2.setType(String.class);
            request.addProperty(pi2);
            pi2=new PropertyInfo();
            pi2.setName("tarih");
            pi2.setValue(b);
            pi2.setType(String.class);
            request.addProperty(pi2);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS2);
            Object response=null;
            try
            {
                httpTransport.call(SOAP_ACTION2, envelope);

                ArrayList<String> x=new ArrayList<String>();
                SoapObject primitive= (SoapObject) envelope.getResponse();
                System.out.println(primitive);
              if  (primitive != null) {
                    System.out.println(primitive);
                    SoapObject diffgram = (SoapObject) primitive.getProperty("diffgram");
                    SoapObject NewDataSet = (SoapObject) diffgram.getProperty("DocumentElement");
                    String [] IdArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] TurArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] BaslikArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] TarihArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] DetayArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] DurumArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] IconArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] KimArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] SaatArray=new String[NewDataSet.getPropertyCount()+1];
                    for (int i = 0; i < NewDataSet.getPropertyCount(); i++) {
                        System.out.println("prim coun:"+primitive.getPropertyCount());
                        System.out.println("dataset xount:"+ NewDataSet.getPropertyCount());
                        SoapObject info=(SoapObject)NewDataSet.getProperty(i);
                        System.out.println("info+"+info);
                        String ıd=info.getProperty("id").toString();
                        String baslik=info.getProperty("konubaslik").toString();
                         String tarih=info.getProperty("tarih").toString();
                        String detay=info.getProperty("konudetay").toString();
                        String tur=info.getProperty("konutur").toString();
                        String icon=info.getProperty("icon").toString();
                        String durum=info.getProperty("durum").toString();
                        String kim=info.getProperty("konukimler").toString();
                        String saat=info.getProperty("saat").toString();

                        IdArray[i]=ıd;
                        BaslikArray[i]=baslik;
                        TarihArray[i]=tarih;
                        DetayArray[i]=detay;
                        TurArray[i]=tur;
                        IconArray[i]=icon;
                        DurumArray[i]=durum;
                        KimArray[i]=kim;
                        SaatArray[i]=saat;

                        x.add(IdArray[i]);
                        x.add(BaslikArray[i]);
                        x.add(TarihArray[i]);
                        x.add(DetayArray[i]);
                        x.add(TurArray[i]);
                        x.add(IconArray[i]);
                        x.add(DurumArray[i]);
                        x.add(KimArray[i]);
                        x.add(SaatArray[i]);

                        a_baslik=BaslikArray;
                        a_date=TarihArray;
                        a_detay=DetayArray;
                        a_tur=TurArray;
                        a_icon=IconArray;
                        a_durum=DurumArray;
                        a_kim=KimArray;
                        a_hour=SaatArray;
                       // response=x;
                    }
                }

            }
            catch (Exception exception)
            {
                //response=exception.toString();
                System.out.println( exception.toString());
            }
            return "x";
            //response.toString();
        }
    public String [] returnBaslik(){
        return a_baslik;
    }
    public String [] returnDDate(){
        return a_date;
    }
    public String [] returnDetay(){
        return a_detay;
    }
    public String [] returnDurum(){
        return a_durum;
    }
    public String [] returnIcon(){
        return a_icon;
    }
    public String [] returnTur(){
        return a_tur;
    }
    public String [] returnKim(){
        return a_kim;
    }
    public String [] returnSaat(){
        return a_hour;
    }
    }

